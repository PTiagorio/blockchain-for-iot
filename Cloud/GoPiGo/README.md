# GoPiGo

In this section we specify all the necessary GoPiGo files and information for our project.

![GoPiGo Image](/Cloud/Images/image11.jpg)

## Operatig System

The operating system used in GoPiGo was Rapbian for Robots, which can be seen an installation tutorial [here](https://www.dexterindustries.com/howto/install-raspbian-for-robots-image-on-an-sd-card/).

## Files

The files in this folder are responsible for GoPiGo's movement locally, each representing a different direction (in the case of left and right it simply turns).

## GoPiGo in IoT Greengrass

In order to run code on GoPiGo as it were locally to control it and form an IoT, GoPiGo needs to have **AWS IoT Greengrass Core Software** installed and running. For this project we used Greengrass Core Software v1.9.3 for the Armv7l architecture. More information on this can be found [here](https://docs.aws.amazon.com/greengrass/latest/developerguide/what-is-gg.html).

### Greengrass Instalation

We installed the Greengrass software by following these steps on Raspberry Pi:

**1. Add groups and users:**
```
$ sudo adduser --system ggc_user
$ sudo addgroup --system ggc_group
```

**2. Increase security:**
```
$ sudo nano 98-rpi.conf

(Or similar) and write at the end of the file:
fs.protected_hardlinks = 1
fs.protected_symlinks = 1

$ sudo reboot
$ sudo sysctl -a 2> /dev/null | grep fs.protected 
(To confirm the change)
```

**3. Allow mount from memory cgroups:**
```
$ cd /boot/
$ cgroup_enable=memory cgroup_memory=1
$ sudo reboot
```

**4. Confirm if everything is fine:**
```
$ cd /home/pi/Downloads
$ wget https://github.com/aws-samples/aws-greengrass-samples/raw/master/greengrass-dependency-checker-GGCv1.9.x.zip
$ unzip greengrass-dependency-checker-GGCv1.9.x.zip
$ cd greengrass-dependency-checker-GGCv1.9.x
$ sudo modprobe configs
$ sudo ./check_ggc_dependencies | more

(Then, check if everything is alright)
```

**5. Setting the Group of the AWS IoT Core:**
it depends on each cloud group, follow [this](https://docs.aws.amazon.com/greengrass/latest/developerguide/gg-config.html) and [this](https://docs.aws.amazon.com/greengrass/latest/developerguide/gg-device-start.html) official tutorials.

### Greengrass Commands
To use the Greengrass software, use this commands:

* **To Start:**
```
$ cd /greengrass/ggc/core/
$ sudo ./greengrassd start
$ ps aux | grep -E 'greengrass.*daemon'

(The last command confirms that the thread is running)
```

* **To Stop:**
```
$ cd /greengrass/ggc/core/
$ sudo ./greengrassd stop
```

**NOTE:** if you want this part to be automatic so you don't need to insert it every time you start Raspberry Pi, follow [this tutorial](https://docs.aws.amazon.com/greengrass/latest/developerguide/gg-core.html#start-on-boot).
