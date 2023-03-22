package com.selenium.bootcamp.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionHelper {
    public static void moveToElement(WebDriver driver, WebElement elementToMove) {
        new Actions(driver)
                .moveToElement(elementToMove)
                .build()
                .perform();

    }

    public static void moveToElement(WebDriver driver, By by) {
        WebElement elementToMove = driver
                .findElement(by);
        new Actions(driver)
                .moveToElement(elementToMove)
                .build()
                .perform();

    }

    public static void scrollToTop(WebDriver driver) {
        // Scroll to top of the page
        new Actions(driver)
                .sendKeys(Keys.HOME)
                .build()
                .perform();
    }
}
