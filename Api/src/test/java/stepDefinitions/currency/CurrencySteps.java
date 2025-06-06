package stepDefinitions.currency;

import currency.models.SupportedCurrencies;
import currency.models.SupportedCurrencies.CurrencyInfo;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

import java.sql.SQLOutput;
import java.util.*;

import static currency.client.CurrencyController.getCurrency;

public class CurrencySteps {
    Response response;
    SupportedCurrencies supportedCurrencies;

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        response = getCurrency(endpoint);

        supportedCurrencies = response.as(SupportedCurrencies.class);
    }
    @Then("all currency codes should be distinct")
    public void all_currency_codes_should_be_distinct() {
        Map<String, CurrencyInfo> currencies = supportedCurrencies.getSupportedCurrenciesMap();
        Set<String> uniqueCodes = new HashSet<>();
        List<String> duplicatedValues = new ArrayList<>();

        for (CurrencyInfo info : currencies.values()) {
            String code = info.getCurrencyCode();
            //true, !true=false, !false=true

            //! + true =false            ! +  false                   = true
            //!   uniqueCodes.add(code), !    uniqueCodes.add(code)
            if (!uniqueCodes.add(code)) {
                duplicatedValues.add(code);
            }
        }
        System.out.println("Currency codes: " + uniqueCodes);
        System.out.println("Total currency codes: " + uniqueCodes.size());
        System.out.println("Duplicated currency codes: " + duplicatedValues.size());
    }

    @Then("all icon URLs should end with {string} and start with {string}")
    public void all_icon_ur_ls_should_end_with_and_start_with(String end, String start) {
        Map<String, CurrencyInfo> currencies = supportedCurrencies.getSupportedCurrenciesMap();

        for (CurrencyInfo info : currencies.values()) {
            String icon = info.getIcon();
            if (icon.endsWith(end) && icon.startsWith(start)) {
                System.out.println("Icon url is correct: " + icon);
            } else {
                System.out.println("Incorrect icon: " + icon);
            }
        }
    }

    @Then("all currencies must have a non-empty currency name")
    public void all_currencies_must_have_a_non_empty_currency_name() {
        Map<String, CurrencyInfo> currencies = supportedCurrencies.getSupportedCurrenciesMap();

        for (CurrencyInfo info : currencies.values()) {
            String currencyName = info.getCurrencyName();

            if (currencyName == null || currencyName.trim().isEmpty()) {
                System.out.println("Currency name is empty for: " + info.getCurrencyCode());
            } else {
                System.out.println("Currency name is not empty: " + currencyName);
            }
        }
    }

    @Then("currencies with status {string} should have availableUntil field is not empty")
    public void currencies_with_status_should_have_available_until_field_is_not_empty(String string) {
        Map<String, CurrencyInfo> currencies = supportedCurrencies.getSupportedCurrenciesMap();

        for (CurrencyInfo info : currencies.values()) {
            String status = info.getStatus();

            if (status.equalsIgnoreCase(string)) {
                Assert.assertFalse(info.getAvailableUntil().isEmpty());
            } else {
                System.out.println("Available until field is empty for: " + info.getCurrencyName());
            }
        }
    }
}














