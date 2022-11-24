import io
import time
from PIL import Image, ImageFilter
import paho.mqtt.client as mqtt
import cv2
import RPi.GPIO as GPIO
import makemos

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


def splitMos(message):
    mos = makemos.makeMos(message)
    mos_list = list(mos)
    return mos_list


# 작성
def ledOnOff(message):
    all_mos = makemos.makeMos(message)
    mos = splitMos(message)
    print("check this out" + all_mos)
    for i in range(len(mos)):
        if mos[i] == '.':
            GPIO.output(led, True)
            time.sleep(0.2)
            GPIO.output(led, False)
            time.sleep(0.3)
        elif mos[i] == '-':
            GPIO.output(led, True)
            time.sleep(0.6)
            GPIO.output(led, False)
            time.sleep(0.3)
        else:
            GPIO.output(led, False)
            time.sleep(2)


def sendMos(message):
    all_mos = makemos.makeMos(message)
    return all_mos


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


if __name__ == "__main__":
    ledOnOff("sos")