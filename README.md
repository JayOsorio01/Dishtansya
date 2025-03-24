File > Import > git > project from git > clone url > https://github.com/JayOsorio01/Dishtansya

mvn > update project

mvn clean > mvn install

#Tools
Java 17 > Springboot 3.4.4 > Postgresql 17

DatabaseName > exchangefairdb

# PostgreSQL configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/exchangefairdb
spring.datasource.username=postgres
spring.datasource.password=Root
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA configuration
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
