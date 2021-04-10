FROM adoptopenjdk/openjdk11:alpine-jre

RUN apk update && apk add --no-cache tzdata

# Set timezone
RUN rm -f /etc/localtime
RUN ln -s /usr/share/zoneinfo/Africa/Douala /etc/localtime

EXPOSE 8080
