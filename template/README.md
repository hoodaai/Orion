[![Build Status](https://travis-ci.org/varunhooda/spring-hibernate-integration.svg)](https://travis-ci.org/varunhooda/spring-hibernate-integration)

## Spring & Hibernate integration project

# Overview
This project is a spring and hibernate integration, which can be used as a base template to start a new project, instead of writing 
all the spring & hibernate integration boilerplate code again.

# Features

- Integration with database migration tool- [FlyWay](http://flywaydb.org/)
- RESTful APIs
- Database- In memory H2 database and MySQL database. You can choose either one by just changing spring configuration file- *persistence-beans.xml*.
- Unit Testing- Junit, EasyMock, H2 database and Spring Test Suite

# Technologies used
 - Java
 - Spring
 - Hibernate
 - H2 Database
 - MySQL database
 - Gradle
 - Flyway

# Running
This project contains in memory H2 database and an embedded Jetty application server. In memory H2 database and tables are automatically 
created by Flyway using database migration scripts 

## Install dependencies
 - Java
 - Gradle
 
 
 You can run this project from the command-line from the root directory of project:
 
 ```
 $ gradle jettyRunWar 
 ```
 
This command creates in memory H2 database and tables with seed data and run application on embedded Jetty server.


After startup, your instance will be available on localhost, port 8080. To check it’s running properly, you can try loading our status page:

```
curl http://localhost:8080/pages/fb
```

You can also run it as a webapp in Tomcat, by deploying the war file generated in the build directory.



