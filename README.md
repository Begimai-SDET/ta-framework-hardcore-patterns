#  Test Automation Framework – Google Cloud Pricing Calculator

This is a modular, scalable **Test Automation Framework** built as part of the “Hardcore” task in the Selenium WebDriver course.  
The project demonstrates modern automation architecture including:

- multi-layer design
- reusable Page Objects
- business services & business models
- environment configuration
- XML test suites (Smoke & Regression)
- logging, screenshots on failure
- element highlighting (bonus)

---

## Tech Stack

- Java 11
- Maven
- TestNG
- Selenium WebDriver
- WebDriverManager
- Log4j2
- Page Object / Page Factory
- TestNG Listeners

---

## Project Architecture

```
ta-framework-hardcore/
│
├── src/test/java/com/begimai/framework
│   ├── core
│   │   ├── config        → ConfigManager (env handling)
│   │   ├── driver        → DriverFactory / DriverManager
│   │   └── listeners     → Screenshot Listener
│   ├── pages             → Page Objects
│   ├── business
│   │   ├── model         → ComputeEngineConfig, EstimateResult
│   │   └── service       → GoogleCloudPricingService
│   └── tests             → Smoke & Regression tests
│
├── src/test/resources
│   ├── config            → config.dev.properties / config.qa.properties
│   ├── smoke.xml         → Smoke suite
│   ├── regression.xml    → Regression suite
│   └── log4j2.xml        → Logging config
│
├── logs/                 → Daily rotated log files (auto-created)
├── screenshots/          → Screenshots on test failures (auto-created)
└── pom.xml
```

---

## Environment Configuration

The framework supports multiple environments using properties:

```
config.dev.properties
config.qa.properties
```

To select the environment:

```bash
-Denv=qa
```

Defaults to:

```
env = dev
```

---

##  Running Tests

### ▶ Run Smoke Suite

```bash
mvn clean test -Dsuite=smoke
```

or run `smoke.xml` in IntelliJ.

---

### ▶ Run Regression Suite

```bash
mvn clean test -Dsuite=regression
```

or run `regression.xml`.

---

### ▶ Running with different browsers

Override browser:

```bash
-Dbrowser=firefox
```

Default: **chrome**

---

##  Business Layer (BO + Services)

Example:

```java
ComputeEngineConfig config = new ComputeEngineConfig(4);

GoogleCloudPricingService service = new GoogleCloudPricingService();
EstimateResult result = service.createComputeEngineEstimate(config);

Assert.assertTrue(result.isComputeEngineCardPresent());
Assert.assertTrue(result.getTotalCost().contains("$"));
```

---

##  Logging

Implemented via **Log4j2**.

### Includes:
- INFO, DEBUG, ERROR levels
- Console + file output
- Daily rotating log files
- Action-level logging for clicks, typing, waits

---

##  Screenshots on Failure

On test failure, a screenshot is saved automatically:

```
screenshots/TestName_2025-12-04_21-15-55.png
```

Path is logged in Log4j2 during failure.

---

##  Element Highlighting (Bonus)

Before each `click()` or `type()`, the framework highlights elements using JavaScript:

- Red border
- Yellow background
- Short flash

Useful for debugging and demo runs.

---

##  Design Patterns Used

- Page Object Model
- Page Factory
- Business Object Pattern
- Service Layer
- Singleton-style Driver Management

---

##  Example Test

```java
@Test
public void userCanAddComputeEngineToEstimate() {

    ComputeEngineConfig config = new ComputeEngineConfig(4);

    GoogleCloudPricingService service = new GoogleCloudPricingService();
    EstimateResult result = service.createComputeEngineEstimate(config);

    Assert.assertTrue(result.isComputeEngineCardPresent());
    Assert.assertTrue(result.getTotalCost().contains("$"));
}
```

---

## ✔ Conclusion

This project demonstrates a complete, maintainable, CI-ready UI test automation framework covering:

- layered architecture
- business-service abstraction
- environment configs
- logging & screenshots
- XML suites
- highlighting (bonus)

Suitable for real UI automation and shows strong engineering practices.

