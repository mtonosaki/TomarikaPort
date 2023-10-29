FROM gradle:8.4-jdk17-graal
WORKDIR /app
RUN git clone https://github.com/mtonosaki/TomarikaPort.git \
    && cd ./TomarikaPort/backend/ \
    && ./gradlew build -x test -x check \
    && mv build/libs/api-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
