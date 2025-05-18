package pages;

import io.cucumber.java.eo.Do;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Ebay {
    WebDriver driver;

    public Ebay(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "gh-ac")
    private WebElement searchBox;

    @FindBy(id = "gh-search-btn")
    private WebElement searchButton;

    @FindBy(xpath = "//h1[@class='srp-controls__count-heading']")
    private WebElement totalResults;

    @FindBy(xpath = "//input[@aria-label='Minimum Value in $']")
    private WebElement minPriceInput;

    @FindBy(xpath = "//input[@aria-label='Maximum Value in $']")
    private WebElement maxPriceInput;

    @FindBy(xpath = "//button[@aria-label='Submit price range']")
    private WebElement applyButton;

    @FindBy(xpath = "//span[@class='s-item__price']")
    private List<WebElement> priceElements;

    public void enterSearchTerm(String product) {
        searchBox.clear();
        searchBox.sendKeys(product);
        searchButton.click();
    }

    public boolean verifyTotalResults(String product) {
        return totalResults.isDisplayed() && totalResults.getText().contains(product);
    }

    public void applyPriceFilter(String minPrice, String maxPrice) {
        minPriceInput.clear();
        minPriceInput.sendKeys(minPrice);

        maxPriceInput.clear();
        maxPriceInput.sendKeys(maxPrice);

        applyButton.click();
    }

    public boolean verifyPriceRange(int minPrice, int maxPrice) {
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replaceAll("[^0-9.to]", "").trim();

            //Check if priceText in empty or invalid
            if (priceText.isEmpty()) {
                System.out.println("Skipping an item with no price displayed!");
                continue;
            }

            double price;

            try {
                //Check if price is a range
                //priceText = "184.61 to 368.36"
                if (priceText.contains("to")) {
                    //String[] prices = [184.61, 368.36]
                    String[] prices = priceText.split("\\s*to\\s*");

                    if (prices.length != 2) {
                        System.out.println("Invalid format");
                        return false;
                    }

                    double price1 = Double.parseDouble(prices[0].trim());
                    double price2 = Double.parseDouble(prices[1].trim());

                    //Check if either price in hte range is within the specified range
                    if ((price1 >= minPrice && price1 <= maxPrice) || (price2 >= minPrice && price2 <= maxPrice)) {
                        System.out.println("Valid price range: " + price1 + " to " + price2);
                        continue;
                    } else {
                        System.out.println("Price range not within limits: "  + price1 + " to " + price2);
                        continue;
                    }
                } else {
                    //Single price scenario
                    price = Double.parseDouble(priceText);

                    if (price < minPrice || price > maxPrice) {
                        System.out.println("Invalid single price within the range: " + price);
                        return false;
                    }

                    System.out.println("Valid single price within range: " + price);
                }
            } catch (NumberFormatException e) {
                System.out.println("Skipping an item with an invalid price: " + priceText);
                continue;
            }
        }

        return true;
    }
}










