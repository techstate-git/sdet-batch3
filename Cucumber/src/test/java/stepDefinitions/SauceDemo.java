package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;
import utils.Utilities;

public class SauceDemo {
    WebDriver driver = DriverManager.getDriver();
    pages.SauceDemo sauceDemo = new pages.SauceDemo(driver);
    Utilities utilities = new Utilities(driver);

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("I enter {string}")
    public void i_enter(String username) {
        sauceDemo.enterUsername(username);
    }
    @When("I enter password")
    public void i_enter_password() {
        sauceDemo.enterPassword();
    }
    @Then("I successfully logged in")
    public void i_successfully_logged_in() throws InterruptedException {
        sauceDemo.successfullyLoggedIn();
        utilities.acceptAlertIfPresent();
    }
}
