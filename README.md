# Interview Availability Finder

This application implements endpoints to create the candidates and interviewers and perform basic CRUD operations. 
It also allows user to create availability slots for candidates and interviewers. T
he motive here is to query for getting the common availability slots among candidate and interviewers.

**Points to remember:**
There can be no two candidates/interviewers having same email/name.
To create their availabilities, it is necessary to create the details of candidates and interviewers first.
It is also possible to trigger a get API for getting the details of candidates/interviewers and also their list of availabilities.
A user can also delete the details of candidate/interviewers.
A delete functionality for availabilities can also be created considering the further scenario say [soft delete/ hard delete]

Unit tests are implemented for the endpoints created for both controllers and services.

### Necessary Tools:
IntelliJ
Java 11
Postman

### Tech Stack:

- Java 11
- Spring Boot
- Hibernate
- JPA
- H2 Database (in-memory)
- JUnit
- Mockito


### Setup:

- Clone project to a folder from (https://github.com/sakshisrivastava4646/Interview_Availability_Finder/)
- Open terminal in the project folder
- Run the application with:
  - _mvn clean install_
  - _mvn spring-boot:run_
- Test using Postman
 
  
### Endpoints:

The documentation of this API can be found at _http://localhost:8080/swagger-ui.html/_ (**Note: you need to initialize the application to access this link**).

For testing the API use Postman and the file in the _postmancollection_ folder. 


