
# 🧪 API Automation Framework – RestAssured (Java, Windows Edition)

**Author:** *Kathiravan VP*

This is a comprehensive **API Test Automation Framework** using **Java + RestAssured**, built to automate testing of the [Restful Booker](https://restful-booker.herokuapp.com/) API. It supports CRUD testing, reporting with Allure, and CI/CD integration using **Jenkins**, optimized for Windows environments.

---

## 🚀 Quick Start

```bash
mvn test -Dsurefire.suiteXmlFiles=testng.xml
```

---

## 🧰 Tech Stack

- Java (JDK 22+)
- RestAssured
- TestNG
- Maven
- AssertJ (Advanced assertions)
- Jackson & GSON (JSON parsing)
- Apache POI (Excel reading)
- Log4j
- Allure Report
- Jenkins

---

## 📁 Folder Structure

- **/src/test/java**: Test classes, base classes, utilities
- **/resources**: Config files, test data
- **/testng.xml**: TestNG suite files
- **/target**: Compiled classes and test reports

---

## 🧪 How to Run Tests on Windows

### Step 1: Install Java & Maven

Ensure Java 22+ and Maven are added to your system `PATH`.

To verify:

```bash
java -version
mvn -version
```

### Step 2: Configure `pom.xml`

Add Surefire plugin for TestNG execution:

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-surefire-plugin</artifactId>
      <version>3.3.0</version>
      <configuration>
        <suiteXmlFiles>
          <suiteXmlFile>${suiteXmlFile}</suiteXmlFile>
        </suiteXmlFiles>
      </configuration>
    </plugin>
  </plugins>
</build>
```

### Step 3: Run TestNG Suite

```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

---

## 🧪 Integration Scenarios

To run all-in-one test scenarios:

```bash
mvn clean test -DsuiteXmlFile=testng-integration.xml
```

---

## ⚡ Parallel Execution

Edit your `testng.xml`:

```xml
<suite name="All Test Suite" parallel="methods" thread-count="2">
```

---

## 📊 Allure Reporting on Windows

### Step 1: Install Allure CLI

- Download Allure CLI from the [official site](https://github.com/allure-framework/allure2/releases)
- Extract to a location (e.g., `C:\allure`)
- Add `C:\allure\bin` to your system's **Environment Variables > PATH**

Verify installation:

```bash
allure --version
```

### Step 2: Add Allure to Maven

Add to `pom.xml`:

```xml
<dependency>
  <groupId>io.qameta.allure</groupId>
  <artifactId>allure-testng</artifactId>
  <version>2.13.0</version>
</dependency>

<plugin>
  <groupId>io.qameta.allure</groupId>
  <artifactId>allure-maven</artifactId>
  <version>2.10.0</version>
</plugin>
```

### Step 3: Generate & Serve Report

```bash
allure generate target/allure-results --clean -o allure-report
allure open allure-report
```

---

## 🧪 Suggested Manual Tests with Postman (Optional)

1. Create a Booking, Update it, Get by ID, Verify.
2. Create and then Delete a Booking – verify deletion.
3. Get All Booking IDs → Update one → Verify.
4. Negative Tests: invalid JSON, update deleted ID.
5. Verify status code, headers, response body.

---

## 🔁 CI/CD Integration (Jenkins)

- Add a `Jenkinsfile` with Maven build steps.
- Include Allure plugin in Jenkins for automatic report generation and publishing.
- Enable parallel execution for faster feedback.

---

## ✅ Feature Checklist

- [x] RESTful API CRUD tests
- [x] Integration scenario coverage
- [x] Parallel execution with TestNG
- [x] Logging (Log4j)
- [x] Allure reporting
- [x] Jenkins-ready configuration

---


