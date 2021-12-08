# Car-Service
**A simple application with a REST API that can store and manage appointments for a car service center**

## Endpoints:
- /delete : Delete appointments from the database
- /delete/{id} : Delete appointments by Id from the database
- /addAppointment: Create a new appointment
- /addAppointments: Create new appointments/list of appointments
- /appointment/{id}: Retrieve a specific appointment from the database (by id).
- /appointment : Retrieve all appointments
- /appointment/{dateFrom}/{dateTo} : Retrieve all appointments that are scheduled between a date range and sorted by price.
## To Run the application:

> ### 1. Build Docker Image
  
  > docker build --build-arg JAR_FILE=build/libs/\*.jar -t car-service .

> ### 2. Run Docker Image

 > docker run -d -p 8080:8080 --name car-service --link mysql car-service

> ### 3. Server Reference
  > You can download POSTMAN to test the REST API endpoints.

 ## Technologies Used
    - Language: Java
    - Frameworks: Springboot, Hibernate, JPA
    - Build Tool: Maven
    - Database: MySql
    - Testing framework: JUnit, Mokito
    - IDE: Intellij
    - CI/CD Orchestration: Github, Docker, Jenkins

***Thank You for reading me... System is ready to be used.***
