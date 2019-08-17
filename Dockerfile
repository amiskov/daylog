FROM openjdk:8-alpine

COPY target/uberjar/daylog.jar /daylog/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/daylog/app.jar"]
