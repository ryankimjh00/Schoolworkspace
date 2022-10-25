import sys
import io
import time
from tkinter import *
from PIL import Image, ImageFilter, ImageTk
import paho.mqtt.client as mqtt
import queue

broker_address = input("브로커 IP>>")

isRunning = True
myqueue = queue.Queue()


def onStart():
    client.publish("command", "start")
    pass


def onStop():
    client.publish("command", "stop")
    pass


def onExit():
    global isRunning
    client.publish("command", "stop")
    isRunning = False
    pass


def onConnect(client, userdata, flag, rc):
    if (rc == 0):
        print("연결되었습니다")  # 연결이 됨
        client.subscribe("mjpeg", qos=0)  # mjpeg라는 토픽으로 수신을 기다림
    pass  # 이것은 더미다. 보통 함수 onConnect의 끝임을 표시함


def onMessage(client, userdata, msg):
    # 이미지 데이터가 바이트 배열로 도착, 이것은 보내는쪽에서 바이트배열로 보내야함을 의미함
    bytes = msg.payload

    # 스트림으로 변환함, 아래에서 Image객체를 생성하기 위함
    stream = io.BytesIO()  # 메모리 파일 stream을 생성함
    stream.write(bytes)  # 메모리 파일에 저장
    stream.seek(0)  # 파일 커서를 맨 앞으로 옮김
    myqueue.put(stream)  # queue에 넣어둔다.
    pass


# 윈도우 제어
window = Tk()  # 윈도우 객체를 생성함
buttonFrame = Frame(window, borderwidth=1)
buttonFrame.pack(side=TOP, fill=BOTH)
Button(buttonFrame, text="start", overrelief="solid", width=15, command=onStart).pack(side=LEFT)
Button(buttonFrame, text="stop", overrelief="solid", width=15, command=onStop).pack(side=LEFT)
Button(buttonFrame, text="exit", overrelief="solid", width=15, command=onExit).pack(side=RIGHT)

canvas = Canvas(window, bg="black")  # canvas객체를 생성함
canvas.pack(side=TOP, fill=BOTH)  # canvas 를 window에 부착합

# MQTT 제어
client = mqtt.Client()  # mqtt 클라이언트 객체 생성
client.on_connect = onConnect  # 연결요청시 Callback 함수
client.on_message = onMessage  # 이미지가 도착하였을때 Callback 함수
client.connect(broker_address, 1883)  # 브로커에 연결을 요청함
client.publish("command", "start")

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
