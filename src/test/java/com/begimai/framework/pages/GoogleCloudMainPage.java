package com.begimai.framework.pages;

import com.begimai.framework.core.config.ConfigManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudMainPage extends BasePage {

    @FindBy(xpath = "//h1[contains(., 'pricing calculator')]")
    private WebElement pageTitle;

    @FindBy(xpath = "(//button[contains(., 'Add to estimate')])[1]")
    private WebElement mainAddToEstimateButton;

    public GoogleCloudMainPage open() {
        open(ConfigManager.getInstance().get("base.url"));
        return this;
    }

    public boolean isOpened() {
        try {
            waitForVisible(pageTitle);
            if (pageTitle.isDisplayed()) {
                return true;
            }
        } catch (Exception ignored) {
        }
        String title = driver.getTitle();
        return title != null && title.toLowerCase().contains("pricing calculator");
    }

    public ProductSelectionPage clickAddToEstimate() {
        click(mainAddToEstimateButton);
        return new ProductSelectionPage();
    }
}
