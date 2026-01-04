package tests;

import base.BaseTest;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import org.testng.Assert;

import org.testng.annotations.Test;

import pages.HomePage;

import utils.WaitUtils;

import java.util.List;

public class UiUxTests extends BaseTest {

	@Test(priority = 19)

	public void TC21_verifyAlignmentOfProductTiles() {

		WaitUtils wait = new WaitUtils(driver);

		HomePage home = new HomePage(driver);

		home.clickHome();

		wait.waitForVisible(By.id("tbodyid"));

		List<WebElement> tiles = driver.findElements(

				By.xpath("//div[@id='tbodyid']/div[contains(@class,'col-lg-4')]"));

		Assert.assertTrue(tiles.size() >= 2,

				"There should be at least two product tiles.");

		String firstClass = tiles.get(0).getAttribute("class");

		boolean allAligned = true;

		for (WebElement tile : tiles) {

			if (!tile.getAttribute("class").equals(firstClass)) {

				allAligned = false;

				break;

			}

		}

		Assert.assertTrue(allAligned,

				"All product tiles should share same grid class for alignment.");

		System.out.println("PASSED: TC21 - Alignment of product tiles. Evidence: all tiles class='" + firstClass + "'");

	}

	@Test(priority = 20)

	public void TC22_checkVisibilityOfActionButtons() {

		boolean loginVisible = driver.findElement(By.id("login2")).isDisplayed();

		boolean signupVisible = driver.findElement(By.id("signin2")).isDisplayed();

		boolean cartVisible = driver.findElement(By.id("cartur")).isDisplayed();

		Assert.assertTrue(loginVisible && signupVisible && cartVisible);

		System.out.println("PASSED: TC22 - Visibility of Login, Sign up, and Cart buttons");

	}

	@Test(priority = 21)

	public void TC23_checkFontConsistencyAcrossPages() {

		WaitUtils wait = new WaitUtils(driver);

		String homeFont = driver.findElement(By.tagName("body"))

				.getCssValue("font-family");

		HomePage home = new HomePage(driver);

		home.clickPhones();

		wait.waitForVisible(By.id("tbodyid"));

		String phonesFont = driver.findElement(By.tagName("body"))

				.getCssValue("font-family");

		Assert.assertEquals(homeFont, phonesFont);

		System.out.println("PASSED: TC23 - Font consistency. Evidence: font-family='" + homeFont + "'");

	}

}
