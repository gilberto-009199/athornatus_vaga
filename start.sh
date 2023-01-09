#!/bin/sh

sudo docker build --no-cache --tag=myserver:latest .

sudo docker run --rm -it -p 8080:8080 myserver:latest
