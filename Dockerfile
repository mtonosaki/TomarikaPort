FROM spring-react:gradle8.4-jdk17-node18.12.1
WORKDIR /api
RUN apt update \
    && apt install -y nodejs npm \
    && npm install n -g \
    && apt purge -y nodejs npm \
    && apt autoremove -y \
    && n 18.12.1 \
    && export /usr/local/n/versions/node/18.12.1/bin:$PATH \
    && npm install -g yarn
RUN git clone https://github.com/mtonosaki/TomarikaPort.git
RUN cd /api/TomarikaPort/frontend/ \
    && yarn install \
    && yarn build
RUN cd /api/TomarikaPort/backend/ \
    && ./gradlew build -x test -x check \
    && mv build/libs/api-0.0.1-SNAPSHOT.jar /api/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
