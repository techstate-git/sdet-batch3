package stepDefinitions;

import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.MyAccountPage;
import utils.DriverManager;

public class OrdersSteps {
    WebDriver driver = DriverManager.getDriver();
    MyAccountPage myAccountPage = new MyAccountPage(driver);

    @Then("I am verify there is no previous orders")
    public void i_am_verify_there_is_no_previous_orders() {
        myAccountPage.noPrevOrders();
    }
}
