package com.begimai.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductSelectionPage extends BasePage {

    @FindBy(xpath = "//div[@role='button' and .//h2[contains(., 'Compute Engine')]]")
    private WebElement computeEngineButton;

    public ComputeEnginePage chooseComputeEngine() {
        click(computeEngineButton);
        return new ComputeEnginePage();
    }
}
