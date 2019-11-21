#  Blockchain

In this section an introduction to Blockchain technology will be presented as well as our project's prerequisites and specifications regarding this technology.

## Blockchain Introduction

Before Introducing the term blockchain, it is necessary to understand P2P (Peer-to-Peer) networks, one of the concepts behind blockchain.
P2P networks are networks designed in a way that the data is spread among every participant of the network, denominated Peer.
This technology was first released in 1999, with the file sharing platform napster, this platform was the first decentralized network, where peers shared data amongst themselves instead of having a central server.

In evolution of these P2P networks, the Distributed Ledgers were born, denominated DLT (Distributed Ledger Technology), these networks use the fundamentals of P2P, as they also are decentralized, being the network composed by all the participants, but instead of sharing data amongst themselves, in Distributed Ledger technologies, the goal is to keep a record system in which every peer has to validate and generate his own copy of the record creating a consensus on the veracity of each item.
Back to blockchain, Blockchain fits in the DLTs box, meaning it’s one implementation of a Distributed ledger Technology. Blockchain was first implemented in 2009 with Bitcoin, as the name suggests, it consists in keeping the records in blocks, which are linked together like a chain, hence the name. 

![Dlt](/Blockchain/images/dlt.png "Distributed Leger Technology vs Centralized Technology")

Each Blockchain can have its own implementation when it comes to the blocks structure but there are three fundamental ones: previous hash, current block hash, timestamp and usually what we called nonce.
Following is an example of a possible block.

![Block](/Blockchain/images/block.png "Block")

One of the most important features of the Blockchain is immutability, meaning once the consensus is reached in the network and the block is appended to the blockchain it cannot be changed, that’s achieved with the current block hash and previous hash mentioned above. In a blockchain block all the data of the registered transactions, note that each block can record more than one transaction, is encrypted and will be transformed into a hashcode, what this means is that the hash will modify if any of the block data changes, so in a situation where all the blocks have their previous hash, changing one block will consequently change all of the network, making it impossible to modify the network once the blocks are validated and appended in the chain.

How are the blocks validated in a P2P system like blockchain? How do we reach consensus about the veracity of the block? This is Blockchain’s biggest and most important feature, the consensus mechanisms and what makes Blockchain so relevant. In order to validate a Block a consensus has to be reached in the network amongst all the peers which makes it susceptible to what is in the Blockchain World known as 51% attack, this means that if someone can control 51% of the network then they can forge transactions and validate those “maleficent” blocks. In order to deal with this vulnerability there were created Consensus Algorithms, methods to guarantee or to make it very hard for someone to be able to explore such vulnerabilities. The most well-known is the Proof-of-Work(PoW) algorithm, used first by Bitcoin and followed by many Blockchain networks that came after. In this algorithm the peers are incentivated to validate blocks, that is done by offering a reward to the peer who validates the block, which is what we call mining. To mine a block, peers need to race against each other in order to receive the reward, this “race” consists on solving a cryptographic puzzle, extremely hard and demanding in processing power but oppositely easy to validate the solution, this puzzle involves the nonce value, a nonce is a value used to make sure that the block hash meets certain conditions, for example starting with 30 zeros, this can vary from blockchain to blockchain, the miners use computing power to race in search for the correct nonce for each block and are then rewarded for it, usually with cryptocurrency from the mined Blockchain.

This method solves the 51% attack, being that controlling half the network would mean an extraordinary amount of processing power, and has been the most used so far but is losing popularity as of late because of the amount of processing power needed and wasted. The other relevant Consensus algorithm, is the Proof-of-Stake(PoS), this is fairly recent and has been gaining popularity over the PoW, being used in most Blockchains recently created. PoS, is also a consensus algorithm and has therefore the same goals and results as the PoW, however it takes a different approach than Pow. In PoS there is no block reward as there is no race to mine a block, in PoS the miner is chosen in a determinist way according to his stake on the network, this means that the miner with the most to lose in the network is the one chosen to validate blocks, this solves the 51% attack as controlling 51% of the network.
The great advantage of Smart Contracts is the possibility of being programmable so that, in addition to validating contract conditions, they impose restrictions and penalties. The first appearance of the term was in 1997 by Nick Szabo. In addition to these features, as mentioned earlier, Smart Contracts have an audit advantage because all Smart Contract transactions are completely auditable, and when validated in Blockchain, they are permanent.

The emergence of smart contracts has introduced the possibility to create what is known as DApps. DApps are applications that run on the Blockchain network and do not need any regulatory authority. An example of a decentralized application is Bitcoin, as well as any other virtual currency platform. However, the major difference from a decentralized application with Smart Contracts is that virtual currency platforms work only with transactions, whereas a decentralized application using Smart Contracts can include much more information in its blocks than a simple transaction and other information and interactions can be programmed.

## Project Approach

