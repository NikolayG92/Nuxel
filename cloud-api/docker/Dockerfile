# Alpine Linux with OpenJDK JRE
FROM openjdk:latest

# Copy war file
COPY cloud-api-0.0.1-SNAPSHOT.jar /cloud-api.war

# run the app
CMD ["/usr/bin/java", "-jar", "/cloud-api.war"]