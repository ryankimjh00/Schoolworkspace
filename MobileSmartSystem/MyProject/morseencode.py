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


# 작성
def ledOnOff(onOff):
    GPIO.output(led, onOff)


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


mod_dict = {
    "A": ".-", "N": "-.", "B": "-...", "O": "---", "C": "-.-.", "P": ".--.", "D": "-..", "Q": "--.-", "E": ".",
    "R": ".-.", "F": "..-.", "S": "...", "G": "--.", "T": "-", "H": "....", "U": "..-", "I": "..", "V": "...-",
    "K": "-.-", "X": "-..-", "J": ".---", "W": ".--", "L": ".-..", "Y": "-.--", "M": "--", "Z": "--.."
}


def createMos(self, alpha):
    alpha = alpha.upper()
    convert = list(map(lambda y: self.mos_dict[y], alpha))
    return ''.join(convert)



def lightMos(self, mos):
    mos = mos.split('')
    for _ in range(len(mos)):
        if mos == '.':
            ledOnOff(onOff)
            time.sleep(0.3)
        elif mos == '-':
            ledOnOff(onOff)
            time.sleep(1)
