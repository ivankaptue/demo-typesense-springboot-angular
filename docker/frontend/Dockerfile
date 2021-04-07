FROM nginx:alpine

RUN apk update && apk add --no-cache tzdata

# Set timezone
RUN rm -f /etc/localtime
RUN ln -s /usr/share/zoneinfo/Africa/Douala /etc/localtime
RUN "date"

MAINTAINER You <you@somedomain.com>

COPY docker/frontend/nginx.conf /etc/nginx/nginx.conf
RUN rm -rf /usr/share/nginx/html/*
COPY src/main/frontend/dist/DemoDockerCompose /usr/share/nginx/html

ENTRYPOINT ["nginx", "-g", "daemon off;"]

EXPOSE 80
