#!/bin/sh
mvn clean install -Pdev,postgresqldev -Dmaven.test.failure.ignore=true
cp ./clouddetect-scheduling/target/clouddetect-scheduling.war /opt/jboss/server/default/deploy
chmod 777 /opt/jboss/server/default/deploy/clouddetect-scheduling.war
