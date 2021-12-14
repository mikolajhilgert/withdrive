FROM azul/zulu-openjdk-alpine:11
WORKDIR /
COPY build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]