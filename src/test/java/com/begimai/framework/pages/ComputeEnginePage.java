package com.begimai.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ComputeEnginePage extends BasePage {

    @FindBy(css = "input[type='number'][min='0'][max='50000'][autocomplete='off']")
    private WebElement instancesInput;

    public ComputeEnginePage setInstances(String count) {
        waitForVisible(instancesInput);
        type(instancesInput, count);
        return this;
    }

}
