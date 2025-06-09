package com.cgstikers.ui.pages;

import com.cgstikers.ui.enums.ElementFinder;
import com.cgstikers.ui.enums.ElementLocator;
import com.cgstikers.ui.utils.DriverManager;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final ElementFinder finder;
    private final WebDriver driver;

    public LoginPage() {
        WebDriver webDriver = DriverManager.getDriver();
        if (webDriver == null) {
            throw new IllegalStateException("❌ WebDriver is null. Did you forget to call DriverManager.initDriver()?");
        }
        this.driver = webDriver;
        this.finder = new ElementFinder(webDriver);
    }

    public void login(String username, String password) throws InterruptedException {
        driver.get("http://35.175.142.166:3039/sign-in");
        finder.find(ElementLocator.USERNAME).sendKeys(username);
        finder.find(ElementLocator.PASSWORD).sendKeys(password);
        Thread.sleep(2000);
        finder.find(ElementLocator.LOGIN_BUTTON).click();
    }
}
