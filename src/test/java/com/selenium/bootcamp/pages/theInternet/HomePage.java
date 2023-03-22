package com.selenium.bootcamp.pages.theInternet;

import com.selenium.bootcamp.driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(id = "flash")
    private WebElement successLblElement;

    @FindBy(className = "subheader")
    private WebElement subHeaderLblElement;

    @FindBy(xpath = "//a[@href='/logout']")
    private WebElement logoutBtnElement;

    public HomePage() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    public void loadWebsite() {
        DriverFactory.getDriver().get("https://the-internet.herokuapp.com/login");
    }

    public String getSuccessMessage() {
        return successLblElement.getText();
    }

    public void logout() {
        logoutBtnElement.click();
    }

    public String getHeaderText() {
        return subHeaderLblElement.getText();
    }
}
