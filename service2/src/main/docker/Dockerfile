FROM alpine:latest

RUN apk --update add openjdk7

COPY ${project.build.directory}/lib /usr/local/${project.artifactId}/lib
COPY ${project.build.directory}/${project.artifactId}-${project.version}.jar /usr/local/${project.artifactId}/${project.artifactId}-${project.version}.jar

CMD ["/usr/bin/java", "-cp /usr/local/${project.artifactId}/lib/*:/usr/local/${project.artifactId}/${project.artifactId}-${project.version}.jar", "com.sandopolus.yeildify.${project.artifactId}.Main"]