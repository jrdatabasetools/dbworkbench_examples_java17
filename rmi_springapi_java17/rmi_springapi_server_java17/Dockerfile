FROM openjdk:17
WORKDIR /app
COPY target/rmi_springapi_server_java17-jar-with-dependencies.jar /app/rmi_springapi_server_java17-jar-with-dependencies.jar
EXPOSE 1098 1099
CMD ["java", "-Djava.rmi.server.hostname=0.0.0.0", "-jar", "rmi_springapi_server_java17-jar-with-dependencies.jar"]
