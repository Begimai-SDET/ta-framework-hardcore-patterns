package com.begimai.framework.pages;

import com.begimai.framework.core.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected final Logger log = LogManager.getLogger(this.getClass());
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    protected void open(String url) {
        log.info("Open URL: {}", url);
        driver.get(url);
    }

    protected void click(WebElement element) {
        log.debug("Click on element: {}", describe(element));
        highlight(element);
        element.click();
    }

    protected void type(WebElement element, String text) {
        log.debug("Type '{}' into element: {}", text, describe(element));
        highlight(element);
        element.clear();
        element.sendKeys(text);
    }

    protected void waitForVisible(WebElement element) {
        log.debug("Wait for visibility of element: {}", describe(element));
        wait.until(driver -> {
            try {
                return element.isDisplayed();
            } catch (StaleElementReferenceException e) {
                return false;
            }
        });
    }

    private String describe(WebElement element) {
        try {
            String tag = element.getTagName();
            String text = element.getText();
            return String.format("<%s> text='%s'", tag, text);
        } catch (Exception e) {
            return "<element>";
        }
    }

    protected void highlight(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            String originalStyle = element.getAttribute("style");

            js.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element,
                    originalStyle + " border: 2px solid red; background-color: rgba(255,255,0,0.3);"
            );

            Thread.sleep(150);

            js.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element,
                    originalStyle
            );
        } catch (Exception e) {
            log.debug("Failed to highlight element (ignoring): {}", e.getMessage());
        }
    }

}
