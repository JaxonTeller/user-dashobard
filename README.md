# user-dashobard
This example demo the basic of Microservices architecture.

User-Dashboard microservice connects with User-Search-History and user-Dashboard-Content microservices with help of Eureka Server
to perform some operation with help of exposed REST endpoints.

User-Dashbard also maked use of Load balancing(With Eureka) and Client Resiliency(Netflix Hystrix).

User-Dashboard acts as a Kafka producer to produce a serach event, which is conusmed by User-Serach_History to maintain the User search history.


