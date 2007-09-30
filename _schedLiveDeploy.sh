#!/bin/sh
DESTDIR=/opt/jboss/server/default/deploy/
WAR=clouddetect-scheduling.war
scp -r ./clouddetect-scheduling/target/$WAR mike@polaris:/home/mike
ssh mike@polaris "mv $DESTDIR/$WAR $DESTDIR/clouddetect-scheduling.old;mv /home/mike/$WAR $DESTDIR;chmod -R 777 $DESTDIR/$WAR"
