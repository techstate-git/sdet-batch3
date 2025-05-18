package class_4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DateParse {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void setUp(){
        driver.get("https://bksultan.github.io/webForms/index.html");
    }

    @Test
    public void homeWork() {
        // Navigate to Components > Tables
        WebElement componentsLink = driver.findElement(By.id("componentsDropdown"));
        componentsLink.click();

        WebElement tableLink = driver.findElement(By.xpath("//a[@href='tables.html']"));
        tableLink.click();
                                                            //2024-08-10
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fromDate = LocalDate.parse("2024-06-01", formatter);

        List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));

        for (WebElement row : rows) {
            try {
                //table/tbody/tr/td[3]
                String startDateText = row.findElement(By.xpath("./td[3]")).getText().trim();
                LocalDate startDate = LocalDate.parse(startDateText, formatter);

                //check if date is within range
                if (startDate.isEqual(fromDate) || startDate.isAfter(fromDate)){
                    Select select = new Select(row.findElement(By.xpath("./td[8]/select")));
                    select.selectByVisibleText("Approve");
                }

            } catch (Exception e) {
                System.out.println("Skipping row due to parsing issue: " + e.getMessage());
            }
        }
    }
}








