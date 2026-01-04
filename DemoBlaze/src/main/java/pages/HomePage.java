package pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;

    private By loginNav   = By.id("login2");

    private By signupNav  = By.id("signin2");

    private By cartNav    = By.id("cartur");

    private By homeNav    = By.xpath("//a[text()='Home ']");

    private By contactNav = By.xpath("//a[text()='Contact']");

    public HomePage(WebDriver driver) {

        this.driver = driver;

    }

    public void openLoginModal()  { driver.findElement(loginNav).click(); }

    public void openSignupModal() { driver.findElement(signupNav).click(); }

    public void openCart()        { driver.findElement(cartNav).click(); }

    public void clickHome()       { driver.findElement(homeNav).click(); }

    public void clickContact()    { driver.findElement(contactNav).click(); }

    public void clickPhones()     { driver.findElement(By.linkText("Phones")).click(); }

    public void clickLaptops()    { driver.findElement(By.linkText("Laptops")).click(); }

    public void clickMonitors()   { driver.findElement(By.linkText("Monitors")).click(); }

    public void clickFirstProduct() {

        driver.findElement(

                By.xpath("(//div[@id='tbodyid']//div[contains(@class,'card')])[1]//a[contains(@class,'hrefch')]\r\n"
                		+ " ")

        ).click();

    }

}
 