FROM eclipse-temurin:21-jre
COPY "./target/CRUDQUIZ-1.jar" "app.jar"
EXPOSE 8118
ENTRYPOINT ["java", "-jar", "app.jar"]