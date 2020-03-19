# Blockchain for IoT: An AI and Smart Contracts Approach

This project consists of a proof-of-concept based on a conceptually simple problem: a system built for a robot car to be controlled by several mobile phones. However, the approach to this problem was made with cutting-edge, complex, innovative and technically challenging technologies: Internet of Things, Cloud and Blockchain.

This project is based on the idea of bringing these two technologies together, taking advantage of the best features of both and creating a scalable system in which multiple entities control multiple devices. Reaching a system that guarantees:
  * Auditability and immutability from Blockchain
  * Cloud processing delegation capability and scalability of node numbers from the Cloud and the Internet of Things

## Path Taken

After conceiving the basic idea for our project, a solution was needed to demonstrate that the system could be implemented in reality. A simple problem where there is a robot-car being controlled by someone has been identified as a good approach.

The group looked at relatively inexpensive and intuitive technologies. We had to get hardware to simulate three different entities in our problem:
1. The controlling entity
1. The communication entity between the controlling entity and the entity to be controlled
1. The entity to be controlled

For the first entity, smartphones were identified, as it would be an easy way to demonstrate several controlling entities, because smartphones are a fairly common technology today. For the second entity the Raspberry Pi was identified, since it is a computer about the size of a credit card, being versatile and cheap. Finally for the third entity was discovered GoPiGo, a robot with wheels prefabricated to support a Raspberry Pi. The discovery of the GoPiGo was an important catalyst to cement our solution, as it is relatively easy to demonstrate results when the third entity has real actions, in this case moving to where the user commands it.

All that remained was to identify how the technologies would be used together. After several brainstorming sessions and several iterations, the group came up with a system architecture:

![General Architecture](/Cloud/Images/image9.png "General Architecture")

In this mockup the entities identified earlier are present. Bridging the gap between them and this mockup: the controlling entities are the mobile phones; The communication entity is the Raspberry Pi, which is commonly referred to as *RaspEstatico* in our project; and finally the GoPiGo which is the controlled entity.

The blockchain is present between mobile phones and  the *RaspEstatico*. Blockchain will allow all commands made by mobile phones to be known to all nodes of the network and to have immutability and auditability characteristics. The cloud / IoT serves as a bridge between the *RaspEstatico* and GoPiGo. Software will be used to have GoPiGo subscribed to the cloud, which can make all subscribed devices move when *RaspEstatico* communicates a command. In our case there is only one GoPiGo, but scalability is one of the main features of this system, because of the use of IoT through the Cloud.

The technical aspects of the technologies used will be explained in the read-me files of other folders, but in general the communication between system nodes can be summarized by:
1. A command given by a mobile phone user to move the car (touch of a button)
1. *RaspEstatico* is listening to an event triggered by Blockchain
1. *RaspEstatico* communicates the command to the cloud 
1. Cloud tells GoPiGo the command to perform

## Repository Structure

* **Blockchain**:
This directory is for all Blockchain information and content
* **Applications**:
This directory contains the two Apps from this project
* **Cloud**:
This directory has the information about the Cloud technologies
* **Presentations**: 
In this directory are stored presentations developed by the group throughout the project

## GLOBAL PRE-REQUISITES

### npm Installation

Windows:

* [npm](https://www.npmjs.com/get-npm)

Ubuntu:

```
$ sudo apt-get update
$ sudo apt-get install nodejs
$ sudo apt-get install npm
```

## Future Work

### Artificial Inteligence

The group intends to use this technology in conjunction with those previously described to solve any sub-problem of our project. There is the possibility of placing Artificial Intelligence (AI) to solve a real problem at the level of a real system using this architecture, and the same AI could be inserted onto the IoT or Smart Contracts layer.

**Studied Examples:**
* [**Multi-agent systems and decentralized artificial superintelligence**](https://arxiv.org/ftp/arxiv/papers/1702/1702.08529.pdf?fbclid=IwAR3WmMKDCE1_I-YbXA4wcTs1f6IfHaV1qNlmNMvQpjyWeu_kxFUU8LNA-ZE)
* [**Enabling off-chain artificial intelligence for Ethereum with iExec**](https://medium.com/iex-ec/enabling-off-chain-artificial-intelligence-for-ethereum-with-iexec-804e640667c0)
* [**Machine Learning with Blockchain Technology**](https://github.com/andcachia/Ethereum-MachineLearning?fbclid=IwAR0DTd_P9WJF8XEIPFr0WMpYYIrOG8-t3RQP_fl_z8n88JEKltutJ0EcPtI)

### Private Blockchain

The group intends to build a private blockchain from scratch, in order to reduce blockchain execution time and make the system more personal and adequate.

## Authors

* **Tiago Ferreira** - Project Manager
* **Luís Silvestre** - Blockchain Developer
* **Rodrigo Rafael** - Software Developer
