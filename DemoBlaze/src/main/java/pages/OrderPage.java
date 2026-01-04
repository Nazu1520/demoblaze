package pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import utils.WaitUtils;

public class OrderPage {

    private WebDriver driver;

    private WaitUtils wait;

    private By placeOrderBtn = By.xpath("//button[text()='Place Order']");

    private By name = By.id("name");

    private By country = By.id("country");

    private By city = By.id("city");

    private By card = By.id("card");

    private By month = By.id("month");

    private By year = By.id("year");

    private By purchaseBtn = By.xpath("//button[text()='Purchase']");

   
    
    public OrderPage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WaitUtils(driver);

    }

    public void openPlaceOrder() {

        wait.click(placeOrderBtn);

    }

    public void fillValidOrderDetails() {

        wait.type(name, "Test User");

        wait.type(country, "India");

        wait.type(city, "Chennai");

        wait.type(card, "4111111111111111");

        wait.type(month, "12");

        wait.type(year, "2025");

    }

    public void clickPurchase() {

        wait.click(purchaseBtn);

    }
    private By closeOrderBtn = By.xpath("//div[@id='orderModal']//button[text()='Close']");

 
 public void closeOrderModal() {
     wait.click(closeOrderBtn);
     wait.waitForElementToDisappear(By.id("orderModal"));
 }

 public void confirmOrder() {
     wait.click(okButton);
     
     wait.waitForElementToDisappear(confirmationPopup);
 }
    private By confirmationPopup = By.cssSelector(".sweet-alert.showSweetAlert");
    private By confirmationText  = By.cssSelector(".sweet-alert.showSweetAlert");
    private By okButton          = By.cssSelector(".confirm.btn.btn-lg.btn-primary");
  
    public boolean isConfirmationPopupVisible() {
       try {
           return driver.findElement(confirmationPopup).isDisplayed();
       } catch (Exception e) {
           return false;
       }
    }
   
    public String getConfirmationMessage() {
       return driver.findElement(confirmationText).getText();
    }
    
    

}
 