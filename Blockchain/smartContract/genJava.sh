#!/bin/bash

if [ $# -eq 0 ]
  then
    echo "No arguments supplied; Use the name of the .abi and .bin files as argument (.abi and .bin files must have the same name)"
    exit 1
fi
web3j solidity generate -b $1.bin -a $1.abi -o /home/luis/Desktop/fika/smartContract/SmartContract/ -p eth

