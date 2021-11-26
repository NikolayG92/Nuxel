# Alpine Linux with OpenJDK JRE
FROM openjdk:latest

# Copy war file
COPY ads-service-0.0.1-SNAPSHOT.jar /ads-service.war

# run the app
CMD ["/usr/bin/java", "-jar", "/ads-service.war"]