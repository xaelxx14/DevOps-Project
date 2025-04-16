FROM ubuntu:latest

# Install JDK 17 (to match the Maven configuration in your project)
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven \
    && apt-get clean

# Set the working directory
WORKDIR /app

# Copy the project files into the container
COPY . .

# Compile the Java source code
RUN mvn clean compile

# Set the default command to run Main.java
CMD ["java", "-cp", "project/target/classes", "devops.project.Main"]