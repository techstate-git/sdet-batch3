package com.cgstikers.ui.steps;

import com.cgstikers.ui.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Authorization {
    private final LoginPage loginPage;

    public Authorization(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    @Given("I login with username {string} and password {string}")
    public void i_login_with_username_and_password(String username, String password) throws InterruptedException {
        loginPage.login(username, password);
    }
    @Then("I should see the dashboard page")
    public void i_should_see_the_dashboard_page() {

    }
}
