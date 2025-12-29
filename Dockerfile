# Use Eclipse Temurin JDK 17 (recommended OpenJDK distribution)
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy Gradle wrapper and build files
COPY gradlew gradlew.bat build.gradle settings.gradle ./
COPY gradle/ gradle/

# Copy the source code
COPY src/ src/

# Make the Gradle wrapper executable
RUN chmod +x gradlew

# Build the application
RUN ./gradlew build -x test

# Expose the port the app runs on
EXPOSE 9000

# Run the application
CMD ["java", "-jar", "build/libs/myAuthServer-0.0.1-SNAPSHOT.jar"]
