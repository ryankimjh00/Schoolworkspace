# hw4gpio.py
# 이 프로그램은 전부 프로그램하여야 합니다.
# 이 프로그램은 hw4controller.py에서 사용하는 모든 함수를 만족하도록 프로그램되어야 합니다.


import io
import time
from PIL import Image, ImageFilter
import paho.mqtt.client as mqtt
import cv2
import hw4controller


def onConnect(client, userdata, flag, rc):
    print("Connect with result code:" + str(rc))
    client.subscribe("command", qos=0)
    pass


def onMessage(client, userdata, msg):
    global isStarted
    command = str(msg.payload.decode("utf-8"))
    print("receive message =%s" % command)
    if (command == 'start'):
        isStarted = True
    elif (command == 'stop'):
        isStarted = False
    pass


broker_address = "localhost"
client = mqtt.Client()
client.on_connect = onConnect
client.on_message = onMessage
client.connect(broker_address, 1883)
client.loop_start()
camera = None

camera = cv2.VideoCapture(1, cv2.CAP_V4L)
while (True):
    if (isStarted == True):
        stream = io.BytesIO()
        ret, frame = camera.read()
        Image.fromarray(frame).save(stream, format='JPEG')
        stream.seek(0)
        client.publish("mjpeg", stream.read(), qos=0)
    else:
        time.sleep(0.1)
client.loop_stop()
client.disconnect()
