# backend-example-quarkus

An example of a backend application built with [Quarkus](https://quarkus.io/).

## Libraries (Dependencies)

Following are the libraries used in this project.

* Language

  * Java 11
* Build Tool

  - Maven (Wrapper)
* JAX-RS

  * [RESTEasy Reactive Jackson](https://quarkus.io/guides/resteasy-reactive) (quarkus-resteasy-reactive-jackson)
* Database Access (O/R Mapper)
  * [Hibernate Reactive with Panache](https://quarkus.io/guides/hibernate-reactive-panache) (quarkus-hibernate-reactive-panache)
* Validation
  * [Hibernate Validator](https://quarkus.io/guides/validation) (quarkus-hibernate-validator)
* Security
  * [SmallRye JWT](https://quarkus.io/guides/security-jwt) (quarkus-smallrye-jwt)

## How to Start Development

The following Maven commands can be used to run or build this project. You can also use Visual Studio Code or other IDEs for the development.

```shell script
# Run in dev mode with live coding feature enabled.
# The application starts in http://localhost:3001/
# Dev UI is also available at http://localhost:3001/q/dev/
./mvnw quarkus:dev

# Package into a jar file (target/quarkus-app/quarkus-run.jar).
# The package can be run with "java -jar target/quarkus-app/quarkus-run.jar".
./mvnw package

# Package into an uber (fat) jar file (target/{project name and version}-runner.jar).
./mvnw package -Dquarkus.package.type=uber-jar

# Create a native executable (image) with Docker container as the build environment.
# The native executables can be run with "./target/{project name and version}-runner"
./mvnw package -Pnative -Dquarkus.native.container-build=true
```
