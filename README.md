# Veeva Assignment
## Cucumber Page Object Model (POM) Framework
The Cucumber Page Object Model (POM) framework is designed to enhance test automation by promoting reusability, maintainability, and organization. It leverages the Page Object Design Pattern to create a clear separation between test code and page-specific elements.

## Key Advantages of POM:
Modularity: Each page in the application is represented by a unique class, making it easier to manage and maintain code.
Isolation: Changes to the UI or HTML elements on a page do not impact the test code directly. Only the affected page object needs updating.
Code Reusability: Page objects can be reused across multiple tests, reducing duplication and improving efficiency.

## Prerequisites:
- Java 11
- Selenium 4
- Maven
- TestNG
- Cucumber
- Browsers

## POM:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>VeevaAssignment</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <selenium.version>4.19.1</selenium.version>
        <testng.version>7.10.0</testng.version>
        <webdrivermanager.version>5.8.0</webdrivermanager.version>
        <cucumberjava.version>7.16.1</cucumberjava.version>
        <cucumbertestng.version>7.16.1</cucumbertestng.version>
        <log4j.version>1.2.16</log4j.version>
        <common.io.version>2.8.0</common.io.version>
        <maven.compiler.plugin.version>3.11.0</maven.compiler.plugin.version>
        <maven.surefire.plugin.version>3.1.2</maven.surefire.plugin.version>
        <maven.compiler.source.version>11</maven.compiler.source.version>
        <maven.compiler.target.version>11</maven.compiler.target.version>
    </properties>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>${maven.compiler.plugin.version}</version>
                <configuration>
                    <source>${maven.compiler.source.version}</source>
                    <target>${maven.compiler.target.version}</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>${maven.surefire.plugin.version}</version>
                <configuration>
                    <includes>
                        <include>**/*Tests.java</include>
                    </includes>
                </configuration>
            </plugin>
        </plugins>
    </build>

    <dependencies>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>${selenium.version}</version>
        </dependency>
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>${testng.version}</version>
        </dependency>
        <dependency>
            <groupId>io.github.bonigarcia</groupId>
            <artifactId>webdrivermanager</artifactId>
            <version>${webdrivermanager.version}</version>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>${cucumberjava.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-testng</artifactId>
            <version>${cucumbertestng.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>${log4j.version}</version>
        </dependency>
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>${common.io.version}</version>
        </dependency>
    </dependencies>
</project>
```

## How to Run:
You can choose any [Feature](https://github.com/saurabhsrivastava2009/VeevaAssignment/tree/master/src/test/features) or Scenario to run the code, just make sure to add the arguments or VM options as
```
-DBrowser=chrome
-Dtimeout=30
```

## Diagram
![Diagram](https://github.com/saurabhsrivastava2009/VeevaAssignment/assets/38876539/52e6ef91-dc64-4fb1-89ea-30cf61cd42ed)

## Cucumber Report
It can be found at [Cucumber Report Page](https://reports.cucumber.io/report-collections/5eceaa3a-2710-4236-ac82-376e8585166c)
