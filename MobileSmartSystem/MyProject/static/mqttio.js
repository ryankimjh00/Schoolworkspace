var port = 9001 // mosquitto의 디폴트 웹 포트
var client = null; // null이면 연결되지 않았음

function startConnect() { // 접속을 시도하는 함수
    clientID = "clientID-" + parseInt(Math.random() * 100); // 랜덤한 사용자 ID 생성

    // 사용자가 입력한 브로커의 IP 주소와 포트 번호 알아내기
    broker = document.getElementById("broker").value; // 브로커의 IP 주소

    // id가 message인 DIV 객체에 브로커의 IP와 포트 번호 출력
    // MQTT 메시지 전송 기능을 모두 가징 Paho client 객체 생성
    client = new Paho.MQTT.Client(broker, Number(port), clientID);

    // client 객체에 콜백 함수 등록
    client.onConnectionLost = onConnectionLost; // 접속이 끊어졌을 때 실행되는 함수 등록
    client.onMessageArrived = onMessageArrived; // 메시지가 도착하였을 때 실행되는 함수 등록

    // 브로커에 접속. 매개변수는 객체 {onSuccess : onConnect}로서, 객체의 프로퍼틴느 onSuccess이고 그 값이 onConnect.
    // 접속에 성공하면 onConnect 함수를 실행하라는 지시
    client.connect({
        onSuccess: onConnect,
    });
}

let isConnected = false;

// 브로커로의 접속이 성공할 때 호출되는 함수
function onConnect() {
    isConnected = true;
    console.log('onConnect')
    document.getElementById("messages").innerHTML = "<span>지금부터 생존 키트를 실행하여 주위 환경을 분석합니다.</span><br/>"
    subscribe("ultrasonic")
    subscribe("temperature")
    subscribe("humidity")
    subscribe("brightness")
}

var topicSave;

function subscribe(topic) {
    if (client == null) return;
    if (isConnected != true) {
        topicSave = topic;
        window.setTimeout("subscribe(topicSave)", 500);
        return
    }

    // 토픽으로 subscribe 하고 있음을 id가 message인 DIV에 출력
    document.getElementById("messages").innerHTML += '<span>Subscribing to: ' + topic + '</span><br/>';
    client.subscribe(topic); // 브로커에 subscribe
}

function publish(topic, msg) {
    if (client == null) return; // 연결되지 않았음
    client.send(topic, msg, 0, false);
}

function unsubscribe(topic) {
    if (client == null || isConnected != true) return;
    // 토픽으로 subscribe 하고 있음을 id가 message인 DIV에 출력
    document.getElementById("messages").innerHTML += '<span>Unsubscribing to: ' + topic + '</span><br/>';
    client.unsubscribe(topic, null); // 브로커에 subscribe
}

// 접속이 끊어졌을 때 호출되는 함수
function onConnectionLost(responseObject) { // 매개변수인 responseObject는 응답 패킷의 정보를 담은 개체
    document.getElementById("messages").innerHTML += '<span>전원 OFF</span><br/>';
    if (responseObject.errorCode !== 0) {
        document.getElementById("messages").innerHTML += '<span>오류 : ' + +responseObject.errorMessage + '</span><br/>';
    }
}

// 메시지가 도착할 때 호출되는 함수
function onMessageArrived(msg) { // 매개변수 msg는 도착한 MQTT 메시지를 담고 있는 객체
    console.log("onMessageArrived: " + msg.payloadString);
    if (msg.destinationName === "temperature") {  // publish된 메세지가 temperature이면 온도를 받아 innerHTML 값을 바꿔준다.
        if (Math.round(msg.payloadString < 10)) {
            document.getElementById("temperature").innerHTML = '<span>' + Math.round(msg.payloadString) + '°C<br>기온이 너무 낮습니다. 저체온증에 걸리지 않게 체온을 높여주세요</span><br/>';
        } else {
            document.getElementById("temperature").innerHTML = '<span>' + Math.round(msg.payloadString) + '°C<br> 생존에 지장이 없는 온도입니다.</span><br/>';
        }
    } else if (msg.destinationName === "humidity") { 
        document.getElementById("humidity").innerHTML = '<span>' + Math.round(msg.payloadString) + '%</span><br/>';
    } else if (msg.destinationName === "brightness") { // publish된 메세지가 brightness이면 조도를 받아 innerHTML 값을 바꿔준다.
        if (Math.round(msg.payloadString) > 50) { // 조도가 50보다 높으면 밝으므로 led 가 잘 보이지 않느다는 경고를 보낸다.
            document.getElementById("brightness").innerHTML = '<span> 너무 밝습니다. 최소 50lux까지 줄여주세요.<br> 현재 밝기: ' + Math.round(msg.payloadString) + 'lux</span><br/>';
        } else {
            document.getElementById("brightness").innerHTML = '<span> led가 빛나기에 최적의 밝기입니다. 유지해주세요.<br> 현재 밝기: ' + Math.round(msg.payloadString) + 'lux</span><br/>';
        }
    } else if (msg.destinationName === "ultrasonic") { //초음파를 받아 앞에 장애물이 있는지 확인하기.
        if (Math.round(msg.payloadString) < 30) {
            document.getElementById("ultrasonic").innerHTML = '<span> 앞에 장애물이 있는것 같습니다. 치워주세요.<br> 타겟과의 거리' + Math.round(msg.payloadString) + 'cm</span><br/>';
        } else {
            document.getElementById("ultrasonic").innerHTML = '<span> 전방에 장애물이 없습니다. 모스 신호를 보내세요.<br> 타겟과의 거리' + Math.round(msg.payloadString) + '</span><br/>';
        }
    }
    // 도착한 메시지 출력
    // document.getElementById("messages").innerHTML = '<span>토픽 : ' + msg.destinationName + '  | ' + msg.payloadString + '</span><br/>';
}

// disconnection 버튼이 선택되었을 때 호출되는 함수
function startDisconnect() {
    client.disconnect(); // 브로커에 접속 해제
    document.getElementById("messages").innerHTML += '<span>Disconnected</span><br/>';
}

function getMessage() {
    let message = document.getElementById("send")
    publish('led', message.value)
}