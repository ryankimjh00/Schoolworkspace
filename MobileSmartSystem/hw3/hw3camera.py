import io
import cv2
from PIL import Image, ImageFilter
import paho.mqtt.client as mqtt

broker_address = input("브로커 IP>>")

client = mqtt.Client()
client.connect(broker_address, 1883)
client.loop_start()

camera = cv2.VideoCapture(0, cv2.CAP_V4L)
camera.set(cv2.CAP_PROP_FRAME_WIDTH, 640)
camera.set(cv2.CAP_PROP_FRAME_HEIGHT, 480)

while True:
    stream = io.BytesIO()
    ret, frame = camera.read()
    Image.fromarray(frame).save(stream, format='JPEG')
    stream.seek(0)
    im_bytes = stream.getvalue()  # 바이트 배열로 저장하
    if client.subscribe("start"):
        client.publish("mjpeg", im_bytes, qos=0)  # 클라이언트(윈도우)로이미지전송

print("프로그램 종료...")
client.loop_stop()
client.disconnect()  # disconnect to broker
