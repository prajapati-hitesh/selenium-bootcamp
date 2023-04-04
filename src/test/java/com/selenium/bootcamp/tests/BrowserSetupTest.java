package com.selenium.bootcamp.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.NumberFormat;
import java.time.Duration;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class BrowserSetupTest {
    private WebDriver driver;

    @BeforeClass(enabled = false)
    public void setupBrowser() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-default-browser-check");
        chromeOptions.addArguments("--remote-allow-origins=*");
        // chromeOptions.addArguments("--headless");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterClass(enabled = false)
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.close();
    }

    @Test
    public void basicInputTest() {

        driver.get("http://demo.seleniumeasy.com/basic-first-form-demo.html");

        WebElement userMessageElement = driver.findElement(By.id("get-input"))
                .findElement(By.id("user-message"));

        String textToEnter = "Hello, this is from Selenium Bootcamp";
        userMessageElement.sendKeys(textToEnter);

        //
        driver.findElement(By.xpath("//*[text()='Show Message']")).click();

        WebElement labelElement = driver.findElement(By.id("displays"));
        String actualText = labelElement.getText();
        System.out.println("Actual text : " + actualText);

        Assert.assertEquals(actualText, textToEnter);

    }

    @Test
    public void sumTest() {
        driver.get(("http://demo.seleniumeasy.com/basic-first-form-demo.html"));

        driver.findElement(By.id("sum1")).sendKeys("20");
        driver.findElement(By.id("sum2")).sendKeys("20");
        driver.findElement(By.xpath("//*[text()='Get Total']")).click();
        String totalActual = driver.findElement(By.id("displayvalue")).getText();

        System.out.println("Total : " + totalActual);
        Assert.assertEquals(totalActual, "40");
    }

    @Test
    public void printAllAnchorUrls() {
        driver.get("http://demo.seleniumeasy.com/basic-first-form-demo.html");
        List<WebElement> listOfAnchorElements = driver.findElements(By.tagName("a"));

        System.out.println("Total Anchor Tags :" + listOfAnchorElements.size());
//       for(int i=0;i<listOfAnchorElements.size(); i++) {
//           String href = listOfAnchorElements.get(i).getAttribute("href");
//           System.out.println(href);
//       }

//        for (WebElement element : listOfAnchorElements) {
//            String href = element.getAttribute("href");
//            System.out.println(href);
//        }

        listOfAnchorElements.forEach(element -> {
            String href = element.getAttribute("href");
            System.out.println(href);
        });
    }

    @Test
    public void progressBarTest() {
        driver.get("https://demoqa.com/progress-bar");
        driver.findElement(By.id("startStopButton")).click();

        // progressBar

        WebDriverWait progressBarWait = new WebDriverWait(driver, Duration.ofSeconds(90));

        progressBarWait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("progressBar"), "100%%"));

        WebElement progressElement = driver.findElement(By.id("progressBar"));
        System.out.println("Current Status : " + progressElement.getText());
    }

    @Test
    public void selectTest() {
        driver.get("http://demo.seleniumeasy.com/input-form-demo.html");

        // Get Select Element
        WebElement selectElement = driver.findElement(By.name("state"));

        Select selectEle = new Select(selectElement);
        selectEle.selectByVisibleText("Arizona");
        System.out.println("Selected Value : " + selectEle.getFirstSelectedOption().getText());

        selectEle.selectByIndex(5);
        System.out.println("Selected Value : " + selectEle.getFirstSelectedOption().getText());
    }

    @Test
    public void jqueryDropdownTest() {
        driver.get("http://demo.seleniumeasy.com/jquery-dropdown-search-demo.html");

        driver.findElement(By.xpath("//*[@aria-labelledby='select2-country-container']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("select2-country-results")));
        List<WebElement> listOfOptionElement = driver.findElement(By.id("select2-country-results")).findElements(By.tagName("li"));

        for (WebElement optionElement : listOfOptionElement) {
            if (optionElement.getText().trim().equalsIgnoreCase("New Zealand")) {
                optionElement.click();
                break;
            }
        }
        WebElement selectedOption = driver.findElement(By.id("select2-country-container"));
        System.out.println("Selected Option : " + selectedOption.getText());

        selectMultiOption("Alaska");
        selectMultiOption("Florida");
        selectMultiOption("Hawaii");

        WebElement selectedMultiOptions = driver.findElement(By.className("select2-selection__rendered"));
        System.out.println("Selected Options : " + selectedMultiOptions.getText());
    }

    private void selectMultiOption(String optionToSelect) {
        // Multi select
        driver.findElement(By.xpath("//*[@class='select2-search__field']")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("select2-results")));

        // Get All Li Elements
        List<WebElement> listOfOptions = driver.findElement(By.className("select2-results__options")).findElements(By.tagName("li"));

        for (WebElement eachElement : listOfOptions) {
            if (eachElement.getText().trim().equalsIgnoreCase(optionToSelect.trim())) {
                eachElement.click();
                break;
            }
        }
    }

    @Test
    public void currencyTest() {
        double currencyAmount = 150487870.487;
        // Create a new Locale
        Locale usa = new Locale("en", "GB");
        // Create a Currency instance for the Locale
        Currency dollars = Currency.getInstance(usa);
        // Create a formatter given the Locale
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);

        // Format the Number into a Currency String
        System.out.println(dollars.getDisplayName() + ": " + dollarFormat.format(currencyAmount));
    }


}
