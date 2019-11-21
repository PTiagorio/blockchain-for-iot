#!/bin/bash

if [ -z "$1"]; then
	geth --datadir="./db" init genesis.json
else
	geth --datadir="./db"+$1 init genesis.json
fi
