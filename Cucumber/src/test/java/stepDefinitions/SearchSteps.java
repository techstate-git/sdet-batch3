package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.SearchPage;
import utils.DriverManager;

public class SearchSteps {
    WebDriver driver = DriverManager.getDriver();
    SearchPage searchPage = new SearchPage(driver);

    @Then("I search product {string}")
    public void i_search_product(String productName) {
        searchPage.searchProduct(productName);
    }

    @Then("I sorted by {string}")
    public void sortProduct(String option) {
        searchPage.sortProduct(option);
        Assert.assertTrue(searchPage.verifyFilter());
    }

    @Then("I verify result contains {string}")
    public void i_verify_result_contains(String option) {
        searchPage.titleList(option);
    }

    @Given("I am on Mans Jackets screen")
    public void i_am_on_mans_jackets_screen() {
        driver.get("https://magento.softwaretestingboard.com/men/tops-men/jackets-men.html");
    }

    @Then("I apply color {string} filter")
    public void i_apply_filter(String color) {
        searchPage.setColorFilter(color);
    }
}











