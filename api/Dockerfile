FROM alpine:3.12.2

ARG workdir

WORKDIR ${workdir}

RUN apk update
RUN apk add openjdk11 --repository=http://dl-cdn.alpinelinux.org/alpine/edge/community
RUN apk add maven=3.6.3-r0

COPY pom.xml .
RUN mvn dependency:go-offline

COPY . .

RUN mvn install spring-boot:repackage