FROM frolvlad/alpine-oraclejdk8:slim

MAINTAINER Hien Nguyen <hienminhnguyen711@gmail.com>

ADD turbine-server.jar /usr/local/turbine-server/

WORKDIR /usr/local/turbine-server/

CMD ["java", "-Xms512m", "-Xmx1g", "-jar", "turbine-server.jar"]
