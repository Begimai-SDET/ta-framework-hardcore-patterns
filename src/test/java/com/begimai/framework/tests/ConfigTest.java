package com.begimai.framework.tests;

import com.begimai.framework.core.config.ConfigManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ConfigTest {

    @Test
    public void checkBaseUrlFromConfig() {
        String baseUrl = ConfigManager.get("base.url");
        System.out.println("BASE URL = " + baseUrl);
        Assert.assertNotNull(baseUrl, "base.url must not be null");
    }
}
