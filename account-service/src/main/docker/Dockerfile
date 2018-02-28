FROM frolvlad/alpine-oraclejdk8:slim

MAINTAINER Hien Nguyen <hienminhnguyen711@gmail.com>

ADD account-service.jar /usr/local/account-service/

WORKDIR /usr/local/account-service/

CMD ["java", "-Xms512m", "-Xmx1g", "-jar", "account-service.jar"]