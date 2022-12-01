# mqtt.py

import time
import paho.mqtt.client as mqtt
import sensor

flag = False
broker_ip = "localhost"
client = mqtt.Client()
client.connect(broker_ip, 1883)
client.loop_start()
while True:
    distance = sensor.measureDistance()
    client.publish("distance", distance, qos=0)
    if distance >= 30:
        sensor.ledOut()
        client.publish("safe", 1, qos=0)
    else:
        sensor.lenOn()
        imageFileName = sensor.takePicture()
        client.publish("image", imageFileName, qos=0)
client.loop_stop()
client.disconnect()
