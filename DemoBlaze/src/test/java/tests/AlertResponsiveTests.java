package tests;

import base.BaseTest;

import java.util.List;

import org.openqa.selenium.Alert;

import org.openqa.selenium.Dimension;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import org.testng.Assert;

import org.testng.annotations.Test;

import pages.HomePage;

import pages.ProductPage;

import utils.WaitUtils;

public class AlertResponsiveTests extends BaseTest {

	@Test(priority = 22)
	public void TC24_testAlertAndPopupStyling() {
		ProductPage product = new ProductPage(driver);
		product.goToHome();
		product.selectProductByIndex(0);
		product.addToCartAndAcceptAlert();
		List<WebElement> navbarLinks = driver.findElements(By.id("nava"));
		Assert.assertTrue(navbarLinks.size() > 0, "Should return to home page after alert (navbar visible)");
		product.goToHome();
		System.out.println("PASSED: TC24 - Add to cart alert verified");
	}

	@Test(priority = 23)

	public void TC25_testHorizontalScrolling() {

		driver.manage().window().setSize(new Dimension(480, 800));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		boolean hasHorizontalScroll = (Boolean) js.executeScript(

				"return document.body.scrollWidth > window.innerWidth;");

		Assert.assertFalse(hasHorizontalScroll,

				"Page should not require horizontal scroll on small width.");

		System.out.println("PASSED: TC25 - Responsive design (no horizontal scrolling) on 480x800 window");

	}

}
