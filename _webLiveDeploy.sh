#!/bin/sh
scp ./clouddetect-web/target/clouddetect-web.war mike@polaris:/home/mike
ssh mike@polaris "rm /opt/jboss/server/default/deploy/clouddetect-web.war;mv /home/mike/clouddetect-web.war /opt/jboss/server/default/deploy/clouddetect-web.war;chmod 777 /opt/jboss/server/default/deploy/clouddetect-web.war"
