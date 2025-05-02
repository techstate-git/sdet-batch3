package class_3;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Waits {
    WebDriver driver = new ChromeDriver();

    @BeforeClass
    public void setUp() {
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test
    public void implicitWait() {
        //Implicit wait: Applied globally
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement username = driver.findElement(By.xpath("//input[@name='username']"));
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        WebElement loginButton = driver.findElement(By.xpath("//button"));

        username.sendKeys("Admin");
        password.sendKeys("admin123");
        loginButton.click();

        WebElement dashboardHeader = driver.findElement(By.xpath("//h6[text()='Dashboard']"));
        Assert.assertTrue(dashboardHeader.isDisplayed(), "Dashboard is not displayed");
    }

    @Test
    public void explicitWait() {
        //Explicit Wait: Wait for elements individually
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button")));

        username.sendKeys("Admin");
        password.sendKeys("admin123");
        loginButton.click();

        WebElement dashboardHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));
        Assert.assertTrue(dashboardHeader.isDisplayed(), "Dashboard is not displayed");
    }

    @Test
    public void fluentWait() {
        //Fluent Wait: Customizable wait
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        WebElement username = fluentWait.until(driver -> driver.findElement(By.xpath("//input[@name='username']")));
        WebElement password = fluentWait.until(driver -> driver.findElement(By.xpath("//input[@name='password']")));
        WebElement loginButton = fluentWait.until(driver -> driver.findElement(By.xpath("//button")));

        username.sendKeys("Admin");
        password.sendKeys("admin123");
        loginButton.click();

        WebElement dashboardHeader = fluentWait.until(driver -> driver.findElement(By.xpath("//h6[text()='Dashboard']")));
        Assert.assertTrue(dashboardHeader.isDisplayed(), "Dashboard is not displayed");
    }
}




















