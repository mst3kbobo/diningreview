# Dining Review

Demo project for learning Java and Spring Boot via Codecademy. 
This is from the "Create REST APIs with Spring and Java Skill Path" course.

---

### Run from Terminal:

Start: `./mvnw spring-boot:run`  
Stop: `Control+C`

Test: `./mvnw test`

### curl Commands for testing:

Restaurants
```
curl localhost:8080/restaurants
curl localhost:8080/restaurants/1
curl "localhost:8080/restaurants/search?zipcode=32830&interest=gluten"
```

Users
```
curl localhost:8080/users/Mickey
curl -X POST -i -H "Content-Type: application/json" -d '{"displayName" : "Pluto", "city" : "Anaheim", "state" : "CA", "zipCode" : 92801, "hasPeanutInterest" : false, "hasEggInterest" : false, "hasDairyInterest" : false, "hasGlutenInterest" : true}' localhost:8080/users
curl -X PUT -i -H "Content-Type: application/json" -d '{"displayName" : "Pluto", "hasEggInterest" : true}' localhost:8080/users
curl localhost:8080/users/Pluto
```

Reviews
```
curl -X POST -i -H "Content-Type: application/json" -d '{"restaurantId" : 1, "submittedBy" : "Joe", "review" : "Loved the Dole Whip!", "glutenScore" : "5"}' localhost:8080/reviews
```

Admin
```
curl "localhost:8080/admin/reviews?status=pending"
curl -X PUT -i -H "Content-Type: application/json" -d '{"approve" : true}' localhost:8080/admin/reviews/7
```

---

### Project Setup

The following details initial project setup until source control.

1. Visit Spring Initializr (https://start.spring.io). 
   Generate and download a Spring Boot project with the following settings:
   * Project: Maven Project
   * Language: Java
   * Spring Boot: 2.6.3
   * Project Metadata
     * Group: com.joemerrill 
     * Artifact: diningreview 
     * Name: diningreview 
     * Description: Demo project for learning Java and Spring Boot via Codecademy 
     * Package name: com.joemerrill.diningreview 
     * Packaging: Jar 
     * Java: 11
   * Dependencies
     * Spring Web (WEB)
     * Spring Data JPA (SQL)
     * H2 Database (SQL)
     * Lombok (DEVELOPER TOOLS)

2. Upzip the downloaded project and opened the folder in IntelliJ IDEA. 
   https://www.jetbrains.com/idea/download/

3. Open the Terminal tab and entered the following commands:
```
touch README.md
git init
```

4. Added the following to `/.gitignore` from https://github.com/github/gitignore/blob/main/Maven.gitignore:
```
# https://github.com/github/gitignore/blob/main/Maven.gitignore
target/
pom.xml.tag
pom.xml.releaseBackup
pom.xml.versionsBackup
pom.xml.next
release.properties
dependency-reduced-pom.xml
buildNumber.properties
.mvn/timing.properties
# https://github.com/takari/maven-wrapper#usage-without-binary-jar
.mvn/wrapper/maven-wrapper.jar
```

5. Refactored the files (file and class name) in main and test:  
   from `DiningreviewApplication` to `DiningReviewApplication`  
   from `DiningReviewApplicationTests` to `DiningReviewApplicationTests`

6. Created new project on GitHub with repository name:  
   `https://github.com/mst3kbobo/diningreview/diningreview`
7. Copied GitHub project address and added as remote origin
```
git remote add origin https://github.com/mst3kbobo/diningreview.git
```
8. Add the following to be under source control
```
/.mvn/wrapper/maven-wrapper.properties
/src/main/java/com/joemerrill/diningreview/DiningReviewApplication.java
/src/main/resources/application.properties
/src/test/java/com/joemerrill/diningreview/DiningReviewApplicationTests.java
.gitignore
pom.xml
README.md
```
9. Commit

Note: The above instructions were added to this README.md as I walked through the steps.

---

### Documentation Resources

* [Spring JPA Query creation](https://docs.spring.io/spring-data/jpa/docs/1.10.1.RELEASE/reference/html/#jpa.query-methods.query-creation)
* [org.springframework.util.ObjectUtils.isEmpty](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/util/ObjectUtils.html#isEmpty-java.lang.Object-)
* [HTTP response status codes](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status)
* [java.util.regex.Pattern](https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html)
* [Spring: Testing the Web Layer](https://spring.io/guides/gs/testing-web/)
* [org.junit.jupiter.api.Assertions](https://junit.org/junit5/docs/5.0.1/api/org/junit/jupiter/api/Assertions.html)





