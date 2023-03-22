package com.selenium.bootcamp.tests;

import com.selenium.bootcamp.pages.theInternet.HomePage;
import com.selenium.bootcamp.pages.theInternet.LoginPage;
import com.selenium.bootcamp.runner.TestNgSuiteRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TheInternetLoginTest extends TestNgSuiteRunner {

    @Test(priority = 0)
    public void verifyInvalidLoginCredential() {
        new HomePage().loadWebsite();

        LoginPage loginPage = new LoginPage();

        // Login to Application
        loginPage.login("tomsmith", "SuperSecretPassword");

        // Get error Message
        String errorMessage = new LoginPage().getErrorMessage().replaceAll("[@#$%×\\n]+", "");

        assertThat(errorMessage)
                .withFailMessage("Invalid error message validation failure.")
                .isInstanceOf(String.class)
                .isEqualTo("Your password is invalid!");

    }

    @Test(priority = 1)
    public void verifyValidLoginCredentials() {
        new HomePage().loadWebsite();

        LoginPage loginPage = new LoginPage();

        // Login to Application
        loginPage.login("tomsmith", "SuperSecretPassword!");

        // Get error Message
        String successLoginMessage = new HomePage().getSuccessMessage().replaceAll("[@#$%×\\n]+", "");

        assertThat(successLoginMessage)
                .withFailMessage("Failed validating of Login Success message")
                .isInstanceOf(String.class)
                .isEqualTo("You logged into a secure area!");
    }
}
