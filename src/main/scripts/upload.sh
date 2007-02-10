#!/bin/sh
scp target/clouddetect-1.0-SNAPSHOT-linux-descriptor.tar.bz2 mike@miker.homelinux.org:/home/mike/cloudDetect/
scp overview.html mike@miker.homelinux.org:/home/mike/cloudDetect/
ssh polaris "cd /home/mike/cloudDetect;rm -Rf clouddetect-1.0-SNAPSHOT;rm *.tar;bunzip2 clouddetect-1.0-SNAPSHOT-linux-descriptor.tar.bz2;tar xvf clouddetect-1.0-SNAPSHOT-linux-descriptor.tar;cp ./backup/* ./clouddetect-1.0-SNAPSHOT"

