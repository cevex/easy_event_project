#!/usr/bin/env bash

#App Build
mvn clean install

#Tomcat Deployement
warOrigin=~/workspace/EasyEvent/easy_event_project/server/target/easyevent-rest-server.war
deployTarget=/servers/tomcat-9.0/webapps

cp -rf $warOrigin $deployTarget
