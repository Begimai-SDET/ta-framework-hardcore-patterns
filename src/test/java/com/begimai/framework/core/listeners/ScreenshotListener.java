package com.begimai.framework.core.listeners;

import com.begimai.framework.core.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotListener implements ITestListener {

    private static final Logger log = LogManager.getLogger(ScreenshotListener.class);
    private static final String SCREENSHOTS_DIR = "screenshots";

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverManager.getDriver();
        if (driver == null) {
            log.error("Driver is null, cannot take screenshot for failed test: {}",
                    result.getMethod().getMethodName());
            return;
        }

        if (!(driver instanceof TakesScreenshot)) {
            log.error("Driver does not support taking screenshots for test: {}",
                    result.getMethod().getMethodName());
            return;
        }

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String testName = result.getMethod().getMethodName();
            String fileName = String.format("%s_%s.png", testName, timeStamp);

            Path screenshotsDir = Paths.get(SCREENSHOTS_DIR);
            Files.createDirectories(screenshotsDir);

            Path destPath = screenshotsDir.resolve(fileName);
            Files.copy(src.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);

            log.error("Test '{}' FAILED. Screenshot saved to: {}",
                    testName, destPath.toAbsolutePath());

        } catch (IOException e) {
            log.error("Failed to save screenshot for test: {}",
                    result.getMethod().getMethodName(), e);
        } catch (Exception e) {
            log.error("Unexpected error while taking screenshot for test: {}",
                    result.getMethod().getMethodName(), e);
        }
    }

}
