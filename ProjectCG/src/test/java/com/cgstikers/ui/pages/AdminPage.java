package com.cgstikers.ui.pages;

import com.cgstikers.api.models.Products;
import com.cgstikers.ui.enums.ElementFinder;
import com.cgstikers.ui.enums.ElementLocator;
import com.cgstikers.ui.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AdminPage {
    private final ElementFinder finder;
    private final WebDriver driver;

    public AdminPage() {
        WebDriver webDriver = DriverManager.getDriver();
        if (webDriver == null) {
            throw new IllegalStateException("❌ WebDriver is null. Did you forget to call DriverManager.initDriver()?");
        }
        this.driver = webDriver;
        this.finder = new ElementFinder(webDriver);
    }

    public void createProduct(String productName) {
        finder.find(ElementLocator.PRODUCTS_PAGE).click();
        finder.find(ElementLocator.ADD_PRODUCT).click();
        finder.find(ElementLocator.PRODUCT_LIST).click();
        finder.find(ElementLocator.PRODUCT_LIST_STICKER).click();
        finder.find(ElementLocator.PRODUCT_NAME).sendKeys(productName);
        finder.find(ElementLocator.PRODUCT_DESCRIPTION).sendKeys("TEST_DESCRIPTION");
        finder.find(ElementLocator.PRODUCT_PRICE).sendKeys("5");
        finder.find(ElementLocator.CREATE_BUTTON).click();
    }

    public void verifyProductCreated(String expectedProductName) {
        finder.find(ElementLocator.PRODUCTS_PAGE).click();

        List<WebElement> allProducts = finder.findAll(ElementLocator.PRODUCTS_TITLES);

        boolean found = false;
        for (WebElement product : allProducts) {
            String productText = product.getText();
            System.out.println("🔍 Checking product: " + productText);
            if (productText.equalsIgnoreCase(expectedProductName)) {
                System.out.println("Product found in UI: " + productText);
                found = true;
                break;
            }
        }
    }
}
