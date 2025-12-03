package com.begimai.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EstimatePanelPage extends BasePage {

    @FindBy(xpath = "//div[contains(., 'Compute Engine') and contains(., 'Instances')]")
    private WebElement computeEngineCard;

    @FindBy(xpath = "//div[normalize-space()='Estimated cost']/following-sibling::div//label")
    private WebElement totalCostLabel;

    public boolean isComputeEngineCardPresent() {
        try {
            waitForVisible(computeEngineCard);
            return computeEngineCard.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getTotalCost() {
        wait.until(driver -> {
            String text = totalCostLabel.getText().trim();
            return !text.isEmpty() && !"--".equals(text);
        });
        return totalCostLabel.getText().trim();
    }
}
