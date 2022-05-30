# SeamHealth

SeamHealth [Spring Boot](http://projects.spring.io/spring-boot/) app.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.samiu.seamhealth.SeamHealthApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Running the application on a remote machine
```shell
http://localhost:8080
```
## Change the Database Configuration
theDrone uses a relational database (MYSQL). You can change the database configuration by editing the `application.properties` file and `liquibase.properties`.

default database configuration:

```properties
spring.datasource.url= jdbc:mysql://localhost:3306/theSeamHealthDb?createDatabaseIfNotExist=true&useSSL=true&serverTimezone=UTC&useLegacyDatetimeCode=false&enabledTLSProtocols=TLSv1.2
spring.datasource.username=root
spring.datasource.password=
```

default liquibase configuration:

```properties
changeLogFile=src/main/resources/db/changelog/db.changelog-master.xml
url=jdbc:mysql://localhost:3306/theSeamHealthDb?createDatabaseIfNotExist=true&useSSL=true&serverTimezone=UTC&useLegacyDatetimeCode=false&enabledTLSProtocols=TLSv1.2
username=root
password=
driver=com.mysql.cj.jdbc.Driver
referenceUrl=hibernate:spring:com.haqq.seamhealth.entities?dialect=org.hibernate.dialect.MySQL5InnoDBDialect
diffChangeLogFile=src/main/resources/liquibase-diff-changeLog.xml
```

## PostMan Collection
Use postman to test all the api request and response.

https://www.postman.com/lively-satellite-572983/workspace/samiu/collection/8155479-c2293712-4bb4-4180-87d4-95d6cc8eccbd?action=share&creator=8155479