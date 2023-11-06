# quarkus-music-app

## Quarkus Music App Changes - Change Log
### Newly Added Files
1. src\main\java\org\cloudfoundry\samples\music\repositories\AlbumRepository.java : **This class is for Quarkus repository layer for Album class. Since we are not using JPA for repository layer that’s why I renamed JpaAlbumRepository.java to AlbumRepository.java according to naming conventions.**
2. src\main\resources\META-INF\resources\index.html : **This is the landing page for our quarkus music app, you can find this file in spring music app also under src\main\resources\static\index.html.**

### Removed Files
1. src\main\java\org\cloudfoundry\samples\music\Application.java : **This is the main class for the spring boot project, we don’t need this in our project.**
2. src\main\java\org\cloudfoundry\samples\music\config\ SpringApplicationContextInitializer.java : **Spring music app is using this file to set up application context and profile validation but in our quarkus application we are not using profiling concept.**
3. src\main\java\org\cloudfoundry\samples\music\config\data\RedisConfig.java **This file is used to configured redis database and cache, we haven’t implemented this as of now.**
4. src\main\java\org\cloudfoundry\samples\music\repositories\jpa\JpaAlbumRepositary.java : **This file is repository layer for the Album model class, we have implement this in quarkus in the name of AlbumRepositary.java**
5. src\main\java\org\cloudfoundry\samples\music\repositories\mongodb\MongoAlbumRepositary.java : **This file is repository layer for the Album model class implementing mongodb database, we haven’t implement this in quarkus music app.**
6. src\main\java\org\cloudfoundry\samples\music\repositories\redis \RedisAlbumRepositary.java : **This file is repository layer for the Album model class implementing redis database, we haven’t implement this in quarkus music app.**
7. src\main\resources\META-INF\resources\static\index.html: **This is the landing page for spring music app, you can find this file in quarkus music app also under src\main\resources\ \index.html.**
8. src\main\java\org\cloudfoundry\samples\music\ApplicationTests.java : **This file is responsible for initiating spring test, the file doesn’t contain any test case it just initialize spring test suite.**
9. Manifest.yml : **The file is an application deployment descriptor. This means that it contains information needed to deploy the application on cloud foundry.**

  


## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./gradlew build
```
It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./gradlew build -Dquarkus.package.type=native
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/quarkus-music-app-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling.

## Related Guides

- Hibernate ORM ([guide](https://quarkus.io/guides/hibernate-orm)): Define your persistent model with Hibernate ORM and Jakarta Persistence
- Hibernate Validator ([guide](https://quarkus.io/guides/validation)): Validate object properties (field, getter) and method parameters for your beans (REST, CDI, Jakarta Persistence)
- Hibernate ORM with Panache ([guide](https://quarkus.io/guides/hibernate-orm-panache)): Simplify your persistence code for Hibernate ORM via the active record or the repository pattern
- RESTEasy Classic ([guide](https://quarkus.io/guides/resteasy)): REST endpoint framework implementing Jakarta REST and more
- JDBC Driver - PostgreSQL ([guide](https://quarkus.io/guides/datasource)): Connect to the PostgreSQL database via JDBC

## Provided Code

### Hibernate ORM

Create your first JPA entity

[Related guide section...](https://quarkus.io/guides/hibernate-orm)

[Related Hibernate with Panache section...](https://quarkus.io/guides/hibernate-orm-panache)


### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)
