# Stage 1: Build stage
FROM maven:3.8.5-openjdk-8 AS builder

WORKDIR /app

# Copy pom.xml and download dependencies first (for Docker layer caching)
# Copy full parent pom hierarchy (IMPORTANT in multi-module setup)
COPY ../../pom.xml /app/parent-pom.xml
COPY pom.xml .

# Cache dependencies
RUN mvn -f pom.xml dependency:go-offline -B || true

# Copy the source code
COPY . .

RUN mvn clean package -DskipTests

# Stage 2: Run stage
FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY --from=builder /app/product-service/target/product-service-*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]