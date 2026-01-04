package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;
import java.util.List;
public class CartPage {
   private WebDriver driver;
   private WaitUtils wait;
   private By cartLink = By.id("cartur");
   private By cartRows = By.xpath("//tr[@class='success']");
   private By deleteBtn = By.xpath("(//a[text()='Delete'])[1]");
   private By placeOrderBtn = By.xpath("//button[text()='Place Order']");
   public CartPage(WebDriver driver) {
       this.driver = driver;
       this.wait = new WaitUtils(driver);
   }
   public void openCart() {
       wait.click(cartLink);
       wait.waitForVisible(cartRows);
   }
   public int getProductCount() {
       return driver.findElements(cartRows).size();
   }
   public void deleteFirstProduct() {
       int before = getProductCount();
       wait.click(deleteBtn);
       wait.waitForRowCountToReduce(cartRows, before);
   }
   public void clearCartIfAny() {
       List<WebElement> rows = driver.findElements(cartRows);
       for (WebElement row : rows) {
           wait.click(deleteBtn);
       }
   }
   public void clickPlaceOrder() {
       wait.click(placeOrderBtn);
   }
   
}