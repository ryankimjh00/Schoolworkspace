# sensor.py

import RPi.GPIO as GPIO
import os;
import io;
import time
import picamera;
import cv2
import numpy as np;
from PIL import Image

trig = 20;
echo = 16  #  초음파 센서를 대한 전역 변수 선언 및 초기화
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)
GPIO.setup(trig, GPIO.OUT)
GPIO.setup(echo, GPIO.IN)
GPIO.output(trig, False)

fileName = ""
stream = io.BytesIO()
camera = cv2.VideoCapture(0, cv2.CAP_V4L)
camera.set(cv2.CAP_PROP_FRAME_WIDTH, 520)
camera.set(cv2.CAP_PROP_FRAME_HEIGHT, 400)
camera.set(cv2.CAP_PROP_BUFFERSIZE, 1)
bufferSize = int(camera.get(cv2.CAP_PROP_BUFFERSIZE))
print(bufferSize)
time.sleep(1)
led_red1 = 6  #  LED 점등을 위한 전역 변수 선언 및 초기화
led_red2 = 13
GPIO.setup(led_red1, GPIO.OUT)  #  GPIO 6번 핀을 출력 선으로 지정.
GPIO.setup(led_red2, GPIO.OUT)


# 거리를 측정하는 함수
def measureDistance():
    global trig, echo
    time.sleep(0.5)
    GPIO.output(trig, True)  #  신호 1 발생
    time.sleep(0.00001)  #  짧은 시간을 나타내기 위함
    GPIO.output(trig, False)  #  신호 0 발생
    while (GPIO.input(echo) == 0):
        pass
    pulse_start = time.time()  #  신호 1을 받았던 시간
    while (GPIO.input(echo) == 1):
        pulse_end = time.time()  #  신호 0을 받았던 시간
    pulse_duration = pulse_end - pulse_start
    return 340 * 100 / 2 * pulse_duration


def ledOn():
    onOff = 1
    controlLED(led_red1, onOff)
    controlLED(led_red2, onOff)


def lenOn2():
    onoff = 1
    controlLED(led_red1, onoff)
    controlLED(led_red2, onoff)


# led 컨트롤
def controlLED(led, onOff):  #  led 번호의 핀에 onOff(0/1) 값 출력
    GPIO.output(led_red1, onOff)
    GPIO.output(led_red2, onOff)


# 2개의 led를 꺼줌
def ledOut():
    onOff = 0
    controlLED(led_red1, onOff)
    controlLED(led_red2, onOff)


def takePicture():
    global fileName, stream, camera

    if len(fileName) != 0:
        os.remove(fileName)

    stream.seek(0)
    stream.truncate()

    for i in range(bufferSize + 1):
        ret, frame = camera.read()
    Image.fromarray(frame).save(stream, format='JPEG')
    stream.seek(0)

    data = np.frombuffer(stream.getvalue(), dtype=np.uint8)
    image = cv2.imdecode(data, 1)
    haar = cv2.CascadeClassifier('./haarCascades/haar-cascade-files-master/haarcascade_frontalface_default.xml')
    image_gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    faces = haar.detectMultiScale(image_gray, 1.1, 3)
    for x, y, w, h in faces:
        cv2.rectangle(image, (x, y), (x + w, y + h), (255, 0, 0), 2)

        takeTime = time.time()
        fileName = "./static/%d.jpg" % (takeTime * 10)
        cv2.imwrite(fileName, image)
        return fileName


# 웹페이지에는 영향이 없는부분 
if __name__ == "__main__":
    count = 0
    while (True):
        name = takePicture()
        print("fname= %s" % name)
        count += 1
        if (count == 5):
            break
        distance = measureDistance()
        print("distance=%f" % distance)
