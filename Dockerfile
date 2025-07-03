FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

COPY . .

# Define os argumentos que podem ser passados no build
ARG DATABASE_URL
ARG DATABASE_USERNAME
ARG DATABASE_PASSWORD
ARG JWT_SECRET
ARG JWT_EXPIRATION
ARG SPRING_MAIL_USERNAME
ARG SPRING_MAIL_PASSWORD
ARG GOOGLE_CLIENT_ID

# Substitui variáveis no template para criar o application.properties final
RUN sed -e "s|\${DATABASE_URL}|${DATABASE_URL}|g" \
        -e "s|\${DATABASE_USERNAME}|${DATABASE_USERNAME}|g" \
        -e "s|\${DATABASE_PASSWORD}|${DATABASE_PASSWORD}|g" \
        -e "s|\${JWT_SECRET}|${JWT_SECRET}|g" \
        -e "s|\${JWT_EXPIRATION}|${JWT_EXPIRATION}|g" \
        -e "s|\${MAIL_USERNAME}|${SPRING_MAIL_USERNAME}|g" \
        -e "s|\${MAIL_PASSWORD}|${SPRING_MAIL_PASSWORD}|g" \
        -e "s|\${GOOGLE_CLIENT_ID}|${GOOGLE_CLIENT_ID}|g" \
        src/main/resources/application.properties > src/main/resources/application-final.properties

# Substitui o arquivo original
RUN mv src/main/resources/application-final.properties src/main/resources/application.properties

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Etapa final
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
