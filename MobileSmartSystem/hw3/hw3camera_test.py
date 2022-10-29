import sys

import io
import time
import picamera
from PIL import Image, ImageFilter
import paho.mqtt.client as mqtt
import cv2

isPicamera = False  # 파이카메라인지 웹캠인지 구별
isStarted = False


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

if (isPicamera):
    camera = picamera.PiCamera(framerate=30)
else:
    camera = cv2.VideoCapture(1, cv2.CAP_V4L)
while (True):
    if (isStarted == True):
        stream = io.BytesIO()
        if (isPicamera):
            camera.capture(stream, format='jpeg', use_video_port=True)
        else:
            ret, frame = camera.read()
            Image.fromarray(frame).save(stream, format='JPEG')
        stream.seek(0)
        client.publish("mjpeg", stream.read(), qos=0)
    else:
        time.sleep(0.1)
client.loop_stop()
client.disconnect()
