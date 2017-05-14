#!/bin/bash

warOrigin=~/workspace/EasyEvent/easy_event_project/server/target/easyevent-rest-server.war
deployTarget=~/workspace/EasyEvent/easy_event_project/server/tomcat-9.0/webapps

cp -rf $warOrigin $deployTarget