#!/bin/sh
mvn clean install -Pdev,postgresql
cp ./clouddetect-web/target/clouddetect-web.war /opt/jboss/server/default/deploy
chmod 777 /opt/jboss/server/default/deploy/clouddetect-web.war
