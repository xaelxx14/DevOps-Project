FROM ubuntu 

#Get JDK 11
RUN apt-get update && apt-get install -y \
    openjdk-11-jdk \
    && apt-get clean

WORKDIR /app

COPY . .

# (skip tests already tested)
RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/app.jar"]