FROM frolvlad/alpine-oraclejdk8:slim

MAINTAINER Hien Nguyen <hienminhnguyen711@gmail.com>

ADD zipkin-server.jar /usr/local/zipkin-server/

WORKDIR /usr/local/zipkin-server/

CMD ["java", "-Xms512m", "-Xmx1g", "-jar", "zipkin-server.jar"]