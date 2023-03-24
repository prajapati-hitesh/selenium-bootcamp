package com.selenium.bootcamp.pages.seleniumeasy;

import com.selenium.bootcamp.driver.DriverFactory;
import org.openqa.selenium.By;

public class SERegisterPage {
    By firstNameInputBy = By.cssSelector("[name='first_name']");
    By lastNameInputBy = By.cssSelector("[name='last_name']");
    By emailInputBy = By.cssSelector("[name='email']");
    By phoneInputBy = By.cssSelector("[name='phone']");
    By addressInputBy = By.cssSelector("[name='address']");
    By cityInputBy = By.cssSelector("[name='city']");
    By zipCodeInputBy = By.cssSelector("[name='zip']");
    By websiteInputBy = By.cssSelector("[name='website']");
    By hasHostingYesRadioBy = By.xpath("//input[@name='hosting'][@value='yes']");
    By hasHostingNoRadioBy = By.xpath("//input[@name='hosting'][@value='no']");
    By projectDescriptionTextAreaBy = By.cssSelector("[name='comment']");

    public void firstName(String firstName) {
        DriverFactory.getDriver().findElement(firstNameInputBy).sendKeys(firstName);
    }

    public void lastName(String lastName) {
        DriverFactory.getDriver().findElement(lastNameInputBy).sendKeys(lastName);
    }

    public void email(String email) {
        DriverFactory.getDriver().findElement(emailInputBy).sendKeys(email);
    }

    public void phone(String phone) {
        DriverFactory.getDriver().findElement(phoneInputBy).sendKeys(phone);

    }

    public void address(String address) {
        DriverFactory.getDriver().findElement(addressInputBy).sendKeys(address);
    }

    public void city(String city) {
        DriverFactory.getDriver().findElement(cityInputBy).sendKeys(city);
    }

    public void zipCode(String postalcode) {
        DriverFactory.getDriver().findElement(zipCodeInputBy).sendKeys(postalcode);
    }

    public void website(String website) {
        DriverFactory.getDriver().findElement(websiteInputBy).sendKeys(website);
    }

    public void hasHosting(boolean hasHosting) {
        if (hasHosting) {
            DriverFactory.getDriver().findElement(hasHostingYesRadioBy).click();
        } else {
            DriverFactory.getDriver().findElement(hasHostingNoRadioBy).click();
        }

    }

    public void projectDescription(String projectDescription) {
        DriverFactory.getDriver().findElement(projectDescriptionTextAreaBy).sendKeys(projectDescription);

    }
}
