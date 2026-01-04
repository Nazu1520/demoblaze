package base;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass

    public void setUpClass() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.demoblaze.com");

    }

    @AfterClass

    public void tearDownClass() {

        if (driver != null) {

            driver.quit();

     

        }

    }

}
 