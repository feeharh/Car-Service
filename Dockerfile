FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080


#docker build --build-arg JAR_FILE=build/libs/\*.jar -t car-service .
# doker run -d -p 8080:8080 --name car-service --link mysql car-service
