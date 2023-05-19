FROM ubuntu:latest

# Install required packages
RUN apt-get -y update && \
    apt-get -y install bash openjdk-11-jre maven


# Set JAVA_HOME environment variable
#ENV JAVA_HOME=/usr/lib/jvm/default-jvm

# Add Java to the PATH
#ENV PATH=$PATH:$JAVA_HOME/bin

RUN apt-get -y install curl tar bash

# Download Kafka
ARG KAFKA_VERSION=3.4.0
ARG SCALA_VERSION=2.13
RUN curl -L "https://downloads.apache.org/kafka/${KAFKA_VERSION}/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz" \
    | tar -xz -C /usr/local && \
    ln -s /usr/local/kafka_${SCALA_VERSION}-${KAFKA_VERSION} /usr/local/kafka

# Download Debezium MySQL connector
ARG DEBEZIUM_VERSION=2.2.1.Final
RUN mkdir /usr/local/kafka/plugins
RUN curl -L "https://repo1.maven.org/maven2/io/debezium/debezium-connector-mysql/${DEBEZIUM_VERSION}/debezium-connector-mysql-${DEBEZIUM_VERSION}-plugin.tar.gz" \
    | tar -xz -C /usr/local/kafka/plugins/

#CMD ["/usr/local/kafka","bin/zookeeper-server-start.sh config/zookeeper.properties && bin/kafka-server-start.sh config/server.properties"] 

# Copy KafkaListener.java to the image
#COPY KafkaListener.java /usr/local/kafka/KafkaListener.java

# Compile KafkaListener.java
#RUN cd /usr/local/kafka && \
#    javac -cp "/usr/local/kafka/libs/*" KafkaListener.java
