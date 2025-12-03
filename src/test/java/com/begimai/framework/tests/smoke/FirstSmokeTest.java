package com.begimai.framework.tests.smoke;

import com.begimai.framework.core.driver.DriverManager;
import com.begimai.framework.tests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstSmokeTest extends BaseTest {

    @Test
    public void openBaseUrlTest() {
        WebDriver driver = DriverManager.getDriver();
        String title = driver.getTitle();
        System.out.println("PAGE TITLE = " + title);
        Assert.assertNotNull(title);
    }
}
