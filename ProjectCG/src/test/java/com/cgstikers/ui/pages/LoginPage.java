package com.cgstikers.ui.pages;

import com.cgstikers.ui.enums.ElementFinder;
import com.cgstikers.ui.enums.ElementLocator;
import com.cgstikers.ui.utils.DriverManager;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final ElementFinder finder;
    private final WebDriver driver;

    public LoginPage() {
        this.finder = new ElementFinder(DriverManager.getDriver());
        driver = DriverManager.getDriver();
    }

    public void login(String username, String password) {
        driver.get("http://35.175.142.166:3039/sign-in");
        finder.find(ElementLocator.USERNAME).sendKeys(username);
        finder.find(ElementLocator.PASSWORD).sendKeys(password);
        finder.find(ElementLocator.LOGIN_BUTTON).click();
    }
}
