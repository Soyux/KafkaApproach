FROM openjdk:11-alpine

RUN apk --no-cache add curl tar bash

# Download Kafka
ARG KAFKA_VERSION=2.8.0
ARG SCALA_VERSION=2.13
RUN curl -L "https://downloads.apache.org/kafka/${KAFKA_VERSION}/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz" \
    | tar -xz -C /usr/local && \
    ln -s /usr/local/kafka_${SCALA_VERSION}-${KAFKA_VERSION} /usr/local/kafka

# Download Debezium MySQL connector
ARG DEBEZIUM_VERSION=1.6.0.Final
RUN curl -L "https://repo1.maven.org/maven2/io/debezium/debezium-connector-mysql/${DEBEZIUM_VERSION}/debezium-connector-mysql-${DEBEZIUM_VERSION}-plugin.tar.gz" \
    | tar -xz -C /usr/local/kafka/plugins/

# Copy KafkaListener.java to the image
COPY KafkaListener.java /usr/local/kafka/KafkaListener.java

# Compile KafkaListener.java
RUN cd /usr/local/kafka && \
    javac -cp "/usr/local/kafka/libs/*" KafkaListener.java

CMD ["/bin/bash"]
