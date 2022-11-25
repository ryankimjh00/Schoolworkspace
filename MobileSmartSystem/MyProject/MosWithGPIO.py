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

trig = 20  # GPIO 핀 번호
echo = 16  # GPIO 핀 번호
led = 6  # GPIO 핀 번호
sda = 2  # GPIO 핀 번호
scl = 3  # GPIO 핀 번호
i2c = busio.I2C(scl, sda)
sensor = HTU21D(i2c)  # HTU21D 장치를 제어
mcp = Adafruit_MCP3008.MCP3008(clk=11, cs=8, miso=9, mosi=10)  # 조도센서 제어
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)
GPIO.setup(led, GPIO.OUT)
GPIO.setup(echo, GPIO.IN)
GPIO.setup(trig, GPIO.OUT)
GPIO.output(trig, False)
onOff = False


def splitMos(message):  # 문자열로 받은 모스부호를 리스트로 쪼개어 반환해주는 함수
    mos = makemos.makeMos(message)
    mos_list = list(mos)
    return mos_list


# 모스부호를 받아 led를 점등하는 함수
def LightLED(message):
    mos = splitMos(message)
    all_mos = makemos.makeMos(message)  # 출력하기 위한 문자열 모스부호 생성
    print("check this out" + all_mos)
    time.sleep(1)
    for i in range(len(mos)):
        if mos[i] == '.':
            GPIO.output(led, True)
            time.sleep(0.2)  # 짧은 신호는 0.2초동안 점등
            GPIO.output(led, False)
            time.sleep(0.3)
        elif mos[i] == '-':
            GPIO.output(led, True)
            time.sleep(0.6)  # 긴 신호는 0.6초동안 점등
            GPIO.output(led, False)
            time.sleep(0.3)
        else:
            GPIO.output(led, False)
            time.sleep(2)


# 초음파 센서를 활용하여 거리를 측정하는 함수
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


# 온도를 받는 함수
def getTemperature():
    return float(sensor.temperature)  # HTU21D 장치로부터 온도 값 읽기


# 습도 측정 함수
def getHumidity():
    return float(sensor.relative_humidity)  # HTU21D 장치로부터 습도 값 읽기


# 조도 측정 함수
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
