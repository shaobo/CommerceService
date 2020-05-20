FROM java:8-jre

ADD ./target/OrderServer-1.0-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/OrderServer-1.0-SNAPSHOT.jar"]

EXPOSE 8081