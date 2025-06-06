package com.cgstikers.ui.enums;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ElementFinder {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final int DEFAULT_TIMEOUT_SECONDS = 10;

    public ElementFinder(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
    }

    public WebElement find(ElementLocator locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator.getBy()));
    }

    public WebElement findClickable(ElementLocator locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
    }

    public List<WebElement> findAll(ElementLocator locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator.getBy()));
    }

    public boolean isVisible(ElementLocator locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator.getBy()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
