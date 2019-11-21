# RaspEstatico

In this directory we present the RaspEstatico code responsible for communicating with the Cloud. This requires entering RaspEstatico as a device from the Greengrass group, as explained in the [previous directory](/Cloud), and sending MQTTs to the Cloud.

After the decision made on the blockchain and raspEstatico became aware of it with the code in [this directory](/Applications), it will call the respective MQTT submission code presented in the present folder, having one for each direction.
