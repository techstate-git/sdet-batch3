package com.cgstikers.api.steps;

import com.cgstikers.api.models.Products;
import com.cgstikers.common.ConfigManager;
import com.cgstikers.ui.enums.ElementLocator;
import com.cgstikers.ui.pages.AdminPage;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.cgstikers.api.clients.ProductTypeController.*;
import static com.cgstikers.api.clients.ProductTypeController.updateProduct;

public class ProductUI {
    Products products = new Products();

    @Then("I create product {string} from the API")
    public void i_create_product_from_the_api(String productName) {
        products.setName(productName);
        products.setDescription("Sticker");
        products.setPrice(15);
        products.setIsActive(true);
        products.setProductTypeId(8);

        //Dimensions
        Products.Dimension dim = new Products.Dimension();
        dim.setWidth(10);
        dim.setHeight(15);
        products.setDimensions(List.of(dim));

        //Discounts
        Products.Discount discount = new Products.Discount();
        discount.setQuantity(0);
        discount.setPercentage(0);
        products.setDiscounts(List.of(discount));

        createProduct(ConfigManager.get("access.token"), products).prettyPrint();
    }

    @When("I verify a product with name {string} is created in UI")
    public void i_verify_a_product_with_name_is_created_in_ui(String productName) {
        new AdminPage().verifyProductCreated(productName);
    }

    @Then("I create {int} Bulk products")
    public void i_create_bulk_products(int count) {
        for (int i = 1; i <= count; i++) {
            System.out.println("Creating order Bulk: " + i);
            createBulkProducts(i);
        }
    }

    @Then("I updated bulk sticker with name containing {string}")
    public void i_updated_bulk_sticker_with_name_containing(String productName) {
        Faker faker = new Faker();
        List<Products> productsList = getAllProducts(ConfigManager.get("access.token")).as(new TypeRef<>() {});
        List<Products> idsToUpdate = new ArrayList<>();

        //Identify name is contains "Bulk"
        for (Products product : productsList) {
            String name = product.getName();

            if (name != null && name.contains(productName)) {
                System.out.println("Marked for update: " + name + " ID: " + product.getId());
                idsToUpdate.add(product);
            }
        }

        //All products with name Bulk
        for (Products product : idsToUpdate) {
            String newBookName = faker.book().title() + " Sticker";
            String newDescriptionName = faker.book().author() + " Sticker";

            createProductBody(newBookName, product.getId(), newDescriptionName);
        }
    }

    public void createBulkProducts(int index) {
        products.setName("B-Bulk " + index);
        products.setDescription("Sticker");
        products.setPrice(15);
        products.setIsActive(true);
        products.setProductTypeId(8);

        //Dimensions
        Products.Dimension dim = new Products.Dimension();
        dim.setWidth(10);
        dim.setHeight(15);
        products.setDimensions(List.of(dim));

        //Discounts
        Products.Discount discount = new Products.Discount();
        discount.setQuantity(0);
        discount.setPercentage(0);
        products.setDiscounts(List.of(discount));

        createProduct(ConfigManager.get("access.token"), products).prettyPrint();
    }

    public void createProductBody(String name, int id, String description) {
        products.setName(name);
        products.setDescription(description);
        products.setPrice(15);
        products.setIsActive(true);
        products.setProductTypeId(8);

        //Dimensions
        Products.Dimension dim = new Products.Dimension();
        dim.setWidth(10);
        dim.setHeight(15);
        products.setDimensions(List.of(dim));

        //Discounts
        Products.Discount discount = new Products.Discount();
        discount.setQuantity(0);
        discount.setPercentage(0);
        products.setDiscounts(List.of(discount));

        updateProduct(ConfigManager.get("access.token"), id, products).prettyPrint();
    }
}















