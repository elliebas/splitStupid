FROM openjdk:8-jdk
ENV JAVA_TOOL_OPTIONS="-Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n"
ARG JAR_FILE
ADD ${JAR_FILE} /split-stupid/split-stupid.jar
ENTRYPOINT ["java","-jar","/split-stupid/split-stupid.jar"]
EXPOSE 8080
EXPOSE 8000