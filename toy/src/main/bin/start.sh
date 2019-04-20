#!/usr/bin/env bash

nohup java -server  -Xms1024m -Xmx1024m -jar k-admin.jar  > ./run.log 2>&1 &
