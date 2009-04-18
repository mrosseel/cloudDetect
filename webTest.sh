#!/bin/sh
mvn clean install -Pdev,postgresqldev -Dmaven.test.failure.ignore=true 
