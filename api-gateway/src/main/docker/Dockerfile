FROM frolvlad/alpine-oraclejdk8:slim

MAINTAINER Hien Nguyen <hienminhnguyen711@gmail.com>

ADD api-gateway.jar /usr/local/api-gateway/

WORKDIR /usr/local/api-gateway/

CMD ["java", "-Xms512m", "-Xmx1g", "-jar", "api-gateway.jar"]