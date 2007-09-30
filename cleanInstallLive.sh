#!/bin/sh
mvn clean install -P live,postgresql -Dmaven.test.failure.ignore=true
