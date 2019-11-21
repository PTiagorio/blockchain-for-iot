#!/bin/bash

if [ $# -eq 0 ]
  then
    echo "No arguments supplied; Arg1: path to webj3App folder Arg2: destination for the .jar file"
    exit 1
fi
cd webj3App/
mvn package
mv $1/webj3App/target/web3jApp-1.0-SNAPSHOT-jar-with-dependencies.jar $2
