package class_3;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Ebay {
    WebDriver driver = new ChromeDriver();

    @BeforeClass
    public void setUp() {
        driver.manage().window().maximize();
    }

    @Test
    public void javaForDummiesBookTest() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.ebay.com/");

        WebElement search = driver.findElement(By.xpath("//div[@class='gh-search__wrap']//input"));
        search.sendKeys("Java for dummies");

        WebElement searchButton = driver.findElement(By.xpath("//button/span[.='Search']"));
        searchButton.click();

        List<WebElement> productTitles = driver.findElements(By.xpath("//span[@role='heading']"));

        System.out.println("Total titles found: " + productTitles.size());

        for (WebElement title : productTitles) {
            try {
                String text = title.getText().trim().toLowerCase();
                if (text.isEmpty()) continue; // Skip junk entries

                System.out.println(">> " + text);
                Assert.assertTrue(text.contains("java"), "Product title does not contain 'java': " + text);
            } catch (Exception e) {
                System.out.println("Skipping item: " + e.getMessage());
            }
        }
    }

}
