FROM amazoncorretto:8

LABEL maintainer="phithon <root@leavesongs.com>"

ENV JMETER_VERSION=4.0
ENV JMETER_HOME=/usr/src/apache-jmeter-${JMETER_VERSION}

RUN yum update -y \
    && yum install -y wget unzip \
    && cd /usr/src \
    && wget https://archive.apache.org/dist/jmeter/binaries/apache-jmeter-${JMETER_VERSION}.zip \
    && unzip apache-jmeter-${JMETER_VERSION}.zip \
    && cd apache-jmeter-${JMETER_VERSION}/bin \
    && chmod +x jmeter jmeter-server \
    && yum remove -y wget unzip \
    && yum clean all

RUN sed -i 's/#server.rmi.ssl.disable=false/server.rmi.ssl.disable=true/' ${JMETER_HOME}/bin/jmeter.properties

RUN keytool -genkey -alias jmeter -keyalg RSA -keystore ${JMETER_HOME}/bin/rmi_keystore.jks \
    -storepass password -keypass password -dname "CN=localhost" -noprompt \
    || echo "Keystore creation failed, using SSL disable method"

EXPOSE 1099 50000

CMD ${JMETER_HOME}/bin/jmeter-server -Dserver.rmi.ssl.disable=true

