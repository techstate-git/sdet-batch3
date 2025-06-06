package com.cgstikers.ui.steps;

import com.cgstikers.api.clients.TokenRequest;
import com.cgstikers.api.models.Products;
import com.cgstikers.api.models.Token;
import com.cgstikers.common.ConfigManager;
import com.cgstikers.ui.enums.ElementFinder;
import com.cgstikers.ui.enums.ElementLocator;
import com.cgstikers.ui.pages.AdminPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.cgstikers.api.clients.ProductTypeController.getAllProducts;
import static com.cgstikers.api.clients.TokenRequest.generateToken;

public class Admin {
    Token token = new Token();
    ElementFinder finder;

    @When("I create a product with name {string}")
    public void i_create_a_product_with_name(String productName) {
        new AdminPage().createProduct(productName);
    }

    @When("I generated new authorization token")
    public void i_generate_token() {
        token.setUsername(ConfigManager.get("admin.username"));
        token.setPassword(ConfigManager.get("admin.password"));
        token = generateToken(token).as(Token.class);

        ConfigManager.set("access.token", token.getAccessToken());
    }

    @Then("the product {string} should be created in the API")
    public void the_product_should_be_created_in_the_api(String productName) {
        List<Products> productsList = getAllProducts(ConfigManager.get("access.token")).as(new TypeRef<>() {});

        /*Products matchedProduct = productsList.stream()
                .filter(product -> productName.equalsIgnoreCase(product.getName().trim()))
                .findFirst()
                .orElse(null);

        if (matchedProduct != null) {
            System.out.println("✅ Product found in API: " + matchedProduct.getName());
        } else {
            Assert.fail("❌ Product '" + productName + "' was NOT found in the API response.");
        }*/

        boolean found = false;

        for (Products product : productsList) {
            System.out.println("🔍 Checking product: " + product.getName());
            if (productName.equalsIgnoreCase(product.getName().trim())) {
                System.out.println("✅ Product found in API: " + product.getName());
                found = true;
                break;
            }
        }

        Assert.assertTrue("❌ Product '" + productName + "' was NOT found in the API response.", found);
    }

    @When("I navigated to Product page")
    public void i_navigated_to_product_page() {
        finder.find(ElementLocator.PRODUCTS_PAGE).click();
    }
    @Then("I delete all products")
    public void i_delete_all_products() {

    }
}
