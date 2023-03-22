package com.selenium.bootcamp.runner;


import com.selenium.bootcamp.driver.DriverFactory;
import com.selenium.bootcamp.enums.Browser;
import com.selenium.bootcamp.utillity.SystemUtility;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestNgSuiteRunner {
    private static final Logger logger = LogManager.getLogger(TestNgSuiteRunner.class.getName());
    final Browser DEFAULT_BROWSER = Browser.Chrome;
    private final String FILE_SEPARATOR = SystemUtility.getFileSeparator();
    private final String USER_DIR = SystemUtility.getUserDirectory();
    public final String SCREENSHOT_DIR = USER_DIR.concat(FILE_SEPARATOR).concat("screenshot").concat(FILE_SEPARATOR);
    public final String AD_BLOCKER_PATH = USER_DIR.concat(FILE_SEPARATOR).concat("browser-extensions").concat(FILE_SEPARATOR).concat("Ultimate AdBlocker_2_2_6_0.crx");

    @BeforeTest(alwaysRun = true)
    @Parameters({"browser-name"})
    public void initializeTestNgRunEnvironment(@Optional String browserName) {
        // Disable Chrome Logs
        System.setProperty("webdriver.chrome.silentOutput", "true");
        String browser = StringUtils.isBlank(browserName) ? DEFAULT_BROWSER.getBrowserName() : browserName;

        if (Browser.getEnum(browser) == Browser.Chrome) {
            ChromeOptions chromeOptions = new ChromeOptions()
                    .addArguments("--no-default-browser-check")
                    //.addExtensions(new File(AD_BLOCKER_PATH))
                    .addArguments("--remote-allow-origins=*");  // this is for the error that is being faced by people with latest selenium & Chrome Version;

            // Initialize Browser
            DriverFactory.initBrowser(Browser.getEnum(browser.trim()), java.util.Optional.of(chromeOptions));
        } else {
            DriverFactory.initBrowser(Browser.getEnum(browser.trim()), java.util.Optional.empty());
        }
        logger.info("Browser Launched and website Loaded.");
    }

    @AfterTest(alwaysRun = true)
    public void cleanUpTestNgRunEnvironment() {
        DriverFactory.closeBrowser();
    }
}
