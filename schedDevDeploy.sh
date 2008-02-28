#!/bin/sh
mvn clean install -Pdev,postgresql
cp ./clouddetect-scheduling/target/clouddetect-scheduling.war /opt/jboss/server/default/deploy
chmod 777 /opt/jboss/server/default/deploy/clouddetect-scheduling.war
