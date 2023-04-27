package com.selenium.bootcamp.driver;

import com.selenium.bootcamp.enums.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.Optional;

/**
 * Class to manage {@link WebDriver}
 *
 * @author Hitesh Prajapati
 * @version %I%, %G%
 * @since 1.0
 */
public class DriverFactory {

    private static final Logger logger = LogManager.getLogger(DriverFactory.class.getName());
    private static final ThreadLocal<WebDriver> driverInstance = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driverInstance.get() != null) {
            return driverInstance.get();
        } else {
            throw new NullPointerException("Please initialize Driver Object Before Calling this function");
        }
    }

    /**
     * To download binary for the browser specified as parameter
     * and create instance of it
     *
     * @param browser Browser to initialize of type <code>Browser</code>
     * @return <code>DriverFactory</code>
     */
    public static void initBrowser(Browser browser, Optional<?> browserOptions) {
        switch (browser) {
            case IE -> {
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
                ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                driverInstance.set(new InternetExplorerDriver(ieOptions));
            }
            case Edge -> {
                WebDriverManager.edgedriver().setup();
                driverInstance.set(new EdgeDriver());
            }
            case Firefox -> {
                System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
                WebDriverManager.firefoxdriver().setup();
                if (browserOptions.isPresent()) {
                    driverInstance.set(new FirefoxDriver((FirefoxOptions) browserOptions.get()));
                } else {
                    driverInstance.set(new FirefoxDriver());
                }
            }
            case Chromium -> {
                WebDriverManager.chromiumdriver().setup();
                driverInstance.set(new ChromeDriver());
            }
            default -> {
                WebDriverManager.chromedriver().setup();
                if (browserOptions.isPresent()) {
                    driverInstance.set(new ChromeDriver((ChromeOptions) browserOptions.get()));
                } else {
                    driverInstance.set(new ChromeDriver());
                }
            }
        }

        // Maximize browser
        getDriver().manage().window().maximize();
    }

    /**
     * To close the browser and clean the driver service.
     */
    public static void closeBrowser() {
        try {
            if (getDriver() != null) {
                getDriver().close();

                if (getDriver() instanceof ChromeDriver) {
                    getDriver().quit();
                }
            }
        } catch (WebDriverException ex) {
            logger.error(ex.getMessage(), ex);
        }

        // set value to null
        driverInstance.set(null);
    }

    /**
     * To get the instance of {@link JavascriptExecutor}
     *
     * @return {@link JavascriptExecutor} object
     */
    public static JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) getDriver();
    }

    /**
     * To get instance of {@link Actions}
     *
     * @return {@link Actions}
     */
    public static Actions getActionObject() {
        return new Actions(getDriver());
    }
}
