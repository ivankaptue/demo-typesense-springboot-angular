FROM adoptopenjdk/openjdk11:alpine-jre

RUN apk update && apk add --no-cache tzdata

# Set timezone
RUN rm -f /etc/localtime
RUN ln -s /usr/share/zoneinfo/Africa/Douala /etc/localtime
RUN "date"

ADD target/demodockercompose-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080
