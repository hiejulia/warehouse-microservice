FROM frolvlad/alpine-oraclejdk8:slim

MAINTAINER Hien Nguyen <hienminhnguyen711@gmail.com>

ADD website.jar /usr/local/website/

WORKDIR /usr/local/website/

CMD ["java", "-Xms512m", "-Xmx1g", "-jar", "website.jar"]