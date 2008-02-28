#!/bin/sh
scp ./clouddetect-web/target/clouddetect-web.war mike@miker.homelinux.org:/home/mike
ssh mike@miker.homelinux.org "rm /opt/jboss/server/default/deploy/clouddetect-web.war;mv /home/mike/clouddetect-web.war /opt/jboss/server/default/deploy/clouddetect-web.war;chmod 777 /opt/jboss/server/default/deploy/clouddetect-web.war"
