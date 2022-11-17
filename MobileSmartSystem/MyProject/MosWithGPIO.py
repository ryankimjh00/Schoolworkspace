# hw4gpio.py
# 이 프로그램은 전부 프로그램하여야 합니다.
# 이 프로그램은 hw4controller.py에서 사용하는 모든 함수를 만족하도록 프로그램되어야 합니다.
# measuerDistance(), ledOnOff(boolean) 만 만들면됨

import io
import time
from PIL import Image, ImageFilter
import paho.mqtt.client as mqtt
import cv2
import RPi.GPIO as GPIO

trig = 20
echo = 16
led = 6

GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)
GPIO.setup(led, GPIO.OUT)
GPIO.setup(echo, GPIO.IN)
GPIO.setup(trig, GPIO.OUT)
GPIO.output(trig, False)

onOff = False

import test


def splitMos():
    mos = test.makeMos()
    mos_list = list(mos)
    return mos_list


def MosTest():
    mos = splitMos()
    mos_list = []
    for i in range(len(mos)):
        if mos[i] == '.':
            # print("short  ")
            mos_list.append("깜빡")
        elif mos[i] == '-':
            # print("long  ")
            mos_list.append("깜---빡")
        else:
            # print("term  ")
            mos_list.append("******")
    print(mos)
    print(mos_list)


# 작성
def ledOnOff(onOff):
    GPIO.output(led, onOff)
    mos = splitMos()
    mos_list = []
    for i in range(len(mos)):
        if mos[i] == '.':
            # print("short  ")
            GPIO.output(led, onOff)
            time.sleep(0.5)
            # mos_list.append("깜빡")
        elif mos[i] == '-':
            # print("long  ")
            GPIO.output(led, onOff)
            time.sleep(1)
            # mos_list.append("깜---빡")
        else:
            # print("term  ")
            time.sleep(1.5)
            # mos_list.append("******")
    print(mos)
    print(mos_list)


# 작성
def measureDistance():
    global trig, echo
    time.sleep(0.5)
    GPIO.output(trig, True)
    time.sleep(0.00001)
    GPIO.output(trig, False)
    while GPIO.input(echo) == 0:
        pass
    pulse_start = time.time()
    while GPIO.input(echo) == 1:
        pass
    pulse_end = time.time()
    pulse_duration = pulse_end - pulse_start
    return 340 * 100 / 2 * pulse_duration
    pass
