package com.cgstikers.ui.enums;

import org.openqa.selenium.By;

public enum ElementLocator {
    // Login Page elements
    USERNAME(By.xpath("(//input)[1]")),
    PASSWORD(By.xpath("(//input)[2]")),
    LOGIN_BUTTON(By.xpath("(//button)[2]")),

    //Admin Panel
    PRODUCTS_PAGE(By.xpath("//span[text()='Products']")),
    ADD_PRODUCT(By.xpath("//button[text()='New Product']")),
    PRODUCT_LIST(By.xpath("//*[@id='mui-component-select-productTypeId']")),
    PRODUCT_LIST_STICKER(By.xpath("//li[text()='Stickers']")),
    PRODUCT_NAME(By.xpath("//input[@name='name']")),
    PRODUCT_DESCRIPTION(By.xpath("//textarea[@name='description']")),
    PRODUCT_PRICE(By.xpath("//input[@name='price']")),
    CREATE_BUTTON(By.xpath("//button[text()='Create']")),
    PRODUCTS_TITLES(By.xpath("//h6")),

    DELETE_BUTTONS(By.xpath("//button[contains(@class, 'MuiIconButton-colorError')]"));

    private final By by;

    ElementLocator(By by) {
        this.by = by;
    }

    public By getBy() {
        return by;
    }
}
