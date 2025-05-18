package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.Utilities.clickElement;
import static utils.Utilities.sendKeysToElement;

public class OrangeHRM {
    WebDriver driver;

    public OrangeHRM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(xpath = "//button")
    private WebElement loginButton;

    @FindBy(xpath = "//span[text()='My Info']/parent::a")
    private WebElement myInfo;

    @FindBy(xpath = "//img[@class=\"employee-image\"]")
    private WebElement profilePicture;

    @FindBy(xpath = "//input[@type=\"file\"]")
    private WebElement pictureInput;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement submitPictureSaveButton;

    @FindBy(xpath = "//button[@type=\"button\" and @class=\"oxd-icon-button oxd-main-menu-button\"]")
    private WebElement submitPictureSaveButton1;

    public void login() {
        sendKeysToElement(username, "Admin");
        sendKeysToElement(password, "admin123");
        clickElement(loginButton);
    }

    public void successfullyLoggedIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("/dashboard")
        ));
    }

    public void clickOnPage(String page) {
        switch (page) {
            case "My Info" : clickElement(myInfo);
            break;
        }
    }

    public void changeProfilePicture() throws InterruptedException {
        clickElement(profilePicture);
        Thread.sleep(5000);
        pictureInput.sendKeys( "/Users/beksultanismatov/IdeaProjects/Batch3/Cucumber/src/test/resources/pics/2.jpg");
        submitPictureSaveButton1.click();
        submitPictureSaveButton.click();
    }
}












