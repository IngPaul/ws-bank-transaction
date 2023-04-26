#!/bin/bash
mvn clean install
docker rmi -f spring-boot/ws-bank-transaction
docker build -t spring-boot/ws-bank-transaction .
docker-compose up
