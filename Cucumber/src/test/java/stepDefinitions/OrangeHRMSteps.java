package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.OrangeHRM;
import utils.DriverManager;

import java.util.UUID;

public class OrangeHRMSteps {
    WebDriver driver = DriverManager.getDriver();
    OrangeHRM orangeHRM = new OrangeHRM(driver);

    @Given("I am on orangeHRM homePage")
    public void i_am_on_orange_hrm_home_page() {
        driver.get("https://opensource-demo.orangehrmlive.com/");

        System.out.println("TEST " + UUID.randomUUID());
    }

    @And("I enter login and password")
    public void i_enter_login_and_password() {
        orangeHRM.login();
    }

    @Then("I verify successfully logged in")
    public void i_verify_successfully_logged_in() {
        orangeHRM.successfullyLoggedIn();
    }

    @Then("I navigate to {string} page")
    public void i_navigate_to_page(String page) {
        orangeHRM.clickOnPage(page);
    }

    @Then("I change the profile picture")
    public void i_change_the_profile_picture() throws InterruptedException {
        orangeHRM.changeProfilePicture();
    }
}
