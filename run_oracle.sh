#!/bin/sh
docker build -t dbw_ora_xe_21_3:25.1.1 .
docker compose up $@
