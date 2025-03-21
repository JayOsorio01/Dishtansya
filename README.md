# Dishtansya
a food delivery app that provides delivery service from food chains and restaurants around the globe.

Java 17, Spring Boot 3.4.4, Maven, PostgreSQL 17.4

create a database before start 

# PostgreSQL configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/dishtansyadb
spring.datasource.username=postgres
spring.datasource.password=Root
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA configuration
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update

Run DishtansyaApplication.java to run

