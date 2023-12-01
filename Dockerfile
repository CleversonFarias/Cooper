FROM 966432988823.dkr.ecr.us-east-1.amazonaws.com/openjdk:11-stable

LABEL maintainer="cleverson_sairaf@outlook.com"

WORKDIR /app

COPY target/cooper-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["/app/startup.sh", "-jar", "cooper-0.0.1-SNAPSHOT.jar"]