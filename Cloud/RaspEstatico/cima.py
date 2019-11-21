import time
import os
import logging
import motor_runner
import RPi.GPIO as GPIO
import json

from time import sleep
import RPi.GPIO as GPIO




from AWSIoTPythonSDK.MQTTLib import AWSIoTMQTTClient

def SetAngle(angle):
    duty = angle / 18 + 2
    GPIO.output(12, True)
    pwm.ChangeDutyCycle(duty)
    sleep(1)
    GPIO.output(12, False)
    pwm.ChangeDutyCycle(0)



def run_motor(self, params, packet):
 
 print("payload", int(float(packet.payload)))
 
 logging.warning('topic'+packet.topic)
 SetAngle(int(float(packet.payload)))
 
GPIO.setmode(GPIO.BOARD)
GPIO.setup(12, GPIO.OUT)
pwm = GPIO.PWM(12, 50)
pwm.start(0)
 
delay_period = 0.01

logging.warning('Watch out!')
myMQTTClient = AWSIoTMQTTClient("raspberryPiHome") #random key, if another connection using the same key is opened the previous one is auto closed by AWS IOT
 
myMQTTClient.configureEndpoint("ailmhkb7l23v5-ats.iot.eu-west-1.amazonaws.com", 8883)
 
certRootPath = '/home/pi/Documentos/my_rpi/'
myMQTTClient.configureCredentials("{}root-ca.pem".format(certRootPath), "{}ea631997d6-private.pem.key".format(certRootPath), "{}ea631997d6-certificate.pem.crt".format(certRootPath))
 
myMQTTClient.configureOfflinePublishQueueing(-1) # Infinite offline Publish queueing
myMQTTClient.configureDrainingFrequency(2) # Draining: 2 Hz
myMQTTClient.configureConnectDisconnectTimeout(10) # 10 sec
myMQTTClient.configureMQTTOperationTimeout(5) # 5 sec
 
myMQTTClient.connect()
myMQTTClient.publish("hello/world/cima/trigger",json.dumps({'message': 'cima'}),0)

def looper():
    time.sleep(1)
    pwm.stop()
    GPIO.cleanup()

looper()

def function_handler(event, context):
    pwm.stop()
    GPIO.cleanup()
    return
