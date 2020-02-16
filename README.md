# worldpay
Coding challenge - world pay offer API [OAPI]

   The solution is built using java spring boot/maven. Persistence  is achieved using an in
   memory h2 database. The given solution could be used to create new/update an offer for the 
   merchant. The api also helps in finding all the active offers at a time. Merchant can remove
   an active offer at any point of time using this api.
   
## Building the application
   
Prerequisite : java 8, maven.
   
 
Use the maven command below to build the application.

```
mvn clean install
```

Use the below to run the application in tomcat. The services will be available
from http://localhost:8080/
```
mvn spring-boot:run
```

Once started please find the api documentation from 

```
http://localhost:8080/swagger-ui.html
```
   
   
The application once built and deployed would provide the running tomcat server
where the services could be consumed as described in the swagger ui. The api as 
such is not defined with all the exception and logging behaviour, but implemented with
a common exception handling. Junit-5(jupiter api) test are included.
   
   
   
   
