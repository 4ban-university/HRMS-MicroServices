# HRMS (Human resources management system) ERP

## Introduction

This is an HRMS module for an ERP system. The module provides opportunities or HR department to manage employees and vacancies of the company. Such as view the list of all employees with using filters, for example view a list of employees of a particular department. Create, delete or edit employee record in the system. Manage the company position, such as create, update or delete the job offers. Manage payrolls of employee.  

 

The system is primarily designed to properly organize the work of the department, speed up the processes of hiring employees or opening vacancies necessary for the company. 

 

In this project, only the backend of the system is going to be implemented, although the frontend is included in the design to describe the entire system. 

## Installation

1) First, install Java on your system.
2) Install the latest maven dependency manager.
3) Install mysql.
4) Update all spring boot configurations inside resources folder in 
    /src/main/resources/application.properties file according to your own system configurations.
5) mvn spring-boot:run
6) Run this query in the database.
  INSERT INTO ${DATABASE NAME}.roles(name) VALUES('ROLE_EMPLOYEE');
  INSERT INTO ${DATABASE NAME}.roles(name) VALUES('ROLE_ADMIN');
  
## Running

Run the eureka -> gateway -> auth -> employee.
Go to individual subdirectories and run 
mvn spring-boot:run

## Architecture

Each service is a separate microservice.
Eureka: server, all the other services register to it
Gateway: entry point for other services. Protects other routes and validates tokens.
Auth: authentication server, used for signing up and issuing tokens
Employee: employee service

So, you should register with Auth first, request a token, and use it for every request for which you need to be authenticated.

Every request will hit the Gateway first, if need be, it will validate the token and grant you access to the service.

## Employee Api's
  ***************************************************************************************
  POST
  http://localhost:8762/auth/signup
  
  Request params:- 
  {
    "firstName": "Kaushik",
    "lastName": "Samanta",
    "dateOfBirth": "24-08-1994",
    "hiringDate": "15-12-2016",
    "province": "QUEBEC",
    "postalCode": "H3H1P4",
    "username" : "kaushik23",
    "email": "kaushiksamanta23@gmail.com",
    "password": "1234567890"
  }
  
  Response:-
  Employee registered successfully
 
  ****************************************************************************************
  POST
  http://localhost:8762/auth/login

  Request params:- 
  {
   "usernameOrEmail" : "kaushik23",
   "password" : "1234567890"
  }
  
  Response:-
   {
     "accessToken": "A token that you should use later",
     "tokenType": "Bearer"
  }

  Later, when you need to authenticate for a service, use the token and put it in the "Authorization" tab of Postman, then select Bearer token as Type.
  
  ## Credits
  
  https://medium.com/omarelgabrys-blog/microservices-with-spring-boot-authentication-with-jwt-part-3-fafc9d7187e8 was used
