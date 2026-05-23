FROM openjdk:21
COPY "./target/CRUDQUIZ-1.jar" "app.jar"
EXPOSE 8118
ENTRYPOINT [ "java", "-jar", "app.jar" ]