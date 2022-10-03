# ORDER-SERVICE-APPLICATION
---------------------

USING THE APPLICATION
------------------------

Hit the below urls in order to verify the application using postman:

Kafka Environment:
Zookeeper: .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
kafka-server: .\bin\windows\kafka-server-start.bat .\config\server.properties

*To create user:

Method: Post; URL: http://localhost:9093/user/save;
  Sample JSON: 
 {
 "id":"",
 "name":"Devyani",
 "balance":300
 }

*To place Order:

Method: Post; URL: http://localhost:9093/order/create;
 Sample JSON:
 {
 "id":"",
 "orderAmount":10,
 "status":"CREATED",
 "userId":1
 }

*To view list of all orders:

Method: Get; URL: http://localhost:9093/order/all

*To view list of all users:

Method: Get; URL: http://localhost:9093/user/all
