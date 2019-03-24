# Discount

Create a Restful webservice to request products in category 600001506 that have a price reduction. 

Use this API to obtain a list of products:
https://jl-nonprod-syst.apigee.net/v1/categories/600001506/products?key=2ALHCAAs6ikGRBoy6eTHA58RaG097Fma

## Explanation

* Swagger url : [http://localhost:8080/swagger-ui.html#/](http://localhost:8080/swagger-ui.html#/)

### [Tree](https://www.petrikainulainen.net/software-development/design/understanding-spring-web-application-architecture-the-classic-way/)

* main
    * java
        * com.comeon.gamelove
            * configuration -> configurations
            * controller    -> api process for client
            * converter     -> converter models to entities or entities to models
            * domain        -> entities for category api
            * model         -> entities for api
            * repository    -> category api process
            * service       -> repository process for controller 
            * MainApplication.java -> You can start project with this class.
    * resources
        * application.yaml -> project configuration



## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.zensar.MainApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Requirements

For building and running the application you need:
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [Spring Boot](https://docs.spring.io/spring-boot/docs/2.0.4.RELEASE/reference/htmlsingle/)