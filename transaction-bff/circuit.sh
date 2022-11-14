#!/bin/sh

while :
do
  curl http://localhost:8080/v1/daily-limits/8080/007
  printf "\n"
done
# sh circuit.sh