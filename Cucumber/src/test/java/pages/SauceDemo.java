package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SauceDemo {
    WebDriver driver;

    public SauceDemo(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(xpath = "//div[@class='login_password']")
    private WebElement actualPassword;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorLoginMessage;

    public void enterUsername(String username) {
        this.username.clear();
        this.username.sendKeys(username);
    }

    public void enterPassword() {
        String actualPsw = actualPassword.getText().replaceAll("Password for all users:", "");
        password.clear();
        password.sendKeys(actualPsw);
    }

    public void successfullyLoggedIn() throws InterruptedException {
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("saucedemo"),
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='app_logo']")),
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']"))
        ));
    }
}
