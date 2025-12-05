# S.O.L.I.D. fixes

Below is a list of code changes made to better follow S.O.L.I.D. principles.

---

## 1. DriverManager / DriverFactory – Single Responsibility Principle (SRP)

**Class:** `DriverManager`, `DriverFactory`  
**Problem (SRP):**  
Previously, `DriverManager` was responsible both for:
- creating `WebDriver` instances for different browsers, and
- managing the lifecycle of the driver (storing in ThreadLocal, quitting, etc.).

This violated the Single Responsibility Principle because one class had two different reasons to change.

**Solution:**
- Extracted driver creation logic into a separate class `DriverFactory`.
- Implemented a Factory Method: `DriverFactory.createDriver(String browserName)` that returns the concrete `WebDriver` implementation (`ChromeDriver`, `FirefoxDriver`, `EdgeDriver`) based on the browser name.
- `DriverManager` now only manages the driver lifecycle (init/get/quit) and delegates creation to `DriverFactory`.

This reduces responsibilities of `DriverManager` and makes the design easier to extend and maintain.

---

## 2. ConfigManager – Single Responsibility & centralized configuration (SRP)

**Class:** `ConfigManager`  
**Problem (SRP):**  
Previously, configuration values could be accessed via static methods, and the class did not control how many times the properties were loaded. There was no guarantee of a single configuration source during the test run.

**Solution:**
- Reworked `ConfigManager` as a Singleton:
    - private constructor,
    - single static instance `INSTANCE`,
    - public `getInstance()` and non-static `get(String key)` method.
- Properties are loaded only once inside the Singleton constructor.
- All code uses `ConfigManager.getInstance().get("key")` now.

This ensures a single source of configuration, improves SRP and makes configuration handling more predictable.

---

## 3. BaseTest & Page Objects – Single Responsibility Principle (SRP)

**Classes:** `BaseTest`, `GoogleCloudMainPage`  
**Problem (SRP):**  
`BaseTest` was responsible both for:
- test infrastructure setup (driver initialization),
- and page navigation (opening the base URL directly via `driver.get(...)`).

Mixing infrastructure and UI navigation in the same class breaks SRP.

**Solution:**
- Left only WebDriver lifecycle responsibility in `BaseTest` (`DriverManager.initDriver(...)`, `DriverManager.quitDriver()`).
- Moved page opening logic into the Page Object: `GoogleCloudMainPage.open()`, which internally uses `ConfigManager.getInstance().get("base.url")`.

Now:
- `BaseTest` is responsible only for test setup/teardown,
- Page Objects are responsible for navigation and UI interactions.

---

## 4. PricingService & Decorator – Open/Closed Principle (OCP) & Dependency Inversion (DIP)

**Classes:** `PricingService`, `GoogleCloudPricingService`, `LoggingPricingServiceDecorator`  
**Problem (OCP, DIP):**  
Tests were tightly coupled to the concrete class `GoogleCloudPricingService`.  
Adding extra behavior (e.g. additional logging or metrics) would require modifying the existing service or test code.

**Solution:**
- Introduced the `PricingService` interface.
- Made `GoogleCloudPricingService` implement `PricingService`.
- Created `LoggingPricingServiceDecorator` which also implements `PricingService` and wraps a delegate implementation.
- Tests now work with the abstraction:

  ```java
  PricingService service =
          new LoggingPricingServiceDecorator(new GoogleCloudPricingService());
