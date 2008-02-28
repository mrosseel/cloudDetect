#!/bin/sh
DESTDIR=/opt/jboss/server/default/deploy/
WAR=clouddetect-scheduling.war
scp -r ./clouddetect-scheduling/target/$WAR mike@miker.homelinux.org:/home/mike
ssh mike@miker.homelinux.org "mv $DESTDIR/$WAR $DESTDIR/clouddetect-scheduling.old;mv /home/mike/$WAR $DESTDIR;chmod -R 777 $DESTDIR/$WAR"
