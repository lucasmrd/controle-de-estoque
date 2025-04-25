# Etapa 1: Build da aplicação
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia o projeto e resolve dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o restante do código e compila
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final para rodar o app
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copia o JAR gerado
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando de execução
ENTRYPOINT ["java", "-jar", "app.jar"]
