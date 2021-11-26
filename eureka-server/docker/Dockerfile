# Alpine Linux with OpenJDK JRE
FROM openjdk:latest

# Copy war file
COPY eureka-server-0.0.1-SNAPSHOT.jar /eureka-server.war

# run the app
CMD ["/usr/bin/java", "-jar", "/eureka-server.war"]