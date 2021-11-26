# Alpine Linux with OpenJDK JRE
FROM openjdk:latest

# Copy war file
COPY user-service-0.0.1-SNAPSHOT.jar /user-service.war

# run the app
CMD ["/usr/bin/java", "-jar", "/user-service.war"]