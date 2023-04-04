package com.selenium.bootcamp.pages.seleniumeasy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage {

    @FindBy(name = "first_name")
    WebElement firstnameInputElement;
    @FindBy(name = "last_name")
    WebElement lastnameInputElement;
    @FindBy(name = "email")
    WebElement emailInputElement;
    @FindBy(name = "phone")
    WebElement phoneInputElement;
    @FindBy(name = "address")
    WebElement addressInputElement;
    @FindBy(name = "city")
    WebElement cityInputElement;
    @FindBy(name = "state")
    WebElement stateSelectElement;
    @FindBy(name = "zip")
    WebElement zipInputElement;
    @FindBy(name = "website")
    WebElement websiteInputElement;
    @FindBy(xpath = "//*[@value='yes']")
    WebElement hostingYesCbxElement;
    @FindBy(xpath = "//*[@value='no']")
    WebElement hostingNoCbxElement;
    @FindBy(name = "comment")
    WebElement commentInputElement;
    @FindBy(xpath = "//button[normalize-space()='Send']")
    WebElement sendButtonElement;

    public RegisterPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void firstname(String firstname) {
        firstnameInputElement.sendKeys(firstname);
    }

    public void lastname(String lastname) {
        lastnameInputElement.sendKeys(lastname);
    }

    public void email(String email) {
        emailInputElement.sendKeys(email);
    }

    public void phone(String phone) {
        phoneInputElement.sendKeys(phone);
    }

    public void address(String fullAddress) {
        addressInputElement.sendKeys(fullAddress);
    }

    public void city(String city) {
        cityInputElement.sendKeys(city);
    }

    public void state(String state) {
        new Select(stateSelectElement).selectByVisibleText(state);
    }

    public void postcode(String zip) {
        zipInputElement.sendKeys(zip);
    }

    public void website(String site) {
        websiteInputElement.sendKeys(site);
    }

    public void hasHosting(Boolean hasHosting) {
        if (hasHosting) {
            hostingYesCbxElement.click();
        } else {
            hostingNoCbxElement.click();
        }
    }

    public void comment(String comment) {
        commentInputElement.sendKeys(comment);
    }

    public void submit() {
        sendButtonElement.click();
    }
}
