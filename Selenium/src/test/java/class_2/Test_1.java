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

import java.nio.DoubleBuffer;

public class Test_1 {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void setUp(){
        driver.get("https://bksultan.github.io/webForms/index.html");
    }

    @Test
    public void test_user_is_invalid() {
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("loginBtn"));
        WebElement loginErrorMsg = driver.findElement(By.id("loginError"));

        username.sendKeys("Incorrect username");
        password.sendKeys("Incorrect password");
        loginBtn.click();

        // True & True = True
        // True & False = False
        // False & False = False
        Assert.assertTrue(loginErrorMsg.isDisplayed());
    }

    @Test
    public void register_new_user() throws InterruptedException {
        WebElement registerButton = driver.findElement(By.xpath("//a[@href='register.html']"));
        registerButton.click();

        // Fill up the form
        WebElement fullName = driver.findElement(By.id("regName"));
        WebElement email = driver.findElement(By.id("regEmail"));
        WebElement username = driver.findElement(By.id("regUsername"));
        WebElement password = driver.findElement(By.id("regPassword"));
        WebElement confirmPassword = driver.findElement(By.id("regConfirmPassword"));
        WebElement genderMale = driver.findElement(By.id("genderMale"));

        fullName.sendKeys("Beksultan Ismatov");
        email.sendKeys("bksultan@gmail.com");
        username.sendKeys("bksultan");
        password.sendKeys("bksultan");
        confirmPassword.sendKeys("bksultan");
        genderMale.click();

        WebElement countryDropDown = driver.findElement(By.xpath("//select[@id='regCountry']"));
        Select selectCountry = new Select(countryDropDown);

        for (int i=1; i<5; i++) {
            selectCountry.selectByIndex(i);
            Thread.sleep(1000);
        }

        selectCountry.selectByVisibleText("Australia");

        WebElement registerBtn = driver.findElement(By.id("registerBtn"));
        registerBtn.click();

        WebElement registerSuccess = driver.findElement(By.id("registerSuccess"));
        Assert.assertTrue(registerSuccess.isDisplayed());
    }

    @Test
    public void tableTest(){
        WebElement componentsLink = driver.findElement(By.id("componentsDropdown"));
        componentsLink.click();

        WebElement tableLink = driver.findElement(By.xpath("//a[@href='tables.html']"));
        tableLink.click();

        WebElement transID02Amount = driver.findElement(By.xpath("//td[.='TXN1002']/following-sibling::td[3]"));
        System.out.println(transID02Amount.getText());
    }

    @Test
    public void tableTest1() {
        WebElement componentsLink = driver.findElement(By.id("componentsDropdown"));
        componentsLink.click();

        WebElement tableLink = driver.findElement(By.xpath("//a[@href='tables.html']"));
        tableLink.click();

        for (int i = 30; i<=50; i++) {
                                                                                                     //30
            WebElement transactionsAmount = driver.findElement(By.xpath("//td[.='TXN10"+ i +"']/following-sibling::td[3]"));
            System.out.println(transactionsAmount.getText());
        }
    }

    @Test
    public void tableTest2() {
        WebElement componentsLink = driver.findElement(By.id("componentsDropdown"));
        componentsLink.click();

        WebElement tableLink = driver.findElement(By.xpath("//a[@href='tables.html']"));
        tableLink.click();

        double sumOfTransactions = 0;

        for (int i = 1; i<=9; i++) {
            WebElement transactionsAmount = driver.findElement(By.xpath("//td[.='TXN100"+ i +"']/following-sibling::td[3]"));
            String priceText = transactionsAmount.getText().replace("$", "");

            System.out.println(priceText);

            double priceDouble = Double.parseDouble(priceText);

            sumOfTransactions += priceDouble;
        }

        System.out.println("Total amount is = " + sumOfTransactions);
    }

    @Test
    public void tableTest3() {
        WebElement componentsLink = driver.findElement(By.id("componentsDropdown"));
        componentsLink.click();

        WebElement tableLink = driver.findElement(By.xpath("//a[@href='tables.html']"));
        tableLink.click();

        for (int i = 1; i<=9; i++) {
            String baseXpath = "//td[.='TXN100" + i + "']";

            WebElement transactionsAmount = driver.findElement(By.xpath(baseXpath + "/following-sibling::td[3]"));
            String priceText = transactionsAmount.getText().replace("$", "");

            double priceDouble = Double.parseDouble(priceText);

            if (priceDouble < 5000.00) {

                WebElement approvalSelect = driver.findElement(By.xpath(baseXpath + "/following-sibling::td[6]/select"));

                Select select = new Select(approvalSelect);
                select.selectByVisibleText("Reject");
            }
        }
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
//        Thread.sleep(5000);
//        driver.quit();
    }
}
