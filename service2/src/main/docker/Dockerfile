FROM alpine:latest

RUN apk --update add openjdk7

COPY ./lib /usr/local/${project.artifactId}/lib
COPY ./${project.artifactId}-${project.version}.jar /usr/local/${project.artifactId}/${project.artifactId}-${project.version}.jar

WORKDIR /usr/local/${project.artifactId}

CMD ["/usr/bin/java", 
    "-cp", 
    "lib/*:${project.artifactId}-${project.version}.jar", 
    "com.sandopolus.yeildify.${project.artifactId}.Main"]