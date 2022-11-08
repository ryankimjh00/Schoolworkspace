# 이 프로그램에서는 hw4controller.py과 연동되기 위하여 1라인 코드가 추가되어야 합니다.
import sys
import io
import time
from tkinter import *
from PIL import Image, ImageFilter, ImageTk
import paho.mqtt.client as mqtt
import queue

broker_address = "192.168.137.200"  # input("브로커 IP>>")

isRunning = True  # 프로그램 실행을 계속한다
isDistance = False  # 거리를 수신하고 있으면 True
isOn = False  # Led가 켜져있으면 True
myqueue = queue.Queue()


def onStart():
    client.publish("command", "start")  # 영상 송신 요청
    pass


def onStop():  # 영상 송신 중단 요청
    client.publish("command", "stop")
    pass


def onExit():  # 모든 것을 리셋한다
    global isRunning
    global isDistance

    client.publish("command", "stop")  # 영상을 송신중이면 송신 중단요청
    client.publish("command", "ledOff")  # LED가 ON상태에면 OFF
    if isDistance == True:  # 거리를 송신중단
        client.publish("command", "distance")
        isDistance = False

    isRunning = False
    pass


def onLedOn():  # LED ON 요청
    client.publish("command", "ledOn")
    pass


def onLedOff():  # LED OFF 요청
    client.publish("command", "ledOff")
    pass


def onDistance():  # 거리 송신 요청
    global isDistance
    isDistance = True if isDistance == False else False
    client.publish("command", "distance")
    pass


def onConnect(client, userdata, flag, rc):
    if (rc == 0):
        print("연결되었습니다")  # 연결이 됨
        client.subscribe("mjpeg", qos=0)  # mjpeg라는 토픽으로 수신을 기다림
    pass  # 이것은 더미다. 보통 함수 onConnect의 끝임을 표시함


def onMessage(client, userdata, msg):
    global distanceLabel

    # 이미지 데이터가 바이트 배열로 도착, 이것은 보내는쪽에서 바이트배열로 보내야함을 의미함

    if (msg.topic == "mjpeg"):
        bytes = msg.payload

        # 스트림으로 변환함, 아래에서 Image객체를 생성하기 위함
        stream = io.BytesIO()  # 메모리 파일 stream을 생성함
        stream.write(bytes)  # 메모리 파일에 저장
        stream.seek(0)  # 파일 커서를 맨 앞으로 옮김
        myqueue.put(stream)  # queue에 넣어둔다.
        pass

    if (msg.topic == "distance"):
        distanceLabel.config(text=str(msg.payload.decode("utf-8")))
        client.subscribe("distance", str(msg.topic), qos=0)
        pass
    pass


# 윈도우 제어
window = Tk()  # 윈도우 객체를 생성함
buttonFrame = Frame(window, borderwidth=1)
buttonFrame.pack(side=TOP, fill=BOTH)
Button(buttonFrame, text="start", overrelief="solid", width=15, command=onStart).pack(side=LEFT)
Button(buttonFrame, text="stop", overrelief="solid", width=15, command=onStop).pack(side=LEFT)
Button(buttonFrame, text="exit", overrelief="solid", width=15, command=onExit).pack(side=RIGHT)
controlFrame = Frame(window, borderwidth=1)
controlFrame.pack(side=TOP, fill=BOTH);
Button(controlFrame, text="LED ON", overrelief="solid", width=15, command=onLedOn).pack(side=LEFT)
Button(controlFrame, text="LED OFF", overrelief="solid", width=15, command=onLedOff).pack(side=LEFT)
Button(controlFrame, text="Distance ON/OFF", overrelief="solid", width=15, command=onDistance).pack(side=RIGHT)
distanceFrame = Frame(window, borderwidth=1)

distanceFrame.pack(side=TOP, fill=BOTH);
distanceLabel = Label(distanceFrame, text="", width=30, height=2, fg="red", relief="solid")
distanceLabel.pack(side=LEFT)

canvas = Canvas(window, bg="black")  # canvas객체를 생성함
canvas.pack(side=TOP, fill=BOTH)  # canvas 를 window에 부착합

# MQTT 제어
client = mqtt.Client()  # mqtt 클라이언트 객체 생성
client.on_connect = onConnect  # 연결요청시 Callback 함수
client.on_message = onMessage  # 이미지가 도착하였을때 Callback 함수
client.connect(broker_address, 1883)  # 브로커에 연결을 요청함

while (isRunning):
    client.loop()  # 메시지가 왔는지 체크함
    if (myqueue.empty() == False):
        stream = myqueue.get()  # 이미지 데이터를 가져온다
        image = Image.open(stream)  # stream객체로부터 Image객체로 변환
        img = ImageTk.PhotoImage(image)  # Image객체를 PhotoImage객체로 변환
        canvas.config(width=img.width(), height=img.height())  # canvas 크기를 이미지 크기로 변경
        canvas.create_image(2, 2, image=img, anchor=NW)  # 이미지를 캔버스에 그림

    window.update_idletasks()
    window.update()

client.disconnect()
