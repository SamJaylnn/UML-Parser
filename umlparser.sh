#!/bin/sh

arg1=$1
arg2=$2

##directory where jar file is located    
##dir=E:/javaworkspace/UMLparser

##jar file name
jar_name=umlparser.jar

## Permform some validation on input arguments, one example below
if [ -z "$1" ] || [ -z "$2" ]; then
        echo "Missing arguments, exiting.."
        echo "Usage : $0 arg1 arg2"
        exit 1
fi

java -jar $jar_name arg1 arg2