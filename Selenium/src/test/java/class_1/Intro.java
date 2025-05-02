package class_1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class Intro {
    WebDriver driver = new ChromeDriver();

    @Test
    public void loginTest() {
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        driver.findElement(By.id("login-button")).click();

        List<WebElement> titles = driver.findElements(By.xpath("//div[@class='inventory_item_name ']"));

        for (WebElement title : titles) {
            System.out.println(title.getText());
        }
    }

    @Test
    public void priceCheck() {
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        driver.findElement(By.id("login-button")).click();

        List<WebElement> prices = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));

        // Print the prices which are in range of > $10 and < $30
        //If within the range Print -> Item in the cart
        //If not print -> Item not in the range

        for (WebElement price : prices) {
            String priceParced = price.getText().replace("$", "");

            double priceDouble = Double.parseDouble(priceParced);

            if (priceDouble > 10 && priceDouble < 30) {
                System.out.println(priceDouble + " Item in the cart");
            } else {
                System.out.println(priceDouble + " Item not in the range");
            }
        }
    }

    @Test
    public void loginTest1() {
        driver.get("https://the-internet.herokuapp.com/login");

        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
}








