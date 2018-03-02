FROM frolvlad/alpine-oraclejdk8:slim

MAINTAINER Hien Nguyen <hienminhnguyen711@gmail.com>

ADD product-catalog-management-web-site.jar /usr/local/product-catalog-management-web-site/

WORKDIR /usr/local/product-catalog-management-web-site/

CMD ["java", "-Xms512m", "-Xmx1g", "-jar", "product-catalog-management-web-site.jar"]
