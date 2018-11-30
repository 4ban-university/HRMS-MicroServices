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
6) Run migrations.sql file in the database.


## Running

Run the eureka -> gateway -> auth -> employee.
Go to individual subdirectories and run 
mvn spring-boot:run

## Architecture

Each service is a separate microservice.

Eureka: server, all the other services register to it.

Gateway: entry point for other services. Protects other routes and validates tokens.

Auth: authentication server, used for signing up and issuing tokens.

Employee: employee service.

So, you should register with Auth first, request a token, and use it for every request for which you need to be authenticated.

Every request will hit the Gateway first, if need be, it will validate the token and grant you access to the service.

## Auth API
POST
http://localhost:8762/auth/signup

```json
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
```

POST
http://localhost:8762/auth/login

```json
{
 "username" : "kaushik23",
 "password" : "1234567890"
}
```

To get the token, look at the Headers tab in Postman, take the value of the Bearer key.

Later, when you need to authenticate for a service, use the token and put it in the "Authorization" tab of Postman, then select Bearer token as Type.

## Employee API

GET
http://localhost:8762/employee/

```json
[
  {
      "createdAt": "2018-11-19T00:31:58.000+0000",
      "updatedAt": "2018-11-19T00:31:58.000+0000",
      "id": 1,
      "firstName": "Kaushik",
      "lastName": "Samanta",
      "dateOfBirth": "24-08-1994",
      "hiringDate": "15-12-2017",
      "province": "QUEBEC",
      "postalCode": "H3H1P4",
      "username": "kaushik23",
      "email": "kaushiksamanta23@gmail.com",
      "password": "$2a$10$dWJThXJxkp69MYzfSmu.O.qWm7IomNIckZK5MSGNDivlSW7j5ppuq",
      "roles": [
          {
              "id": 1,
              "name": "ROLE_EMPLOYEE"
          }
      ],
      "jobpostings": []
  },
  {
      "createdAt": "2018-11-25T20:22:57.000+0000",
      "updatedAt": "2018-11-25T20:22:57.000+0000",
      "id": 2,
      "firstName": "Kaushik",
      "lastName": "Samanta",
      "dateOfBirth": "24-08-1994",
      "hiringDate": "15-12-2016",
      "province": "QUEBEC",
      "postalCode": "H3H1P4",
      "username": "kaushik233",
      "email": "kaushiksamanta233@gmail.com",
      "password": "$2a$10$/i0rg7WxVZJC3cvQzKPZ4e2ZR0rjRa3iWKVujvcGHHEs179NOkefq",
      "roles": [
          {
              "id": 1,
              "name": "ROLE_EMPLOYEE"
          }
      ],
      "jobpostings": []
  }
]
```
GET
http://localhost:8762/employee/1

```json
{
  "createdAt": "2018-11-19T00:31:58.000+0000",
  "updatedAt": "2018-11-19T00:31:58.000+0000",
  "id": 1,
  "firstName": "Kaushik",
  "lastName": "Samanta",
  "dateOfBirth": "24-08-1994",
  "hiringDate": "15-12-2017",
  "province": "QUEBEC",
  "postalCode": "H3H1P4",
  "username": "kaushik23",
  "email": "kaushiksamanta23@gmail.com",
  "password": "$2a$10$dWJThXJxkp69MYzfSmu.O.qWm7IomNIckZK5MSGNDivlSW7j5ppuq",
  "roles": [
      {
          "id": 1,
          "name": "ROLE_EMPLOYEE"
      }
  ],
  "jobpostings": []
}
```

TODO

## Recruitment API

GET
http://localhost:8762/job

```json
[
    {
        "id": 1,
        "minSalary": 50000,
        "maxSalary": 10000,
        "applicationStatus": "OPEN",
        "jobDescription": "CEO",
        "noOfOpenings": "3",
        "contractType": "test",
        "contractPeriod": 6
    },
]
```

POST
http://localhost:8762/job

```json
{
    "minSalary": "50000",
    "maxSalary": "10000",
    "jobDescription": "CEO",
    "noOfOpenings": "3",
    "contractType": "test",
    "contractPeriod": "6",
    "applicationStatus": "OPEN"
}
```

PUT
http://localhost:8762/job/1

```json
{
    "minSalary": "50000",
    "maxSalary": "10000",
    "jobDescription": "CEO",
    "noOfOpenings": "1",
    "contractType": "test",
    "contractPeriod": "6",
    "applicationStatus": "OPEN"
}
```

GET
http://localhost:8762/job/1

GET
http://localhost:8762/job/status/OPEN
http://localhost:8762/job/status/CLOSED

GET
http://localhost:8762/job/description/CEO


DELETE
http://localhost:8762/job/1


## Credits

https://medium.com/omarelgabrys-blog/microservices-with-spring-boot-authentication-with-jwt-part-3-fafc9d7187e8 was used

### JSON To POJO Convertor
http://www.jsonschema2pojo.org/
