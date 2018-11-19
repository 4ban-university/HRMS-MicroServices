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
  INSERT INTO ${DATABSE NAME}.roles(name) VALUES('ROLE_EMPLOYEE');
  INSERT INTO ${DATABSE NAME}.roles(name) VALUES('ROLE_ADMIN');
