package class_1;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.security.Key;

public class WebInteractions {
    WebDriver driver = new ChromeDriver();
    Actions actions = new Actions(driver);

    @Test
    public void dragAndDrop() {
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");

        WebElement a = driver.findElement(By.id("column-a"));
        WebElement b = driver.findElement(By.id("column-b"));

        actions.dragAndDrop(a, b).build().perform();
    }

    @Test
    public void alerts(){
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        WebElement acceptAlert = driver.findElement(By.xpath("//button[.='Click for JS Alert']"));
        WebElement dismissAlert = driver.findElement(By.xpath("//button[.='Click for JS Confirm']"));
        WebElement sendKeysAlert = driver.findElement(By.xpath("//button[.='Click for JS Prompt']"));

        acceptAlert.click();
        Alert alertAccept = driver.switchTo().alert();
        alertAccept.accept();

        dismissAlert.click();
        Alert alertDismiss = driver.switchTo().alert();
        alertDismiss.dismiss();

        sendKeysAlert.click();
        Alert alertSendKeys = driver.switchTo().alert();
        alertSendKeys.sendKeys("TechState");
        alertSendKeys.accept();
    }

    @Test
    public void keyPress() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/key_presses");

        actions.sendKeys(Keys.TAB).perform();
        Thread.sleep(4000);
        actions.sendKeys(Keys.ARROW_UP).perform();
    }

    @Test
    public void fileUpload() {
        driver.get("https://the-internet.herokuapp.com/upload");

        WebElement uploadButton = driver.findElement(By.id("file-upload"));
        String filePath = "/Users/beksultanismatov/Downloads/Java_1745079880.pdf";

        uploadButton.sendKeys(filePath);

        WebElement upload = driver.findElement(By.id("file-submit"));
        upload.click();
    }
}
