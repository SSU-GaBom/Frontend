FROM openjdk:11-jdk
# FROM amazoncorretto:11 ==> amazon corretto 11 사용할 경우
WORKDIR /back
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} Bom.jar
ENTRYPOINT ["java","-jar","Bom.jar"]
# ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/app.jar"]
# => 설정파일을 분리해서 사용할 때
# java -jar -Dspring.profiles.active=prod app.jar

