#!/bin/bash

geth --datadir "./db" --networkid 1 --rpc --rpcport "8545" --rpccorsdomain "*" --port 30303 --nodiscover --rpcapi="admin,db,eth,debug,miner,net,shh,txpool,personal,web3"
