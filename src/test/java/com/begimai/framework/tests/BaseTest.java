package com.begimai.framework.tests;

import com.begimai.framework.core.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseTest {

    protected final Logger log = LogManager.getLogger(this.getClass());

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) {
        log.info("===== STARTING TEST: {} | browser={} =====", this.getClass().getSimpleName(), browser);

        DriverManager.initDriver(browser);

        WebDriver driver = DriverManager.getDriver();
        log.debug("WebDriver initialized: {}", driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("===== FINISHING TEST: {} =====", this.getClass().getSimpleName());
        DriverManager.quitDriver();
    }
}
