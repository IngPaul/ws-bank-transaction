FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/ws-bank-transaction-0.0.1-SNAPSHOT.jar ws-bank-transaction-0.0.1-SNAPSHOT.jar
ENV ENVIRONMENT=docker
ENTRYPOINT ["java","-jar","/ws-bank-transaction-0.0.1-SNAPSHOT.jar"]