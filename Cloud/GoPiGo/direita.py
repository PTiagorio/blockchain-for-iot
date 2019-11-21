import time

# some_file.py
#import sys
# insert at 1, 0 is the script path (or '' in REPL)
#sys.path.insert(1, '/home/pi/Dexter/GoPiGo3/Software/Python/build/lib.linux-armv7l-2.7')
#sys.path.insert(2, '/dev')
#sys.path.insert(3, '/sys/bus/spi/devices')

#sys.path.insert(1, '/dev/spidev0.0')
#sys.path.insert(2, '/dev/spidev0.1')

#import spidev

#import easygopigo3
import easygopigo3 as easy
gpg=easy.EasyGoPiGo3()

#radius = 30

#GPG_SPI = spidev.SpiDev()
#print(GPG_SPI)
#GPG_SPI.open(0, 1)

gpg.orbit(90,10)
#gpg.drive_cm(20)
#gpg.orbit(180,10)
#gpg.drive_cm(60)