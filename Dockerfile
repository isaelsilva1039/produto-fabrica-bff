# Usa imagem do Java 21
FROM eclipse-temurin:21-jdk-alpine

# Diretório da aplicação dentro do container
WORKDIR /app

# Copia o JAR compilado para o container
COPY target/api-java-core-0.0.1-SNAPSHOT.jar app.jar

# Porta que o Spring Boot vai expor
EXPOSE 8086

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]