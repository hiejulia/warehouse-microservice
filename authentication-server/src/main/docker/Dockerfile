FROM frolvlad/alpine-oraclejdk8:slim

MAINTAINER Hien Nguyen <hienminhnguyen711@gmail.com>

ADD authentication-server.jar /usr/local/authentication-server/

WORKDIR /usr/local/authentication-server/

CMD ["java", "-Xms512m", "-Xmx1g", "-jar", "authentication-server.jar"]