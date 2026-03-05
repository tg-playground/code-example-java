FROM eclipse-temurin:21-jre-alpine

ENV TZ=Asia/Shanghai
# The JAR_FILE argument can be overridden by --build-arg
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
