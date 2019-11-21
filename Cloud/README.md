# Cloud

This section explains, with due technical details, all Cloud services used under this project.

## Technologies

The group chose to use the **Amazon Web Services** (AWS) Cloud for this project, because of its speed, quality of service and technology convenience. As such, the services presented below correspond to the services provided by **AWS**.

### AWS IoT Core

**AWS IoT Core** is a managed cloud service that lets connected devices easily and securely interact with cloud applications and other devices. AWS IoT Core can support billions of devices and trillions of messages, and can process and route those messages to AWS endpoints and to other devices reliably and securely. With **AWS IoT Core**, your applications can keep track of and communicate with all your devices, all the time, even when they aren’t connected.

As part of our project, **IoT Core** was used to communicate between the *RaspEstatico* and the Cloud.

### AWS IoT Greengrass

**AWS IoT Greengrass** is a Cloud service that extends other AWS services to devices that are connected to the **IoT Greengrass Core** so that those services run locally on the connected devices.

In the context of this project Greengrass is responsible for the communication between the *RaspEstatico*, Cloud services used and the GoPiGo.

### AWS Lambda

**AWS Lambda** is a service that lets code run in the Cloud or in other devices when working with AWS IoT Greengrass.

In conjunction with **IoT Greengrass**, this service is used to run local code on the GoPiGo. 

## Cloud Pathway Explained

*RaspEstatico* is waiting for an event to be triggered by the blockchain network, when that happens, the *RaspEstatico* connects to the Cloud through **AWS IoT Core**, publishing a Message Queuing Telemetry Transport (MQTT) which contains the desired direction. Then, the **AWS IoT Greengrass** has a topic subscription for each direction and a corresponding **AWS Lambda** function, which runs locally in the GoPiGo to move it.

![Cloud Pathway](https://github.com/l-silvestre/fikalab/blob/master/Cloud/Images/image10.png)

An zoomed version of Cloud architecture:
![Cloud Pathway](https://github.com/l-silvestre/fikalab/blob/master/Cloud/Images/image2.png)

**NOTE:** Details of how *RaspEstatico* MTQQ is sent can be found in the [*RaspEstatico* folder](https://github.com/l-silvestre/fikalab/tree/master/Cloud/RaspEstatico). Details of how **IoT Greengrass** was installed on GoPiGo in order to make it an IoT device and perform tasks locally can be found in the [GoPiGo folder](https://github.com/lsilvestre/fikalab/tree/master/Cloud/GoPiGo).

## Cloud API Details

The greengrass subscriptions used for forwarding messages within the Cloud can be seen in the following image:
![Cloud Pathway](https://github.com/l-silvestre/fikalab/blob/master/Cloud/Images/image3.png)

For Greengrass subscriptions to receive the information provided by *RaspEstatico*, it had to be inserted into the IoT Greengrass as a device. A tutorial on how to do this can be seen [here](https://docs.aws.amazon.com/greengrass/latest/developerguide/device-group.html).
![Cloud Pathway](https://github.com/l-silvestre/fikalab/blob/master/Cloud/Images/image4.png)

Also for the Greengrass subscriptions works, we needed to insert the respective Lambda functions as shown [here](https://docs.aws.amazon.com/greengrass/latest/developerguide/config-lambda.html). We used an 30s timeout with 256MB of memory limit and *on-demand* feature in all the lambdas functions:
![Cloud Pathway](https://github.com/l-silvestre/fikalab/blob/master/Cloud/Images/image5.png)

In order to get the GoPiGo moving, we needed to unlock the spidev 0.0 and 0.1 of the raspberry in the GoPiGo as shown in the image:
![Cloud Pathway](https://github.com/l-silvestre/fikalab/blob/master/Cloud/Images/image6.png)

## Folder Structure

* **GoPiGo** - directory that has more information and files about GoPiGo
* **Images** - in this directory we put all the images used to explain the Cloud architecture
* **Lambda** - directory where we put additional information about the project Lambdas and their codes
* **RaspEstatico** - in this directory where we talked more about *RaspEstatico* and insert its code from the Cloud
