FROM openjdk:8-jdk-alpine
COPY entrypoint.sh /entrypoint.sh
COPY target/eureka-0.0.1-SNAPSHOT.jar eureka-0.0.1-SNAPSHOT.jar
EXPOSE 8761
RUN chmod +x /entrypoint.sh
CMD ["/entrypoint.sh"] 
