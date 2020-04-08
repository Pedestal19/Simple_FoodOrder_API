# Simple Food Order API

A simpile Spring JAVA project example for creating RESTful APIs for food ordering.
Database used is MySQL and in order to run the project, sql must be running in background.

Some error messages are shown in the logcat while some are returned as the response.

Developed on IntelliJ using maven.

API Documentation can be accessed on the following urls(that is after running application):

**http://localhost:7000/v2/api-docs (Plain Documentation)**

**http://localhost:7000/swagger-ui.html (Formatted Documntation)**

Installation Notes:

1. MySQL should be installed
2. Create databse "foods". Hibernate creates the necessary tables at runtime.
3. Install POSTMAN or any other client for making HttpRequests
4. Open project in IntelliJ.
5. Open Pom.xml and install missing dependencies
6. Application can be run on the terminal by using "mvn spring-boot:run".
7. Unit Testing, Integration & Systems tests can be run on the terminal by using "mvn test".
8. Application runs on tomcat server on port 7000.
9. Use postman or any other application to make Http POST Requests on the urls like: localhost:7000/order


_(Refer to appropriate branches to see login implementations & other variations - master branch includes just api):_
Spring-boot is used for easy configuration of the project while concept of Interceptor is used to handle requests on login/logout/food/meals uri's.
