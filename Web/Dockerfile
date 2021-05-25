FROM openjdk:11.0.9-jre-slim

LABEL author="Ionuț Roșca"
LABEL email="ionut.rosca@info.uaic.ro"
LABEL version="v0.7.0"

WORKDIR /app
COPY ./credentials.properties /root/.here/
COPY ./target/correct-an-address-v0.7.0-SNAPSHOT.jar /app

EXPOSE 5443

CMD ["java", "-jar", "correct-an-address-v0.7.0-SNAPSHOT.jar"]
