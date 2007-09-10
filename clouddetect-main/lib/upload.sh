#!/bin/sh
# JMF
#mvn install:install-file -DgroupId=javax.jmf -DartifactId=multiplayer-linux \
#          -Dversion=2.1.1e -Dpackaging=jar -Dfile=/path/to/file
#mvn install:install-file -DgroupId=javax.jmf -DartifactId=mediaplayer-linux \
#          -Dversion=2.1.1e -Dpackaging=jar -Dfile=/path/to/file
#mvn install:install-file -DgroupId=javax.jmf -DartifactId=jmf-linux \
#          -Dversion=2.1.1e -Dpackaging=jar -Dfile=

# JAVAX
#mvn install:install-file -DgroupId=javax.activation -DartifactId=activation \
#          -Dversion=1.0.2 -Dpackaging=jar -Dfile=activation-1.0.2.jar
#mvn install:install-file -DgroupId=javax.mail -DartifactId=mail \
#          -Dversion=1.3.3_01 -Dpackaging=jar -Dfile=/path/to/file
mvn install:install-file -DgroupId=javax.transaction -DartifactId=jta -Dversion=1.0.1B -Dpackaging=jar -Dfile=jta-1.0.1B.jar

# WERX
mvn install:install-file -DgroupId=werx -DartifactId=reflectionbus \
          -Dversion=20dec04 -Dpackaging=jar -Dfile=reflectionbus-20dec04.jar
mvn install:install-file -DgroupId=werx -DartifactId=werx \
          -Dversion=20dec04 -Dpackaging=jar -Dfile=werx-20dec04.jar
mvn install:install-file -DgroupId=werx -DartifactId=bus \
          -Dversion=20dec04 -Dpackaging=jar -Dfile=bus-20dec04.jar

# OTHER
mvn install:install-file -DgroupId=gov.nist.math -DartifactId=Jama \
          -Dversion=1.0.1 -Dpackaging=jar -Dfile=Jama-1.0.1.jar

mvn install:install-file -DgroupId=mhuss.com -DartifactId=astrolib \
          -Dversion=1.1.4 -Dpackaging=jar -Dfile=AstroLib.jar
