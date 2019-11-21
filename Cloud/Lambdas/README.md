# Lambdas

This section explains how the project Lambdas were created, and presents their respective code.

## Explanation

Because GoPiGo is connected to IoT Greengrass [as explained here](https://github.com/l-silvestre/fikalab/tree/master/Cloud), each Lambda code runs locally on GoPiGo. The Lambda code itself just creates a subprocess that executes python code that is located in the [GoPiGo Desktop folder](https://github.com/l-silvestre/fikalab/tree/master/Cloud/GoPiGo). The benefit of creating the subprocess is the convenience of changing code relative to GoPiGo movements without having to change Lambda code in the cloud, which is very difficult to do. This solution is for this proof-of-concept, however it should be noted that in a real situation, for security reasons it would be better to place the code on the Lambda itself rather than on the device.

## Creation

The Lambdas were created based on [this official tutorial](https://docs.aws.amazon.com/greengrass/latest/developerguide/module3-II.html), 
placing the code and dependencies in this folder as "**.zip**" in its respective Lambda function.
All Lambdas are built with an existing function that gives you access to all existing features, as shown in the following image:

![All Existing Features in Lambda](https://github.com/l-silvestre/fikalab/blob/master/Cloud/Images/image7.png)

**Note**: Function handlers variate, so be careful about this. An example of a handler is shown below through "**lambda.lambda_handler**":

![Function Handler Example](https://github.com/l-silvestre/fikalab/blob/master/Cloud/Images/image8.png)
