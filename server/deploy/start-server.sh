#!/usr/bin/env bash
/servers/tomcat-9.0/bin/shutdown.sh
/servers/tomcat-9.0/bin/startup.sh

tail -200f /servers/tomcat-9.0/logs/catalina.out
