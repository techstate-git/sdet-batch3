package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchPage {
    WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "search")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@title='Search']")
    private WebElement searchBtn;

    @FindBy(xpath = "//select[@id='sorter']")
    private WebElement sortSelect;

    @FindBy(xpath = "//span[@class='price']")
    private List<WebElement> priceElements;

    @FindBy(xpath = "//strong[@class='product name product-item-name']")
    private List<WebElement> productsTitles;

    public void searchProduct(String productName) {
        searchInput.sendKeys(productName);
        searchBtn.click();
    }

    @FindBy(xpath = "//div[@class='filter-options-title' and .='Color']")
    private WebElement colorFilter;

    @FindBy(xpath = "//div[@class='filter-options-title' and .='Color']/following-sibling::div//div[@option-label='Black']")
    private WebElement blackColorOption;

    //"product name"
    public void sortProduct(String option) {
        Select select = new Select(sortSelect);

        switch (option) {
            case "price":
                select.selectByVisibleText("Price");
                break;
            case "product name":
                select.selectByVisibleText("Product Name");
                break;
        }
    }

    public boolean verifyFilter() {
        List<Double> prices = new ArrayList<>();

        for (WebElement price : priceElements) {
            String priceText = price.getText().replace("$", "");

            if (!priceText.isEmpty()) {
                prices.add(Double.parseDouble(priceText));
            }
        }

        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices, Collections.reverseOrder());

        if (!prices.equals(sortedPrices)) {
            System.out.println("The results are not sorted correctly");
            System.out.println("Actual prices from website: " + prices);
            System.out.println("Expected list of prices: " + sortedPrices);
            return false;
        }
        System.out.println("Actual prices from website: " + prices);
        System.out.println("Expected list of prices: " + sortedPrices);

        return true;
    }

    public void titleList(String option) {
        for (WebElement title : productsTitles) {
            String tt = title.getText();

            if (tt.contains(option)) {
                System.out.println("Correct titles " + tt);
            } else {
                System.out.println("Incorrect title " + tt);
            }
        }
    }

    public void setColorFilter(String color) {
        colorFilter.click();

        switch (color){
            case "black" : blackColorOption.click();
            break;
        }

    }
}





