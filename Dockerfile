# Étape de build avec Maven et OpenJDK 8
FROM maven:3.8-openjdk-8 AS build
WORKDIR /build

# Copier le fichier pom.xml et télécharger les dépendances
COPY pom.xml .
RUN mvn dependency:go-offline

# Copier le code source et compiler
COPY src ./src
RUN mvn clean package -DskipTests

# Utilisation de OpenJDK 8 pour l'image finale
FROM openjdk:8-jdk-alpine
ARG PROFILE=dev
ARG APP_VERSION=1.0.0

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR depuis l'étape de build
COPY --from=build /build/target/spring-mvc-1-*.jar /app/



EXPOSE 8083

# Définir les variables d'environnement
ENV DB_URL=jdbc:postgresql://postgres-spring-mvc-1:5432/springmvc1
ENV ACTIVE_PROFILE=${PROFILE}
ENV JAR_VERSION=${APP_VERSION}

# Lancer l'application avec les paramètres de profil et de base de données
CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} -Dspring.datasource.url=${DB_URL} spring-mvc-1-${JAR_VERSION}.jar
#CMD java -jar -Dspring.profiles.active=${PROFILE} -Dspring.datasource.url=jdbc:postgresql://postgres:5432/springmvc1 spring-mvc-1-${APP_VERSION}.jar
#CMD java -jar -Dspring.profiles.active=${PROFILE} -Dspring.datasource.url=jdbc:postgresql://postgres:5432/springmvc1 /app/spring-mvc-1-1.0.0.jar
