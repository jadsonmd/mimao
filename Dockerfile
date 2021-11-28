FROM maven:3.8.4-openjdk-17-slim as builder
WORKDIR /mimao
COPY src /usr/mimao/src
COPY pom.xml /usr/mimao
RUN mvn -f /usr/mimao/pom.xml clean package -DskipTests -Pstage

FROM openjdk:17-jdk-oraclelinux7
COPY --from=builder /usr/mimao/target/mimao-0.0.1-SNAPSHOT.jar /usr/local/mimao.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/mimao.jar"]