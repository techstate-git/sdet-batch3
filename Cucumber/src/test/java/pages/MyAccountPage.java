package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
    WebDriver driver;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='box box-information']//div[@class='box-content']/p")
    private WebElement welcomeUser;

    @FindBy(xpath = "//a[.='My Orders']")
    private WebElement myOrders;

    @FindBy(xpath = "//div[@class='message info empty']/span")
    private WebElement noOrdersMessage;


                                //Beksultan
    public void loginValidation(String user) {
        String welcome = welcomeUser.getText();
        System.out.println(welcome);
        Assert.assertTrue(welcome.contains(user));
    }

    public void noPrevOrders() {
        myOrders.click();
        Assert.assertTrue(noOrdersMessage.isDisplayed());
    }
}
