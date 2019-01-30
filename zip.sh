#!/bin/bash

sync;
cd Front_End && ng build --prod && cd ../Back_End && sync && mvn clean install && cd .. && sync
zip -r ipsen3_all.zip Front_End/dist Back_End/target/IPSEN3_Back_End-1.0-SNAPSHOT.jar Back_End/config.yml && sync
