# publisher

import time
import paho.mqtt.client as mqtt
import MosWithGPIO  # 초음파 센서 입력 모듈 임포트
import time
import RPi.GPIO as GPIO
import Adafruit_MCP3008

GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)

mcp = Adafruit_MCP3008.MCP3008(clk=11, cs=8, miso=9, mosi=10)


def on_connect(client, userdata, flag, rc):
    client.subscribe("led", qos=0)


def on_message(client, userdata, msg):
    msg = str(msg.payload.decode("utf-8"))
    print(msg)
    MosWithGPIO.LightLED(msg)  # 사용자가 입력한 msg를 받아와 led 깜빡임을 만드는 함수실행



broker_ip = "localhost"  # 현재 이 컴퓨터를 브로커로 설정

client = mqtt.Client()
client.on_connect = on_connect
client.on_message = on_message

client.connect(broker_ip, 1883)
client.loop_start()

while True:
    ultrasonic = MosWithGPIO.measureDistance() # 거리 측정 publish
    client.publish("ultrasonic", ultrasonic, qos=0)

    temperature = MosWithGPIO.getTemperature()# 온도 측정 publish
    client.publish("temperature", temperature, qos=0)

    humidity = MosWithGPIO.getHumidity()# 습도 측정 publish
    client.publish("humidity", humidity, qos=0)

    brightness = MosWithGPIO.isBright()# 밝기 측정 publish
    client.publish("brightness", brightness, qos=0)

    time.sleep(1)

client.loop_stop()
client.disconnect()
