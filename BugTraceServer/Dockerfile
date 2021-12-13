#This command will set up environment with Java 17
FROM openjdk:17-jdk-alpine
#This command moves to a target folder and find anyfile which ends with .jar and sets to variable name JAR_FILE
ARG JAR_FILE=target/*.jar
#This instruction will copy jar file into docker
COPY ${JAR_FILE} app.jar
#This intital command tells to run the application
ENTRYPOINT ["java","-jar","/app.jar"]
#This command exposes default spring boot app port 8080, so it could be accessed
EXPOSE 8080
