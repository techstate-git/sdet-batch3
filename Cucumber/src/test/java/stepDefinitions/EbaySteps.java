package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.Ebay;
import utils.DriverManager;

public class EbaySteps {
    WebDriver driver = DriverManager.getDriver();
    Ebay ebay = new Ebay(driver);

    private String minPrice;
    private String maxPrice;

    @Given("I am on the ebay homepage")
    public void i_am_on_the_ebay_homepage() {
        driver.get("https://www.ebay.com/");
    }

    @When("I search for {string}")
    public void i_search_for(String product) {
        ebay.enterSearchTerm(product);
    }

    @Then("I should see search results containing {string}")
    public void i_should_see_search_results_containing(String product) {
        Assert.assertTrue("Search results do not contain the expected product!",
                ebay.verifyTotalResults(product));
    }

    @When("I apply a price filter from {string} to {string}")
    public void i_apply_a_price_filter_from_to(String minPrice, String maxPrice) {
        this.minPrice = minPrice; //Store the value
        this.maxPrice = maxPrice; //Store the value
        ebay.applyPriceFilter(minPrice, maxPrice);
    }

    @Then("I should see search results within the price range")
    public void i_should_see_search_results_within_the_price_range() {
        int min = Integer.parseInt(this.minPrice); //Reuse the stored value
        int max = Integer.parseInt(this.maxPrice); //Reuse the stored value
        Assert.assertTrue("Some results are outside the price range",
                ebay.verifyPriceRange(min, max));
    }
}
