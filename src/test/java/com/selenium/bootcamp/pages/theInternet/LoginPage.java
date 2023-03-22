package com.selenium.bootcamp.pages.theInternet;

import com.selenium.bootcamp.driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "flash")
    private WebElement flashErrorLblElement;

    @FindBy(id = "username")
    private WebElement usernameTxtElement;

    @FindBy(id = "password")
    private WebElement passwordTxtElement;

    @FindBy(className = "radius")
    private WebElement loginBtnElement;

    public LoginPage() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    public void login(String username, String password) {
        usernameTxtElement.sendKeys(username);
        passwordTxtElement.sendKeys(password);
        loginBtnElement.click();
    }

    public String getErrorMessage() {
        return flashErrorLblElement.getText();
    }
}
