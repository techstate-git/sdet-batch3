package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Utilities {
    static WebDriver driver;
    static Duration duration = Duration.ofSeconds(5);

    public Utilities(WebDriver driver) {
        this.driver = driver;
    }

    //Loading Property file
    public static Properties readPropertyFile(String pathToPropertyFile) throws IOException {
        Properties properties = new Properties();
        File propFile = new File(pathToPropertyFile);
        FileInputStream fileInputStream = new FileInputStream(propFile);
        properties.load(fileInputStream);

        return properties;
    }

    public static String getSecret(String key) {
        try {
            Properties properties = readPropertyFile("src/test/resources/secret.properties");
            String value = properties.getProperty(key);
            return value;
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    public boolean acceptAlertIfPresent() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert Message: " + alertText);

            alert.accept();
            return true;
        } catch (NoAlertPresentException e) {
            System.out.println("No Alert presented");
            return false;
        }
    }

    public static void clickElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        clickableElement.click();
    }

    public static void sendKeysToElement(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
        visibleElement.clear();
        visibleElement.sendKeys(text);
    }
}











