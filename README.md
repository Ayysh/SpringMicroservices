 "Microservices"
 
- I have implemented a small working demo of building microservices using Spring Cloud. 
- There are 3 Microservices called currency-conversion-service,currency-exchange-service and limits-service.
- They are configured in centralized Cloud Config Server.
- Communication is established between microservices and load balancing is enabled by integrating with Ribbon(client side load balancing     framework).
- Implemented Eureka Naming Server for Service Registration and Service Recovery. Also takes care of scaling up and down of microservices.
- Zuul Api gateway is implemented instead of allowing microservices to talk to eachother directly. It provides few features like             authentication,logging,fault tolerant.
- Implemented Distributed tracing with Spring Cloud Sleuth and Zipkin.
