package com.selenium.bootcamp.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MayTests {

    WebDriver driver;

    @BeforeClass
    public void initializeBrowser() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        Thread.sleep(5000);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void inputFormTest() throws InterruptedException {
        driver.get("http://demo.seleniumeasy.com/input-form-demo.html");
        driver.findElement(By.name("first_name")).sendKeys("Hitesh"); // Every 500 ms, Poll DOM x 1 * 60
        driver.findElement(By.name("last_name")).sendKeys("Prajapati");
        driver.findElement(By.name("email")).sendKeys("hitesh@gmail.com");
        driver.findElement(By.name("phone")).sendKeys("+9156487987897");
        driver.findElement(By.name("address")).sendKeys("Somewhere on earth");
        driver.findElement(By.name("city")).sendKeys("Vadodara");

        Select stateSelect = new Select(driver.findElement(By.name("state")));
        stateSelect.selectByVisibleText("Illinois");
        System.out.println("Selected Option : " + stateSelect.getFirstSelectedOption().getText());

        driver.findElement(By.name("zip")).sendKeys("AB12 1OZ");
        driver.findElement(By.name("website")).sendKeys("something@something.com");

        List<WebElement> radioElements = driver.findElements(By.name("hosting"));
        WebElement yesRadio = radioElements.get(0);
        WebElement noRadio = radioElements.get(1);

        yesRadio.click();
        Thread.sleep(5000);
        noRadio.click();

        driver.findElement(By.name("comment")).sendKeys("Project description goes here.");
    }

    @Test
    public void checkboxTest() throws InterruptedException {
        driver.get("http://demo.seleniumeasy.com/input-form-demo.html");
        List<WebElement> radioElements = driver.findElements(By.name("hosting"));
        WebElement yesRadio = radioElements.get(0);
        WebElement noRadio = radioElements.get(1);

        yesRadio.click();
        Thread.sleep(5000);
        noRadio.click();
    }

    @Test
    public void windowSwitchingTest() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/windows");
        driver.findElement(By.linkText("Click Here")).click();
        Thread.sleep(5000);

        // get all the windows that got opened using driver.getWindowHandles
        List<String> windows = driver.getWindowHandles().stream().toList();

        // switch to the desired windows
        driver.switchTo().window(windows.get(1));

        // perform the actions or validation
        String newWindowText = driver.findElement(By.tagName("h3")).getText();

        System.out.println("H3 Text On Child Window : " + newWindowText);

        // assert the value
        assertThat(newWindowText).isEqualTo("New Window");

        // close the current tab
        driver.close();

        // switch back to parent windows
        driver.switchTo().window(windows.get(0));
        System.out.println("H3 Text On Parent Window : " + driver.findElement(By.tagName("h3")).getText());
    }

    @Test
    public void newTabOrWindowTest() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/inputs");

        WebElement inputElement = driver.findElement(By.tagName("input"));
        inputElement.sendKeys("98765");

        String parentWindowId = driver.getWindowHandle();

        // open new tab
        // driver.switchTo().newWindow(WindowType.WINDOW);
        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        Thread.sleep(5000);
        driver.close();
        // switch back to parent window
        driver.switchTo().window(parentWindowId);

        // refresh the page
        driver.navigate().refresh();
        WebElement inputElement2 = driver.findElement(By.tagName("input"));
        inputElement2.clear();
        inputElement2.sendKeys("12345");
    }

    @Test
    public void frameSwitchingTest() {
        driver.get("http://the-internet.herokuapp.com/nested_frames");
        // Total Frame - 4
        // Top Frame - 3 (Left, Middle, Right)
        // find Parent Frame (Top Frame)
        WebElement topFrameElement = driver.findElement(By.name("frame-top"));
        // Switch to Top Frame using Frame Element
        driver.switchTo().frame(topFrameElement);
        // find child frames (left, middle, right)
        // based on what frame you chose you get the frame element
        WebElement middleTopFrameElement = driver.findElement(By.name("frame-middle"));
        // switch to it
        driver.switchTo().frame(middleTopFrameElement);
        // find body element
        WebElement bodyElement = driver.findElement(By.tagName("body"));
        // get text
        System.out.println("Text Withing Middle Frame Is : " + bodyElement.getText());
        // assert
        driver.switchTo().parentFrame();
        driver.switchTo().parentFrame();

        // Bottom Frame - 2 (Horizontal)
        WebElement bottomFrameElement = driver.findElement(By.name("frame-bottom"));
        driver.switchTo().frame(bottomFrameElement);
        System.out.println("Text Within Bottom Frame Is : " + driver.findElement(By.tagName("body")).getText());
    }

    @Test
    public void synchronizationTest() {
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");
        driver.findElement(By.id("start")).findElement(By.tagName("button")).click();

        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        explicitWait.until(ExpectedConditions.and(
                        ExpectedConditions.invisibilityOfElementLocated(By.id("loading")),
                        ExpectedConditions.visibilityOfElementLocated(By.id("finish"))
                )
        );
        String finalValue = driver.findElement(By.id("finish")).getText();
        System.out.println("Final Value : " + finalValue);
    }

    @Test
    public void dragAndDropTest() {
        driver.get("https://demoqa.com/droppable");
        WebElement draggableElement = driver.findElement(By.id("draggable"));
        WebElement droppableElement = driver.findElement(By.id("droppable"));
        new Actions(driver)
                .dragAndDrop(draggableElement, droppableElement)
                .build().perform();


        // refresh page
        driver.navigate().refresh();
        WebElement draggableOneElement = driver.findElement(By.id("draggable"));
        WebElement droppableTwoElement = driver.findElement(By.id("droppable"));
        new Actions(driver)
                .clickAndHold(draggableOneElement)
                .moveToElement(droppableTwoElement)
                .release(draggableOneElement)
                .build().perform();
    }

    @Test
    public void newTabOrWindowsUsingActionClassTest() throws InterruptedException {
        driver.get("https://www.selenium.dev/downloads/");

        WebElement documentationLink = driver.findElement(By.xpath("//*[text()='Documentation']"));
        WebElement projectLink = driver.findElement(By.xpath("//*[text()='Projects']"));
        Thread.sleep(2000);
        Actions actions = new Actions(driver);

        // open documentation in new tab
        actions.keyDown(Keys.LEFT_CONTROL)
                .click(documentationLink)
                .keyUp(Keys.LEFT_CONTROL)
                .build().perform();

        Thread.sleep(5000);
        // open Project link into new window
        actions.keyDown(Keys.LEFT_SHIFT)
                .click(projectLink)
                .keyUp(Keys.LEFT_CONTROL)
                .build().perform();

    }

    @Test
    public void javascriptExecutorTest() {
        driver.get("http://the-internet.herokuapp.com/login");

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("document.getElementById('username').value = 'tomsmith'");
        js.executeScript("document.getElementById('password').value = 'SuperSecretPassword!'");
        driver.findElement(By.xpath("//*[normalize-space()='Login']")).click();

    }

    @Test
    public void alertTests() {
        driver.get("http://the-internet.herokuapp.com/javascript_alerts");
        WebElement alertJsButton = driver.findElement(By.xpath("//button[normalize-space()='Click for JS Alert']"));
        alertJsButton.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();

        WebElement anotherAlertButton = driver.findElement(By.xpath("//button[normalize-space()='Click for JS Confirm']"));
        anotherAlertButton.click();
        Alert alertTwo = driver.switchTo().alert();
        alertTwo.dismiss();

    }

    @Test
    public void contextClickTest() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        WebElement rightClickMeButton = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='right click me']")));

        new Actions(driver)
                .contextClick(rightClickMeButton)
                .build().perform();

        driver.findElement(By.xpath("//li[contains(@class,'context-menu-item') and ./span[text()='Cut']]")).click();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept();
        System.out.println("Text On Alert : " + text);
    }

    @Test
    public void shadowRootTest() {
        driver.get("http://watir.com/examples/shadow_dom.html");
        WebElement parentElement = driver.findElement(By.id("shadow_host"));

        // from parent, get shadowRoot
        SearchContext childShadowDom = parentElement.getShadowRoot();

        WebElement infoElement = childShadowDom.findElement(By.className("info"));
        System.out.println("Text Withing First Shadow Root Is : " + infoElement.getText());

    }


    @AfterClass
    public void cleanUp() throws InterruptedException {
        Thread.sleep(5000);
        driver.close();
    }
}