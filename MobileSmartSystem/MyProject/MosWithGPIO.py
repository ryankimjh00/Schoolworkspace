import io
import time
from PIL import Image, ImageFilter
import paho.mqtt.client as mqtt
import cv2
import RPi.GPIO as GPIO
import makemos
import busio
from adafruit_htu21d import HTU21D
import Adafruit_MCP3008

trig = 20
echo = 16
led = 6
sda = 2  # GPIO 핀 번호
scl = 3  # GPIO 핀 번호
i2c = busio.I2C(scl, sda)
sensor = HTU21D(i2c)  # HTU21D장치를제어하는객체리턴
mcp = Adafruit_MCP3008.MCP3008(clk=11, cs=8, miso=9, mosi=10)
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
def LightLED(message):
    mos = splitMos(message)
    all_mos = makemos.makeMos(message)
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


def getTemperature():
    return float(sensor.temperature)  # HTU21D 장치로부터 온도 값 읽기


def getHumidity():
    return float(sensor.relative_humidity)  # HTU21D 장치로부터 습도 값 읽기


def isBright():
    return float(mcp.read_adc(0))


if __name__ == "__main__":
    while True:
        print("%3d" % getTemperature())
        print("%3d" % getHumidity())
        if mcp.read_adc(0) > 50:
            print("Bright")
        elif mcp.read_adc(0) <= 50:
            print("Dark")
        distance = measureDistance()
        print("distance=%f" % distance)