In this Project we chose Ethereum to take advantage of its Smart Contracts. For a first approach and testing purposes we chose to use one of Ethereum's test networks, [Kovan](https://kovan-testnet.github.io/website/) which uses the PoA(Proof-of-Authority) consensus, so we could largely reduce the time it takes to register a transaction in the blockchain. Ethereum was also chosen because it provides a [lightweight Java API](https://docs.web3j.io/) for both Java and Android through maven.  This API allows full interaction with an ethereum node. To connect to the Kovan network we are using the Infura Platform. 
[Infura](https://infura.io/) is a platform that provides online nodes on the ethereum blockchain by simply connecting to an http endpoint or a websocket endpoint. 
To implement our project we also needed to define a [Smart Contract](https://github.com/l-silvestre/fikalab/tree/master/Blockchain/smartContract) that could deal with our needs.

### Blockchain Architecture

* Mobile Devices and Raspberry use web3j and Infura to connect to Ethereum node on the kovan test network
* The Smart Contract is loaded to the Blockchain and his address is known to the Apps
* The Apps load their wallets or create them if none exist and load the Smart Contract to their context
* The Mobile device can now Interact with the Smart Contract to move the GoPiGo
* A call to the move function in the Smart Contract will throw an event
* The Raspberry Application will subscribe to the specific event defined in our Smart Contract
* When the event is caught the Raspberry Application will execute a python script

More Info on the [Applications Folder](https://github.com/l-silvestre/fikalab/tree/master/Applications).

## Directory Structure

* **eth-net:** root directory of an implementation of a private ethereum local chain (discontinued)
* **smartContract:** directory containing the SmartContract .sol files, as well as smartContract .abi .bin and java web3j wrappers as well as scripts to easily generate said files 
* **wallets:**	directory containing generated wallets (.json files) used through the project for testing
* **README.md:**	README file containing directory documentation and guidelines

**NOTE:** This directory is currently inactive due to the use of public Ethereum test networks, except for the smartContract directory which contains the current Smart Contract being used in out Project.

### eth-net
In this folder is an inactive implementation of a private ethereum chain using geth.

* db - directory to contain the blockchain data
* genesis.json - blockchain Genesis file (used for the network configuration)
* geth.sh - script to launch the geth console connected to the local blockchain on localhost:8545
* initChain.sh - script to initialize the the blockchain, receives the name for the data directory
* passwords - file with created accounts and passwords for the initiaized private chain
* startPeer.sh - script to start a private chain node


### smartContract
* SmartContract - folder in which the javaWrapper contracts are stored (Smart Contract implemented in Java through web3j)
* compile.sh - script created to compile a contract, receives the name of the file as argument (e.g: ./compile.sh name)
* Contract.sol - smart Contract written in Solidity #Current contract being used
* Contract_sol_Contract.abi - generated contract abi
* Contract_sol_Contract.bin - generated contract bin
* Contract_sol_test.abi - generated subClass abi
* Contract_sol_test.bin - generated subClass bin
* ContractV2.sol - **SmartContractV2 not being used**
* ContractV2_sol_Contract.abi - generted abi and bin for v2
* ContractV2_sol_Contract.bin
* ContractV2_sol_test.abi
* ContractV2_sol_test.bin
* ContractV3.sol - **SmartContractV3 not being used**
* genJava.sh - script to generate the SmartContract in Java language to use in Applications; This Script receives the name of the ".abi" and ".bin" files as argument. **NOTE: ".abi" and ".bin" file names must be the same** (e.g ./genJava Contract_sol_test)

### wallets
* metamaskWallet.json - wallet for Ethereum Kovan test network
  * password: pwd
* UTC--2019-10-30T17-35-04.311000000Z--12cf0548932152c2705853e5f3f168eddb95458f.json
* UTC--2019-10-31T16-45-13.355000000Z--c431b1792b8d0d7d5c898e40bfe95f78fb40df78.json
* UTC--2019-10-31T18-07-09.563000000Z--fb14cc995ba649b1924a8dccdc3a0ea326c48f98.json

In this folder are stored Ethereum Blockchain wallets, the metamaksWallet.json is the wallet for our main address in the Kovan test network that serves to give funds to the new created wallets amongst the devices.

The remainder wallet files were created in testing of the old ehtereum private chain.

## Pre-Requesites
Solc	

```
$ npm install -g solc
```

web3j
* https://docs.web3j.io/quickstart/
* https://docs.web3j.io/getting_started/

geth
* https://geth.ethereum.org/docs/install-and-build/installing-geth

## References						
* https://medium.com/coinmonks/how-to-create-your-own-private-ethereum-blockchain-137ab15989c6
* https://medium.com/blockchainbistro/set-up-a-private-ethereum-blockchain-and-deploy-your-first-solidity-smart-contract-on-the-caa8334c343d
* https://medium.com/coinmonks/private-ethereum-by-example-b77063bb634f

