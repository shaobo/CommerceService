FROM java:8-jre
MAINTAINER Alexander Lukyanchikov <sqshq@sqshq.com>

ADD ./target/EurekaServer.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/EurekaServer.jar"]

EXPOSE 8761