#!/bin/sh
mvn clean install -Pdev,postgresqldev -Dmaven.test.failure.ignore=true
cp ./clouddetect-web/target/clouddetect-web.war /opt/jboss/server/default/deploy
chmod 777 /opt/jboss/server/default/deploy/clouddetect-web.war
