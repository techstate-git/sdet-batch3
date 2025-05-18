package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MyAccountPage;
import utils.DriverManager;

public class LoginTest {
    WebDriver driver = DriverManager.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    MyAccountPage myAccountPage = new MyAccountPage(driver);

    @Given("I am on the magento login page")
    public void i_am_on_the_magento_login_page() {
        driver.get("https://magento.softwaretestingboard.com/customer/account/login");
    }

    @When("I am login with valid credentials")
    public void i_am_login_with_valid_credentials() {
        loginPage.login();
    }

    @Then("I am logged in as {string}")
                                //Beksultan
    public void i_am_logged_in(String user) {
        System.out.println("Logged in as " + user);
        myAccountPage.loginValidation(user);
    }
}
