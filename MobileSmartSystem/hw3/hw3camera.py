"""
hw3camera.py 프로그램 요건
“command”라는 토픽으로 메세지 수신을 기다린다
수신된 데이터가 “start”이라면 “stop”라는 데이터가 올때까지 최대한 빠르게 사진을 찍어 “mjpeg”라는 토픽으로 이미지를 계속하여 보낸다.
(보내는 데이터의 포맷은 제공되는 hw3viewer.py의 코드를 해석하여 알맞도록 변형하여 보내라)
수신된 데이터가 “stop”이라면 “start”라는 데이터가 올때까지 아무것도 하지 않는다.

hw3camera.py 프로그램 힌트
파이카메라를 사용하는 경우 예제 4-3을 활용하라.
USB	카메라를 사용하는 경우 예제 4-3에서 io.BytesIO()를 얻기 위하여 아래 코드를 이용하라

기타 MQTT관련 내용은 6장의 예제를 기초로 프로그래밍하라.
"""

# publisher를 생성하는 것

import io
import cv2
from PIL import Image, ImageFilter
import paho.mqtt.client as mqtt


ip = input("IP 주소 입력>> ")

client = mqtt.Client()
client.connect(ip, 1883)
client.loop_start()


