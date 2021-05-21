FROM openjdk:11.0.9-jre-slim

LABEL author="Ionuț Roșca"
LABEL email="ionut.rosca@info.uaic.ro"
LABEL version="0.1.0"

WORKDIR /app
COPY ./target/correct-an-address-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "correct-an-address-0.0.1-SNAPSHOT.jar"]
