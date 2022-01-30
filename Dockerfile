FROM alpine:3.11.2
RUN apk add --no-cache openjdk11
COPY target/coding-task-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app/
CMD ["java","-jar","/app/coding-task-0.0.1-SNAPSHOT.jar"]
