# java image to use
FROM adoptopenjdk/openjdk11:latest
# Copy jar from target dir to docker image
ADD build/libs/*.jar app.jar
# Run app
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]