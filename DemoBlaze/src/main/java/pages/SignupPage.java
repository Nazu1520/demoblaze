package pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

public class SignupPage {

    WebDriver driver;

    private By signupUsername = By.id("sign-username");

    private By signupPassword = By.id("sign-password");

    private By signupButton   = By.xpath("//button[text()='Sign up']");

    public SignupPage(WebDriver driver) {

        this.driver = driver;

    }

    public void signup(String username, String password) {

        driver.findElement(signupUsername).clear();

        driver.findElement(signupUsername).sendKeys(username);

        driver.findElement(signupPassword).clear();

        driver.findElement(signupPassword).sendKeys(password);

        driver.findElement(signupButton).click();

    }

}
 