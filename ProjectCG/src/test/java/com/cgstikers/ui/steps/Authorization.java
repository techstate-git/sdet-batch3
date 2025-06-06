package com.cgstikers.ui.steps;

import com.cgstikers.ui.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Authorization {
    LoginPage loginPage = new LoginPage();

    @Given("I login with username {string} and password {string}")
    public void i_login_with_username_and_password(String username, String password) {
        loginPage.login(username, password);
    }
    @Then("I should see the dashboard page")
    public void i_should_see_the_dashboard_page() {

    }
}
