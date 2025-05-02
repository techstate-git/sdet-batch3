package class_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.List;
import java.util.NoSuchElementException;

public class HomeWork {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void setUp(){
        driver.get("https://bksultan.github.io/webForms/index.html");
    }

    @Test
    public void homeWork() {
        WebElement componentsLink = driver.findElement(By.id("componentsDropdown"));
        componentsLink.click();

        WebElement tableLink = driver.findElement(By.xpath("//a[@href='tables.html']"));
        tableLink.click();

        List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
        System.out.println("Total transactions: " + rows.size());

        for (WebElement row : rows) {
            String price = row.findElement(By.xpath("./td[5]")).getText().replace("$", "").trim();
            double priceDouble = Double.parseDouble(price);

            if (priceDouble > 6000.00 && priceDouble < 7500.00) {
                String transactionID = row.findElement(By.xpath("./td[2]")).getText();
                String description = row.findElement(By.xpath("./td[4]")).getText();
                String status = row.findElement(By.xpath("./td[6]")).getText();

                WebElement approvalSelect = row.findElement(By.xpath("./td[8]/select"));
                Select select = new Select(approvalSelect);
                select.selectByVisibleText("Review");

                System.out.println("Transaction ID: " + transactionID);
                System.out.println("Description: " + description);
                System.out.println("Status: " + status);
                System.out.println("================");
            }
        }
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
//        Thread.sleep(5000);
//        driver.quit();
    }
}
