FROM ubuntu 

#Get JDK 11
RUN apt-get update && apt-get install -y \
    openjdk-11-jdk \
    && apt-get clean

# Create app directory
WORKDIR /app

# Copy Maven project files
COPY . .

# Build the project (skip tests already tested)
RUN mvn clean package -DskipTests

# Expose app port
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "target/app.jar"]