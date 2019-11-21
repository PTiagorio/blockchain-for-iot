#!/bin/bash

if [ $# -eq 0 ]
  then
    echo "No arguments supplied; Pass the name of the Contract file as argument: e.g ./commpile.sh Contract"
    exit 1
fi
solcjs $1.sol --bin --abi --optimize -o ~/Desktop/fika/smartContract
