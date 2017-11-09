#!/bin/bash
BROKER=$1
if [ -z "$1" ]; then
        BROKER="192.168.56.1:9092"
fi

cat orders.txt | while read line; do
        echo "$line"
        sleep 0.1
done | /opt/kafka_2.11-0.8.2.1/bin/kafka-console-producer.sh --broker-list $BROKER --topic orders

