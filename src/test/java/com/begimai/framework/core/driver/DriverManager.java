package com.begimai.framework.core.driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static WebDriver driver;

    public static void initDriver(String browser) {
        if (driver != null) {
            return;
        }
        driver = DriverFactory.createDriver(browser);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
