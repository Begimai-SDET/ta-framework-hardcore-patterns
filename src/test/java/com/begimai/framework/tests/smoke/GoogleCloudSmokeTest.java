package com.begimai.framework.tests.smoke;

import com.begimai.framework.pages.GoogleCloudMainPage;
import com.begimai.framework.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleCloudSmokeTest extends BaseTest {

    @Test
    public void calculatorMainPageShouldBeOpened() {
        GoogleCloudMainPage mainPage = new GoogleCloudMainPage().open();
        Assert.assertTrue(mainPage.isOpened(), "Google Cloud Pricing Calculator page is not opened");
    }

}
