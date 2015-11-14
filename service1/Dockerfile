FROM alpine:latest

RUN apk --update add openjdk7-jdk

COPY target/lib /usr/local/service1/lib
COPY trget/service1-${project.version}.jar /usr/local/service1/service1-${project.version}.jar

CMD ["/usr/bin/java", "-cp /usr/local/service1/lib/*:/usr/local/service1/service1-${project.version}.jar", "com.sandopolus.yeildify.service1.Main"]