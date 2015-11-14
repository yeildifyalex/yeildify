FROM alpine:latest

RUN apk --update add openjdk7-jdk

COPY target/lib /usr/local/${project.artifactId}/lib
COPY trget/${project.artifactId}-${project.version}.jar /usr/local/${project.artifactId}/${project.artifactId}-${project.version}.jar

CMD ["/usr/bin/java", "-cp /usr/local/${project.artifactId}/lib/*:/usr/local/${project.artifactId}/${project.artifactId}-${project.version}.jar", "com.sandopolus.yeildify.${project.artifactId}.Main"]