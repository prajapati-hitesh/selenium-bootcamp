package com.selenium.bootcamp.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScrollHelper {

    private static final Logger logger = LogManager.getLogger(ScrollHelper.class.getName());
    private static final long scrollWait = 200;

    /**
     * To scroll to a specific element in dom
     *
     * @param driver  Object of {@link WebDriver}
     * @param element {@link WebElement} to scroll to
     */
    public static void scrollToElement(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor jse = ((JavascriptExecutor) driver);
            jse.executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * To Scroll to the middle of an element
     *
     * @param driver  Object of {@link WebDriver}
     * @param element {@link WebElement} to scroll to
     */
    public static void scrollElementToMiddle(WebDriver driver, WebElement element) {
        try {
            String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                    + "var elementTop = arguments[0].getBoundingClientRect().top;"
                    + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

            ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * To scroll down to a specific height in DOM
     *
     * @param driver    Object of {@link WebDriver}
     * @param element   {@link WebElement} to scroll down to
     * @param dimension Height in pixel to scroll down
     */
    public static void scrollDownToHeightFromElement(WebDriver driver, WebElement element, int dimension) {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scroll(0," + dimension + ")", element);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * To scroll up to a specific height from an element in DOM
     *
     * @param driver  Object of {@link WebDriver}
     * @param element {@link WebElement} from where to scroll up
     */
    public static void scrollUpToHeightFromElement(WebDriver driver, WebElement element, int dimension) {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scroll(0,-" + dimension + ")", element);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * To scroll all the way down to end of page in DOM
     *
     * @param driver Object of {@link WebDriver}
     */
    public static void scrollToBottom(WebDriver driver) {
        try {
            driver.switchTo().defaultContent();
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollTo(0, document.body.scrollHeight || document.documentElement.scrollHeight);");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * To scroll all the way down to end of page in DOM smoothly
     *
     * @param driver Object of {@link WebDriver}
     */
    public static void scrollDownSmoothly(WebDriver driver) {
        try {
            for (int i = 0; i < 2000; i++) {
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,10)", "");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * To scroll smoothly to a specific element down the DOM
     *
     * @param driver  Object of {@link WebDriver}
     * @param element {@link WebElement} to where scroll to
     */
    public static void scrollDownToElementSmoothly(WebDriver driver, WebElement element) {
        try {
            for (int i = 0; i < 2000; i++) {
                JavascriptExecutor jse = ((JavascriptExecutor) driver);
                jse.executeScript("arguments[0].scrollIntoView(true);", element);
                jse.executeScript("window.scrollBy(0,-100)");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Scroll all the way from down to Top Of Page
     *
     * @param driver Object of {@link WebDriver}
     */
    public static void scrollToTop(WebDriver driver) {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollTo({ top: 0, behavior: 'smooth' });");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
