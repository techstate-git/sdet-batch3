package class_4;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Homework {
    WebDriver driver = new ChromeDriver();
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @BeforeTest
    public void setUp(){
        driver.get(" https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
    }


    @Test
    public void lumaWebPage() throws InterruptedException {
        WebElement womanClickLocator = driver.findElement(By.xpath("//a[@id='ui-id-4']"));
        womanClickLocator.click();
        WebElement jacketsLocator = driver.findElement(By.xpath("//div[@class='categories-menu']//ul[@class='items']//a[text()='Jackets']"));
        jacketsLocator.click();
        WebElement sortBySelect = driver.findElement(By.xpath("//select[@id='sorter']"));
        Select select = new Select(sortBySelect);
        select.selectByVisibleText("Product Name");
        WebElement perPageScrollDown = driver.findElement(By.xpath("//span[@class='limiter-text']"));
        // js.executeScript("arguments[0].scrollIntoView(true);",perPageScrollDown );
//        js.executeScript("window.scroll(0, document.body.scrollHeight)");

        // Find the desired element using XPath
        WebElement element = driver.findElement(By.xpath("//div[@class='search results']/div[1]//select[@id='limiter']"));

        // Use JavaScript to get the ::after content
        String content = (String) ((JavascriptExecutor) driver).executeScript(
                "return window.getComputedStyle(arguments[0], '::after').getPropertyValue('content');", element);

        // Print the content
        System.out.println("Content from ::after: " + content);

        Thread.sleep(2000);
//        WebElement selectPerPage = driver.findElement(By.xpath("//div[@class=\"search results\"]/div[3]//select[@id=\"limiter\"]"));

//        Select select1 = new Select(selectPerPage);
//        select1.selectByVisibleText(" 36 ");
    }
    @AfterTest
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}
