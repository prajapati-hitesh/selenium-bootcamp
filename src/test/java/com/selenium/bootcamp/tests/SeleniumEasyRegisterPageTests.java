package com.selenium.bootcamp.tests;

import com.github.javafaker.Faker;
import com.selenium.bootcamp.driver.DriverFactory;
import com.selenium.bootcamp.pages.seleniumeasy.SERegisterPage;
import com.selenium.bootcamp.runner.TestNgSuiteRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SeleniumEasyRegisterPageTests extends TestNgSuiteRunner {
    private static final Logger logger = LogManager.getLogger(SeleniumEasyRegisterPageTests.class.getName());

    @Test(priority = 0)
    public void seleniumEasyRegisterTest() {
        DriverFactory.getDriver().get("http://demo.seleniumeasy.com/input-form-demo.html");
        logger.info("URL : " + DriverFactory.getDriver().getTitle());

        SERegisterPage seRegisterPage = new SERegisterPage();
        seRegisterPage.firstName("Hitesh");
        seRegisterPage.lastName("Prajapati");
        seRegisterPage.email("hiteshprajapati1992@gmail.com");
        seRegisterPage.phone("+919427004603");
        seRegisterPage.address("12, Shreeji Streets, Vadodara");
        seRegisterPage.city("Vadodara");
        seRegisterPage.zipCode("392002");
        seRegisterPage.website("https://github.com/prajapati-hitesh");
        seRegisterPage.hasHosting(true);
        seRegisterPage.projectDescription("Some random Project Description");
    }

    @Test(priority = 1, dataProvider = "randomRegistrationDataProvider")
    public void seleniumEasyRegisterDataProviderTest(String firstName, String lastName, String email, String phone, String address,
                                                     String city, String zipcode, String website, boolean hasHosting, String projectDescription) {
        DriverFactory.getDriver().get("http://demo.seleniumeasy.com/input-form-demo.html");
        logger.info("URL : " + DriverFactory.getDriver().getTitle());

        SERegisterPage seRegisterPage = new SERegisterPage();
        seRegisterPage.firstName(firstName);
        seRegisterPage.lastName(lastName);
        seRegisterPage.email(email);
        seRegisterPage.phone(phone);
        seRegisterPage.address(address);
        seRegisterPage.city(city);
        seRegisterPage.zipCode(zipcode);
        seRegisterPage.website(website);
        seRegisterPage.hasHosting(hasHosting);
        seRegisterPage.projectDescription(projectDescription);
    }

    @DataProvider(name = "randomRegistrationDataProvider")
    public Object[][] getRegistrationData() {
        Faker faker = new Faker();
        // generate random counter
        int randomDataCount = faker.random().nextInt(1, 15);

        logger.info("Generating [" + randomDataCount + "] data.");

        Object[][] dataObject = new Object[randomDataCount][10];

        for (int row = 0; row < randomDataCount; row++) {
            dataObject[row][0] = faker.name().firstName();
            dataObject[row][1] = faker.name().lastName();
            dataObject[row][2] = faker.internet().safeEmailAddress();
            dataObject[row][3] = faker.phoneNumber().phoneNumber();
            dataObject[row][4] = faker.address().fullAddress();
            dataObject[row][5] = faker.address().city();
            dataObject[row][6] = faker.address().zipCode();
            dataObject[row][7] = faker.internet().domainName();
            dataObject[row][8] = faker.random().nextBoolean();
            dataObject[row][9] = faker.gameOfThrones().quote();
        }
        return dataObject;
    }
}
