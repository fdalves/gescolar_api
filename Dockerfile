FROM openjdk:8u171-jdk-alpine3.8
LABEL maintainer="francisco.d.alves@gmail.com"

ENV LANG C.UTF-8

RUN apk add --update bash

ADD target/*.jar /app/app.jar

CMD java -jar /app/app.jar $APP_OPTIONS