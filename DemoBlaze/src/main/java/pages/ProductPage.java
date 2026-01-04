package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

public class ProductPage {

    private WebDriver driver;
    private WaitUtils wait;
    private By productLinks = By.xpath("//a[@class='hrefch']");
    private By addToCartBtn = By.xpath("//a[text()='Add to cart']");
    private By homeLink = By.id("nava");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public void goToHome() {
        try {
            wait.click(homeLink);
        } catch (Exception e) {
            // Fallback to JS click if a modal is intercepting the physical click
            wait.clickJS(homeLink);
        }
        wait.waitForElements(productLinks);
    }

    public void selectProductByIndex(int index) {
        wait.waitForElements(productLinks);
        List<WebElement> products = driver.findElements(productLinks);
        if (products.size() == 0) {
            throw new RuntimeException("❌ No products found. Are you on Home page?");
        }
        if (index >= products.size()) {
            throw new RuntimeException("❌ Index " + index + " out of range. Found " + products.size() + " products");
        }
        products.get(index).click();
    }

    public void addToCart() {
        wait.click(addToCartBtn);
    }

    public void acceptAlert() {
        wait.acceptAlert();
    }

    public void addToCartAndAcceptAlert() {
        addToCart();
        acceptAlert();
    }
}