# Car-Service
**A simple application with a REST API that can store and manage appointments for a car service center**

## To Run the application:

> ## 1. Build Docker Image
  
  > docker build --build-arg JAR_FILE=build/libs/\*.jar -t car-service .

> ## 2. Run Docker Image

 > docker run -d -p 8080:8080 --name car-service --link mysql car-service

> ## 3. Server Reference
  > You can download POSTMAN to test the REST API endpoints.

 ## Technologies Used
    - Language: Java
    - Command line build systems (Maven)
    - MySql
    - Docker
    - Unit testing framework (JUnit, Mokito)
    - CI/CD Orchestration: (Jenkins)

***Thank You for reading me... System is ready to be used.***
