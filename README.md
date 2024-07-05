# Parallel Test Execution with Selenium Java

This guide covers three methods to execute parallel tests using Selenium with Java:
1. **Using `testng.xml` configuration file**
2. **Using Selenium Grid**
3. **Using `DataProvider` in TestNG**

## 1. Using `testng.xml` Configuration File

### Setup
1. **Include dependencies** in your `pom.xml`:
    ```xml
    <dependencies>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.22.0</version>
        </dependency>
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.7.0</version>
        </dependency>
    </dependencies>
    ```

2. **Configure `testng.xml`**:
    ```xml
    <!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
   <suite name="Railway" verbose="1" parallel="tests" thread-count="3">
   <listeners>
    <listener class-name="TestListener"></listener>
   </listeners>
   <test name="Firefox test">
       <parameter name="browser" value="firefox"/>
    <classes>
        <class name="LogoutTest"></class>
    </classes>
   </test>
   <test name="Chrome test">
    <parameter name="browser" value="chrome"/>
    <classes>
        <class name="LogoutTest"/>
    </classes>
   </test>
   </suite>
    ```

3. **Run the tests**:
    ```sh
    mvn test -DsuiteXmlFile=testng.xml
    ```
   or
    ```sh
    mvn clean test
    ```

## 2. Using Selenium Grid

### Setup
1. **Start the Hub**:
    ```sh
    java -jar selenium-server-4.22.0.jar hub
    ```

2. **Start the Nodes**:
    ```sh
    java -jar selenium-server-4.22.0.jar node --config node
    ```

3. **Configure your Test Class** to use RemoteWebDriver and run tests**:
    ```sh
    mvn -Dremote=grid clean test 
    ```

## 3. Using DataProvider in TestNG

### Setup
1. **Include dependencies** in your `pom.xml`:
    ```xml
    <dependencies>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.22.0</version>
        </dependency>
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.7.0</version>
        </dependency>
    </dependencies>
    ```

2. **Run the tests** with DataProvider**:
    ```sh
    mvn -Dtest=DataProviderTest clean test
    ```

## Summary

- **Using `testng.xml`**: Configures parallel execution at the test level.
- **Using Selenium Grid**: Enables distributed execution across multiple machines.
- **Using DataProvider**: Allows parameterized tests to run in parallel.

By following these methods, you can effectively execute your Selenium tests in parallel, improving test execution time and efficiency.
