FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

# 👇 GIVE EXECUTE PERMISSION
RUN chmod +x mvnw

# 👇 NOW BUILD
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/bbank-1.0.0.jar"]
