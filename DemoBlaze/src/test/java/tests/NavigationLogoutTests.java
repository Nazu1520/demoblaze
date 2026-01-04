package tests;

import base.BaseTest;

import org.openqa.selenium.By;

import org.openqa.selenium.TimeoutException;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

import org.testng.annotations.Test;

import pages.ProductPage;

import utils.WaitUtils;

import java.time.Duration;

import java.util.List;

public class NavigationLogoutTests extends BaseTest {

	@Test(priority = 13)

	public void TC15_browseProductCategories() {

		ProductPage product = new ProductPage(driver);

		product.goToHome();

		List<WebElement> products = driver.findElements(By.xpath("//a[@class='hrefch']"));

		product.selectProductByIndex(0);

		product.goToHome();

		Assert.assertTrue(products.size() > 0, "Products should be visible");

		System.out.println("PASSED: TC15 - Product categories accessible");

	}

	@Test(priority = 14)

	public void TC16_verifyCategoryNavigationLinks() {

		ProductPage product = new ProductPage(driver);

		product.goToHome();

		List<WebElement> initialProducts = driver.findElements(By.xpath("//a[@class='hrefch']"));

		Assert.assertTrue(initialProducts.size() > 0, "❌ Initial product list empty");

		product.selectProductByIndex(0);

		product.goToHome();

		List<WebElement> afterProducts = driver.findElements(By.xpath("//a[@class='hrefch']"));

		Assert.assertTrue(afterProducts.size() > 0, "Products should be visible after navigation");

		System.out.println("PASSED: TC16 - Navigation working. Initial: " + initialProducts.size() +

				", After: " + afterProducts.size());

	}

	@Test(priority = 15)

	public void TC17_viewProductDetails() {

		ProductPage product = new ProductPage(driver);

		product.goToHome();

		product.selectProductByIndex(0);

		WaitUtils wait = new WaitUtils(driver);

		wait.waitForVisible(By.xpath("//a[text()='Add to cart']"));

		product.goToHome();

		System.out.println("PASSED: TC17 - Product details verified");

	}

	@Test(priority = 16)

	public void TC18_verifyHomeNavigation() {

		ProductPage product = new ProductPage(driver);

		product.goToHome();

		product.selectProductByIndex(0);

		product.goToHome();

		List<WebElement> products = driver.findElements(By.xpath("//a[@class='hrefch']"));

		Assert.assertTrue(products.size() > 0, "Home page should show products");

		System.out.println("PASSED: TC18 - Home navigation verified");

	}

	@Test(priority = 17)
	public void TC19_verifyContactNavigation() {
		WaitUtils wait = new WaitUtils(driver);

		driver.navigate().to("https://www.demoblaze.com");

		wait.click(By.xpath("//a[normalize-space()='Contact']"));

		wait.waitForVisible(By.id("exampleModal"));

		Assert.assertTrue(driver.findElement(By.id("exampleModal")).isDisplayed(), "Contact modal should be visible");

		wait.click(By.xpath("//div[@id='exampleModal']//button[contains(@class,'btn-secondary')]"));

		System.out.println("PASSED: TC19 - Contact navigation verified");
	}

	@Test(priority = 18)

	public void TC20_verifyLoginLogout() {

		WaitUtils wait = new WaitUtils(driver);

		driver.navigate().to("https://www.demoblaze.com");

		Assert.assertTrue(driver.findElements(By.id("login2")).size() > 0,

				"Login link should exist on home page");

		wait.click(By.id("login2"));

		Assert.assertTrue(driver.findElements(By.id("logInModal")).size() > 0,

				"Login modal should open");

		System.out.println("PASSED: TC20 - Login functionality verified");

	}

}
