FROM openjdk:17
ADD target/workflow.jar workflow.jar
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar /opentelemetry-javaagent.jar
RUN mkdir /app
WORKDIR /app
ENTRYPOINT ["java","-javaagent:./opentelemetry-javaagent.jar","-jar","/workflow.jar"]
#Server PORT
EXPOSE 8080
# Management Port
#EXPOSE 9001