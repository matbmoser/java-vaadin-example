FROM maven:3.6.3-jdk-8 AS builder
COPY pom.xml /tmp/pom.xml
COPY ./. /
RUN mvn clean compile assembly:single install

FROM jetty:9.4-jdk8
COPY --from=builder ./. $JETTY_BASE
COPY --from=builder ./target/*.war $JETTY_BASE/webapps/
EXPOSE 8080
