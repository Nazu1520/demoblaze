package pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    private By loginUsername = By.id("loginusername");

    private By loginPassword = By.id("loginpassword");

    private By loginButton   = By.xpath("//button[text()='Log in']");

    public LoginPage(WebDriver driver) {

        this.driver = driver;

    }

    public void login(String username, String password) {

        driver.findElement(loginUsername).clear();

        driver.findElement(loginUsername).sendKeys(username);

        driver.findElement(loginPassword).clear();

        driver.findElement(loginPassword).sendKeys(password);

        driver.findElement(loginButton).click();

    }

}
 