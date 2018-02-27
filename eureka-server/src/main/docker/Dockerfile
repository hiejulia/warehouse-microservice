FROM frolvlad/alpine-oraclejdk8:slim

MAINTAINER Hien Nguyen <hienminhnguyen711@gmail.com>

ADD eureka-server.jar /usr/local/eureka-server/

WORKDIR /usr/local/eureka-server/

CMD ["java", "-Xms512m", "-Xmx1g", "-jar", "eureka-server.jar"]