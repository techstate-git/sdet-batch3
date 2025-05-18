package class_4;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;

public class Tabs {
    WebDriver driver = new ChromeDriver();
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @BeforeClass
    public void setUp(){
        driver.get("https://www.kayak.com/");
        driver.manage().window().maximize();

        WebElement acceptCookies = driver.findElement(By.xpath("//div[text()='I understand']"));
        acceptCookies.click();
    }

    @Test
    public void tabs() {
        //4393F8ACE32876FBE7E6CCD9A90F5835
        String originalWindow = driver.getWindowHandle();
        String originalTitle = driver.getTitle();

        System.out.println("original window id: " + originalWindow);
        System.out.println("original title: " + originalTitle);

        WebElement cars = driver.findElement(By.xpath("//h3[text()='New York']/parent::div/following-sibling::div//a[.='CARS']"));
        js.executeScript("window.open(arguments[0].href, '_blank');", cars);

        //4393F8ACE32876FBE7E6CCD9A90F5835
        //NEW3F8ACE32876FBE7E6CCD9A90F5835
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window); // Switch to the new window
                break;
            }
        }

        String newOpenedWindow = driver.getWindowHandle();
        String newTitle = driver.getTitle();

        System.out.println("New window id: " + newOpenedWindow);
        System.out.println("New window title: " + newTitle);

        driver.close();
        driver.switchTo().window(originalWindow);

        System.out.println("Back to original window: " + driver.getTitle());
    }
}









