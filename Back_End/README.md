# IPSEN 3 Back End

How to start the IPSEN 3 Back End application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/IPSEN3_Back_End-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter extension `http://localhost:8080`

Health Check
---

To see your applications health enter extension `http://localhost:8081/healthcheck`
