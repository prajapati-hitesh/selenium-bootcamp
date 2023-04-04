package com.selenium.bootcamp.tests;

import com.github.javafaker.Faker;
import com.selenium.bootcamp.pages.seleniumeasy.RegisterPage;
import com.selenium.bootcamp.utillity.SystemUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Locale;

public class ToolsQARegistrationTests {

    private final String FILE_SEPARATOR = SystemUtility.getFileSeparator();
    private final String USER_DIR = SystemUtility.getUserDirectory();
    public final String AD_BLOCKER_PATH = USER_DIR.concat(FILE_SEPARATOR).concat("browser-extensions").concat(FILE_SEPARATOR).concat("Ultimate AdBlocker_2_2_6_0.crx");
    private WebDriver driver;

    @BeforeClass
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-default-browser-check");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addExtensions(new File(AD_BLOCKER_PATH));
        // chromeOptions.addArguments("--headless");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.close();
    }

    @Test
    public void studentRegistrationTest() {
        driver.get("http://demo.seleniumeasy.com/input-form-demo.html");

        Faker faker = new Faker(new Locale("en-IND"));
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().safeEmailAddress();
        String phone = faker.phoneNumber().phoneNumber();
        String address = faker.address().fullAddress();
        String city = faker.address().city();
        String zip = faker.address().zipCode();
        String website = faker.company().url();
        String comment = faker.gameOfThrones().quote();

        List<String> stateList = List.of("Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware");
        String randomState = faker.options().nextElement(stateList);
        Boolean hasHosting = faker.random().nextBoolean();


        driver.findElement(By.name("first_name")).sendKeys(firstName);
        driver.findElement(By.name("last_name")).sendKeys(lastName);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys(phone);
        driver.findElement(By.name("address")).sendKeys(address);
        driver.findElement(By.name("city")).sendKeys(city);

        // Get Select Element
        new Select(driver.findElement(By.name("state"))).selectByVisibleText(randomState);

        driver.findElement(By.name("zip")).sendKeys(zip);
        driver.findElement(By.name("website")).sendKeys(website);

        if (hasHosting) {
            driver.findElement(By.xpath("//*[@value='yes']")).click();
        } else {
            driver.findElement(By.xpath("//*[@value='no']")).click();
        }

        driver.findElement(By.name("comment")).sendKeys(comment);
        driver.findElement(By.xpath("//button[normalize-space()='Send']")).click();
    }

    @Test
    public void pomRegisterTest() {
        driver.get("http://demo.seleniumeasy.com/input-form-demo.html");

        Faker faker = new Faker(new Locale("en-IND"));

        List<String> stateList = List.of("Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware");
        String randomState = faker.options().nextElement(stateList);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.firstname(faker.name().firstName());
        registerPage.lastname(faker.name().lastName());
        registerPage.email(faker.internet().safeEmailAddress());
        registerPage.phone(faker.phoneNumber().phoneNumber());
        registerPage.address(faker.address().fullAddress());
        registerPage.city(faker.address().city());
        registerPage.state(randomState);
        registerPage.postcode(faker.address().zipCode());
        registerPage.website(faker.company().url());
        registerPage.hasHosting(faker.random().nextBoolean());
        registerPage.comment(faker.gameOfThrones().quote());
        registerPage.submit();
    }

    @Test
    public void dynamicTableTest() {
        driver.get("http://demo.seleniumeasy.com/table-sort-search-demo.html");

        // Enter office value to filter
        driver.findElement(By.id("example_filter")).findElement(By.tagName("input")).sendKeys("London");

        List<WebElement> allRowElements = driver.findElement(By.id("example"))
                .findElement(By.tagName("tbody"))
                .findElements(By.tagName("tr"));


        allRowElements.forEach(rowElement -> {
            List<WebElement> columnElements = rowElement.findElements(By.tagName("td"));

            // at index 2 we have office column
            String currOfficeName = columnElements.get(2).getText();

            System.out.println("Office Name : " + currOfficeName);
            Assert.assertEquals(currOfficeName, "London");
        });
    }
}
