package class_3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Test_3 {
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @BeforeTest
    public void setUp(){
        driver.get("https://bksultan.github.io/webForms/index.html");
    }

    @Test
    public void modals() throws InterruptedException {
        WebElement componentsLink = driver.findElement(By.id("componentsDropdown"));
        componentsLink.click();

        WebElement modalLink = driver.findElement(By.xpath("//a[@href='alerts.html']"));
        modalLink.click();

        WebElement modalButton = driver.findElement(By.id("openModalBtn"));
        modalButton.click();

        WebElement modalBodyParagraph = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-body']/p")));
        System.out.println("Content: " + modalBodyParagraph.getText());

        WebElement modalClose = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-footer']/button")));
        modalClose.click();
    }

    @Test
    public void iframes() {
        WebElement componentsLink = driver.findElement(By.id("componentsDropdown"));
        componentsLink.click();

        WebElement modalLink = driver.findElement(By.xpath("//a[@href='iframe.html']"));
        modalLink.click();

        //By web Element
//        WebElement iframe = driver.findElement(By.xpath("//iframe[@id='testIframe']"));
//        driver.switchTo().frame(iframe);

        //By index
//        driver.switchTo().frame(0);

        //By name or id
        driver.switchTo().frame("testIframe");

        WebElement clickMeButton = driver.findElement(By.xpath("//button[@id='iframeButton']"));
        clickMeButton.click();

        // Switch back to main level
        driver.switchTo().defaultContent();

        //Go back one level
        driver.switchTo().parentFrame();

        WebElement title = driver.findElement(By.xpath("//h2"));
        System.out.println(title.getText());
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
//        Thread.sleep(5000);
//        driver.quit();
    }
}
