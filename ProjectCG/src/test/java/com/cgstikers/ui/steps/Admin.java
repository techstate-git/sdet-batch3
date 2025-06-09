package com.cgstikers.ui.steps;

import com.cgstikers.api.models.Products;
import com.cgstikers.api.models.Token;
import com.cgstikers.common.ConfigManager;
import com.cgstikers.ui.enums.ElementFinder;
import com.cgstikers.ui.enums.ElementLocator;
import com.cgstikers.ui.pages.AdminPage;
import com.cgstikers.ui.utils.DriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;

import java.util.List;

import static com.cgstikers.api.clients.ProductTypeController.getAllProducts;
import static com.cgstikers.api.clients.TokenRequest.generateToken;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Admin {
    private Token token;
    private ElementFinder finder;

    @When("I create a product with name {string}")
    public void i_create_a_product_with_name(String productName) {
        new AdminPage().createProduct(productName);
    }

    @When("I generated new authorization token")
    public void i_generate_token() {
        token = new Token(); // ✅ initialize
        token.setUsername(ConfigManager.get("admin.username"));
        token.setPassword(ConfigManager.get("admin.password"));
        token = generateToken(token).as(Token.class);
        ConfigManager.set("access.token", token.getAccessToken());
    }

    @Then("the product {string} should be created in the API")
    public void the_product_should_be_created_in_the_api(String productName) {
        List<Products> productsList = getAllProducts(ConfigManager.get("access.token")).as(new TypeRef<>() {});

        boolean found = false;
        for (Products product : productsList) {
            System.out.println("🔍 Checking product: " + product.getName());
            if (productName.equalsIgnoreCase(product.getName().trim())) {
                System.out.println("✅ Product found in API: " + product.getName());
                found = true;
                break;
            }
        }

        assertTrue(found, "❌ Product '" + productName + "' was NOT found in the API response.");
    }

    @When("I navigated to Product page")
    public void i_navigated_to_product_page() {
        if (finder == null) {
            finder = new ElementFinder(DriverManager.getDriver()); // ✅ Fix added
        }
        finder.find(ElementLocator.PRODUCTS_PAGE).click();
    }

    @Then("I delete all products")
    public void i_delete_all_products() {
        // TODO: implement logic
    }
}
