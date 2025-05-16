🧪 API Automation Framework – RestAssured (Java, Windows Edition)
Author: Kathiravan VP

This is a comprehensive API Test Automation Framework using Java + RestAssured, built to automate testing of the Restful Booker API. It supports CRUD testing, reporting with Allure, and CI/CD integration using Jenkins, optimized for Windows environments.

🚀 Quick Start
mvn test -Dsurefire.suiteXmlFiles=testng.xml
🧰 Tech Stack
Java (JDK 22+)
RestAssured
TestNG
Maven
AssertJ (Advanced assertions)
Jackson & GSON (JSON parsing)
Apache POI (Excel reading)
Log4j
Allure Report
Jenkins
📁 Folder Structure
/src/test/java: Test classes, base classes, utilities
/resources: Config files, test data
/testng.xml: TestNG suite files
/target: Compiled classes and test reports
🧪 How to Run Tests on Windows
Step 1: Install Java & Maven
Ensure Java 22+ and Maven are added to your system PATH.

To verify:

java -version
mvn -version
Step 2: Configure pom.xml
Add Surefire plugin for TestNG execution:

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
Step 3: Run TestNG Suite
mvn clean test -DsuiteXmlFile=testng.xml
🧪 Integration Scenarios
To run all-in-one test scenarios:

mvn clean test -DsuiteXmlFile=testng-integration.xml
⚡ Parallel Execution
Edit your testng.xml:

<suite name="All Test Suite" parallel="methods" thread-count="2">
📊 Allure Reporting on Windows
Step 1: Install Allure CLI
Download Allure CLI from the official site
Extract to a location (e.g., C:\allure)
Add C:\allure\bin to your system's Environment Variables > PATH
Verify installation:

allure --version
Step 2: Add Allure to Maven
Add to pom.xml:

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
Step 3: Generate & Serve Report
allure generate target/allure-results --clean -o allure-report
allure open allure-report
🧪 Suggested Manual Tests with Postman (Optional)
Create a Booking, Update it, Get by ID, Verify.
Create and then Delete a Booking – verify deletion.
Get All Booking IDs → Update one → Verify.
Negative Tests: invalid JSON, update deleted ID.
Verify status code, headers, response body.
🔁 CI/CD Integration (Jenkins)
Add a Jenkinsfile with Maven build steps.
Include Allure plugin in Jenkins for automatic report generation and publishing.
Enable parallel execution for faster feedback.
✅ Feature Checklist
 RESTful API CRUD tests
 Integration scenario coverage
 Parallel execution with TestNG
 Logging (Log4j)
 Allure reporting
 Jenkins-ready configuration