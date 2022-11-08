# hw4controller.py
import sys
import io
import time
from PIL import Image, ImageFilter
import paho.mqtt.client as mqtt
import cv2
import hw4gpio

isPicamera = False  # 파이 카메라 또는 웹캠
isStarted = False  # 비디오가 전송중이면 True
isDistance = False  # 거리가 전송중이면 True


def onConnect(client, userdata, flag, rc):
    print("Connect with result code:" + str(rc))
    client.subscribe("command", qos=0)  # command라는 토픽 데이터 수신 요청
    pass


def onMessage(client, userdata, msg):
    global isStarted
    global isDistance

    command = str(msg.payload.decode("utf-8"))
    if (command == 'start'):
        isStarted = True
    elif (command == 'stop'):
        isStarted = False
    elif (command == 'ledOn'):
        hw4gpio.ledOnOff(True)  # LED ON
    elif (command == 'ledOff'):
        hw4gpio.ledOnOff(False)  # LED OFF
    elif (command == 'distance'):
        # 거리 송출에 대한 ON/OFF
        isDistance = True if isDistance == False else False
    pass


broker_address = "localhost"

client = mqtt.Client()
client.on_connect = onConnect
client.on_message = onMessage

client.connect(broker_address, 1883)
client.loop_start()

camera = cv2.VideoCapture(0, cv2.CAP_V4L)

while True:
    if isStarted == True:
        stream = io.BytesIO()

        ret, frame = camera.read()
        Image.fromarray(frame).save(stream, format='JPEG')

        stream.seek(0)
        client.publish("mjpeg", stream.read(), qos=0)
        pass

    # 거리 송신에 대한 요청이 있으면
    if isDistance == True:
        distance = hw4gpio.measureDistance()  # 거리 읽어옴
        client.publish("distance", str(distance), qos=0)
        pass

client.loop_stop()
client.disconnect()
