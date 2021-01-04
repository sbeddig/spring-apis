FROM ubuntu:20.04
MAINTAINER simon.beddig@googlemail.com

ENV JAVA_VERSION 11.0.7.10.1

RUN apt update && \
    apt upgrade && \
    apt install --no-install-recommends -y wget ca-certificates && \
    apt clean

RUN wget -O /tmp/java.tar.gz https://corretto.aws/downloads/resources/${JAVA_VERSION}/amazon-corretto-${JAVA_VERSION}-linux-x64.tar.gz  && \
    gunzip /tmp/java.tar.gz && \
    tar -C /opt -xf /tmp/java.tar && \
    ln -s /opt/amazon-corretto-${JAVA_VERSION}-linux-x64 /opt/jdk && \
    rm /tmp/java.tar

ENV JAVA_HOME /opt/jdk
ENV PATH ${PATH}:${JAVA_HOME}/bin

ENTRYPOINT ["/opt/jdk/bin/java"]
COPY build/libs/*.jar /opt/app.jar
CMD ["-jar", "/opt/app.jar"]

EXPOSE 8080

