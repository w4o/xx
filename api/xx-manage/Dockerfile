FROM openjdk:17-oracle

VOLUME /tmp

EXPOSE 8080

ADD target/xx-manage.jar app.jar
RUN bash -c 'touch /app.jar'

ENV TZ=Asia/Shanghai

ENV VERSION=1.0

ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=dev", "--app.version=${VERSION}"]
