# Applications

In this directory are both the applications implemented in this project, one is a Java application and the other an Android application. Both applications use the web3j API and Infura platform to interact with the Ethereum Kovan test network. Both applications also have maven dependencies.

The Android application is the android app that the users can download to control the gopigo, it connects to the Infura network, loads a wallet or creates one if none is found in the downloads folder of the device. If a wallet is created then funds are requested to the metamask address. After loading the wallet  the contract is loaded to the app and can be used to call the move function in the Smart Contract.

The Java application is the app that is ran by the Raspberry and serves as a listener for Ethereum Events, more Specifically a "moved" event defined by our Smart Contract and sent to the EVM when the move function of the Smart Contract is called from the mobile device.

## Directory Structure
* **Android-Device:** directory that contais the android studio project of the android app and the compiled ".apk"
  * **EthAndroid**
  * **GoPiGoRemoteController.apk**
* **RaspberrypiAp:** directory that contains the maven project for the raspberry app as well as the compile ".jar" file for the app
  * **webj3App:** a script to generate the .jar file and a script to run the ".jar" file
  * **createwebj3AppJar.sh**
  * **runJar.sh**
  * **web3jApp-1.0.0-SNAPSHOT-jar-with-dependencies.jar**

## Pre-Requisites

* **maven:** https://maven.apache.org/install.html
	* https://kauri.io/article/b9eb647c47a546bc95693acc0be72546/connecting-to-an-ethereum-client-with-java-eclipse-and-web3j

* **web3j:** https://docs.web3j.io/quickstart/
	* https://docs.web3j.io/getting_started/

* **Infura:** https://infura.io/

## Endpoints

* **Main:** https://<span></span>mainnet.infura.io/v3/b321b2057ccc412da1a6cfc01c158880
* **Ropsten:** https://<span></span>ropsten.infura.io/v3/b321b2057ccc412da1a6cfc01c158880
* **Kovan:** https://<span></span>kovan.infura.io/v3/b321b2057ccc412da1a6cfc01c158880
* **Rinkeby:** https://<span></span>rinkeby.infura.io/v3/b321b2057ccc412da1a6cfc01c158880
* **websocket:** wss://<span></span>"replace-with-network".infura.io/ws/v3/b321b2057ccc412da1a6cfc01c158880

## Recomended Platforms
* **[Android Studio](https://developer.android.com/studio/install)**
* **[IntelliJ IDEA](https://www.jetbrains.com/idea/download/)**
