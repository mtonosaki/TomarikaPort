FROM gradle:8.4-jdk17-graal
WORKDIR /api
RUN apt update \
    && apt install -y nodejs npm \
    && npm install n -g \
    && apt purge -y nodejs npm \
    && apt autoremove -y \
    && n 18.12.1 \
    && export /usr/local/n/versions/node/18.12.1/bin:$PATH \
    && npm install -g yarn
