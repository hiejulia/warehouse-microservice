<a href="https://www.buymeacoffee.com/hientech" target="_blank"><img src="https://img.shields.io/badge/-buy_me_a%C2%A0coffee-gray?logo=buy-me-a-coffee" alt="Buy Me A Coffee"></a>
  <br>
# warehouse-microservice
## Project overview 



## Tech stack 
+ Microservices 
    + Spring cloud config server
    + Eureka server 
    + API gateway : Zuul, Feign 
    + Hystrix 
    + Spring cloud stream 
    + Spring Cloud Sleuth
    + Ribbon: client-side load balance 
    + Messaging: RabbitMQ 
+ Spring boot 
    + HATEOAS   
    + Caching 
    + i18n 
+ Spring data 
+ Spring cloud 
+ Messaging 
+ Container: Docker , Docker composer 
+ Database: MySQL, MongoDB 
+


## Service 
+ Account service 
    + Account service cassandra keyspace configuration 
        +  `CREATE KEYSPACE accountEventKeyspace WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};
            CREATE KEYSPACE productCatalogEventKeyspace WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};
            CREATE KEYSPACE purchaseOrderEventKeyspace WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};`




+ Zipkin-server 
    





## How to run 



