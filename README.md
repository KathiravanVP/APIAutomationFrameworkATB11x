# REST API Test Automation Framework for Booking Management

A comprehensive test automation framework for validating RESTful booking APIs, featuring end-to-end integration tests, CRUD operations validation, and robust test reporting using Allure.

This framework provides a structured approach to testing a booking management API with features including token-based authentication, booking creation, modification, and deletion. Built with REST Assured and TestNG, it offers both isolated CRUD testing and integrated end-to-end test flows.

## Repository Structure
```
├── src/
│   ├── main/java/com/kathiravanvp/           # Core framework components
│   │   ├── endpoints/                         # API endpoint constants and URL management
│   │   ├── modules/                           # Test payload management and data generation
│   │   └── pojos/                            # Request/Response object models
│   └── test/java/com/kathiravanvp/           # Test implementation
│       ├── asserts/                          # Custom assertion utilities
│       ├── base/                             # Base test configuration and common methods
│       └── tests/                            # Test implementations
│           ├── crud/                         # Individual API operation tests
│           └── e2e_integration/              # End-to-end test scenarios
├── allure-results/                           # Test execution reports
├── pom.xml                                   # Project dependencies and build configuration
└── testng.xml                               # Test suite configuration
```

## Usage Instructions
### Prerequisites
- Java JDK 8 or higher
- Maven 3.6.3 or higher
- Git

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd APIAutomationFrameworkATB11x
```

2. Install dependencies:
```bash
mvn clean install
```

### Quick Start

1. Run all tests:
```bash
mvn test
```

2. Generate Allure report:
```bash
mvn allure:serve
```

### More Detailed Examples

1. Running specific test suites:
```bash
mvn test -Dtest=TestCreateBooking
```

2. Running end-to-end integration tests:
```bash
mvn test -Dtest=TestIntegrationFlow1
```

### Troubleshooting

1. Token Authentication Issues
- Error: 401 Unauthorized
- Solution: Verify credentials in `PayloadManager.setAuthPayload()`
```java
auth.setUsername("admin");
auth.setPassword("password123");
```

2. Test Data Generation Issues
- Problem: Random test data causing inconsistent results
- Solution: Use fixed test data by modifying `PayloadManager.createPayloadBookingAsString()`

3. Report Generation Issues
- Problem: Missing Allure reports
- Solution: Clear previous results and regenerate
```bash
mvn clean test allure:report
```

## Data Flow
The framework implements a layered approach to API testing with clear separation of concerns.

```ascii
[Test Classes] -> [Base Test] -> [Payload Manager] -> [REST Assured] -> [API]
     |              |               |                      |             |
     |              |               |                      |             |
     v              v               v                      v             v
[Assertions] <- [Response] <- [POJO Models] <- [JSON Response] <- [API Response]
```

Key component interactions:
1. Test classes inherit from BaseTest for common setup and utilities
2. PayloadManager handles request/response serialization
3. REST Assured manages HTTP communications
4. POJO classes provide type-safe data handling
5. Assertions validate response data and status codes