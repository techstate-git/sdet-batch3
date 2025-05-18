package class_4;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class JSExec {
    WebDriver driver = new ChromeDriver();
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @Test
    public void jsScroll() throws InterruptedException {
        driver.get("https://www.kayak.com/");
        driver.manage().window().maximize();

        WebElement acceptCookies = driver.findElement(By.xpath("//div[text()='I understand']"));
        acceptCookies.click();

        //TRUE                       TRUE
        //h2[@class='KzeV-title' and text()='For travel pros']
        WebElement targetElement = driver.findElement(By.xpath("//h2[@class='KzeV-title' and text()='For travel pros']"));

        js.executeScript("arguments[0].scrollIntoView(true);", targetElement);
        Thread.sleep(3000);
        takeScreenshot();

        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        takeScreenshot();
    }

    @Test
    public void highlightElement() {
        driver.get("https://www.kayak.com/");
        driver.manage().window().maximize();

        WebElement acceptCookies = driver.findElement(By.xpath("//div[text()='I understand']"));
        acceptCookies.click();

        WebElement targetElement = driver.findElement(By.xpath("//h2[@class='KzeV-title' and text()='For travel pros']"));

        js.executeScript("arguments[0].scrollIntoView(true);", targetElement);
        js.executeScript("arguments[0].style.border='2px solid red'", targetElement);
        js.executeScript("arguments[0].style.background='#ccc'", targetElement);
        takeScreenshot();
    }

    @Test
    public void helperMethod() throws InterruptedException {
        driver.get("https://www.kayak.com/");
        driver.manage().window().maximize();

        clickWithWait("//div[text()='I understand']");
        clickWithWait("//h3[text()='New York']/parent::div/following-sibling::div//a[.='CARS']");
    }

    public void takeScreenshot() {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                                                      //20250505182700
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        File destination = new File("src/test/resources/screenshots/ss_" + timestamp + ".png");

        try {
            FileUtils.copyFile(screenshot, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickWithWait(String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].style.border='1px solid red'", element);
        takeScreenshot();
        element.click();
    }
}












