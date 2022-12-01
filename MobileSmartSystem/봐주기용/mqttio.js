#static / mqttio.js
var port = 9001  // mosquitto의 디폴트 웹 포트
var client = null; // null이면 연결되지 않았음
var canvas;
var context;
var img;

// load 이벤트 리스너 등록. 웹페이지가 로딩된 후 실행
window.addEventListener("load", function () {
    canvas = document.getElementById("myCanvas");
    context = canvas.getContext("2d");
    img = new Image();
    img.onload = function () {
        context.drawImage(img, 0, 0, 700, 400);
    }
});

function drawImage(imgUrl) { // imgUrl은 이미지의 url
    img.src = imgUrl; // img.onload에 등록된 코드에 의해 그려짐
}

function startConnect() {// 접속을 시도하는 함수
    clientID = "clientID-" + parseInt(Math.random() * 100);// 랜덤한 사용자 ID 생성
    broker = document.getElementById("broker").value; // 브로커의 IP 주소
    client = new Paho.MQTT.Client(broker, Number(port), clientID);
    client.onConnectionLost = onConnectionLost; // 접속이 끊어졌을 때 실행되는함수등록
    client.onMessageArrived = onMessageArrived; // 메시지가 도착하였을 때 실행

    client.connect({
        onSuccess: onConnect,
    });
}

var isConnected = false;

// 브로커로의 접속이 성공할 때 호출되는 함수
function onConnect() {
    isConnected = true;
    console.log('onConnect');
    document.getElementById("messages").innerHTML = '<span>관리시작</span><br/>';
    subscribe("image");
    subscribe("safe");
}

var topicSave;

function subscribe(topic) {
    if (client == null)
        return;
    if (isConnected !== true) {
        topicSave = topic;
        window.setTimeout("subscribe(topicSave)", 500);
        return
    }
    client.subscribe(topic); // 브로커에 subscribe
}

function publish(topic, msg) {
    if (client == null)
        return; // 연결되지 않았음
    client.send(topic, msg, 0, false);
}

function unsubscribe(topic) {
    if (client == null || isConnected != true)
        return;
    client.unsubscribe(topic, null); // 브로커에 subscribe
}

function onConnectionLost(responseObject) { // 매개변수인 responseObject는 응답 >패킷의 정보를 담은 개체
    if (responseObject.errorCode !== 0) {
        document.getElementById("messages").innerHTML += '<span>오류 : ' + +responseObject.errorMessage + '</span><br/>';
    }
}


// 메시지가 도착할 때 호출되는 함수
function onMessageArrived(msg) {
    console.log("onMessageArrived: " + msg.payloadString);
    if (msg.destinationName == "image") {
        drawImage(msg.payloadString); // 메시지에 담긴 파일 이름으로 drawImage() 호출. drawImage()는 웹 페이지에 있음
        document.getElementById("mes").innerHTML = '<span>아이가 위험>합니다!!</span><br/>';
    }

    if (msg.destinationName == "safe") {
        //safe가 도착을했다면 반려동물이 안전한 상황이므로 메세지를 html 의 id가 mes 곳에 출력한다.
        document.getElementById("mes").innerHTML = '<span>아이는 안전>합니다</span><br/>';
    }
}


// disconnection 버튼이 선택되었을 때 호출되는 함수
function startDisconnect() {
    client.disconnect(); // 브로커에 접속 해제
    document.getElementById("messages").innerHTML = '<span>관리종료</span><br/>';
}

function getMessage() { // 도착한 메세지 출력(subscribe, connect, disconnect 등)
    let message = document.getElementById("send");
    publish('led', message.value);
}

