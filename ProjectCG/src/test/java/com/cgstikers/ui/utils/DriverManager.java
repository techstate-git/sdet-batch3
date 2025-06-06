package com.cgstikers.ui.utils;

import io.cucumber.java.AfterAll;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {
        if (driver.get() == null) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--disable-password-manager-reauthentication");
            options.addArguments("credentials_enable_service=false");
            options.addArguments("profile.password_manager_enabled=false");

            WebDriver chromeDriver = new ChromeDriver(options);
            chromeDriver.manage().window().maximize();
            driver.set(chromeDriver);
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
