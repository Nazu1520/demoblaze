package tests;

import base.BaseTest;

import org.openqa.selenium.Alert;

import org.openqa.selenium.By;

import org.testng.Assert;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import pages.HomePage;

import pages.LoginPage;

import utils.WaitUtils;

public class LoginTests extends BaseTest {

	@BeforeMethod

	public void goToLoggedOutHome() {

		driver.navigate().to("https://www.demoblaze.com");

		new WaitUtils(driver).waitForVisible(By.id("login2"));

	}

	@Test(priority = 1)

	public void TC01_verifyLoginWithValidCredentials() {

		WaitUtils wait = new WaitUtils(driver);

		HomePage home = new HomePage(driver);

		LoginPage loginPage = new LoginPage(driver);

		home.openLoginModal();

		wait.waitForVisible(By.id("loginusername"));

		loginPage.login("validUserone", "validPass");

		wait.waitForVisible(By.id("logout2"));

		boolean loggedIn = driver.getPageSource().contains("Welcome validUser");

		Assert.assertTrue(loggedIn, "User should be logged in with valid credentials.");

		System.out.println("PASSED: TC01 - Valid login. Evidence: 'Welcome validUser' visible.");
		driver.findElement(By.id("logout2")).click();

		wait.waitForVisible(By.id("login2"));

		System.out.println("INFO: Logged out after TC01.");

	}

	@Test(priority = 2)

	public void TC02_verifyLoginWithIncorrectPassword() {

		WaitUtils wait = new WaitUtils(driver);

		HomePage home = new HomePage(driver);

		LoginPage loginPage = new LoginPage(driver);

		home.openLoginModal();

		wait.waitForVisible(By.id("loginusername"));

		loginPage.login("validUser", "wrongPass");

		wait.waitForAlert();

		Alert alert = driver.switchTo().alert();

		String msg = alert.getText();

		alert.accept();

		Assert.assertTrue(msg.toLowerCase().contains("wrong")

				|| msg.toLowerCase().contains("not"),

				"Alert should indicate wrong password. Actual: " + msg);

		System.out.println("PASSED: TC02 - Wrong password. Evidence: alert='" + msg + "'");

	}

	@Test(priority = 3)

	public void TC03_verifyLoginWithEmptyFields() {

		WaitUtils wait = new WaitUtils(driver);

		HomePage home = new HomePage(driver);

		LoginPage loginPage = new LoginPage(driver);

		home.openLoginModal();

		wait.waitForVisible(By.id("loginusername"));

		loginPage.login("", "");

		wait.waitForAlert();

		Alert alert = driver.switchTo().alert();

		String msg = alert.getText();

		alert.accept();

		Assert.assertTrue(msg.length() > 0, "Validation alert should appear for empty fields.");

		System.out.println("PASSED: TC03 - Empty fields. Evidence: alert='" + msg + "'");

	}

	@Test(priority = 4)

	public void TC04_verifyLoginWithInvalidEmailFormat() {

		WaitUtils wait = new WaitUtils(driver);

		HomePage home = new HomePage(driver);

		LoginPage loginPage = new LoginPage(driver);

		home.openLoginModal();

		wait.waitForVisible(By.id("loginusername"));

		loginPage.login("user.com", "pass123");

		wait.waitForAlert();

		Alert alert = driver.switchTo().alert();

		String msg = alert.getText();

		alert.accept();

		Assert.assertTrue(msg.length() > 0, "Validation alert should appear for invalid email.");

		System.out.println("PASSED: TC04 - Invalid email format. Evidence: alert='" + msg + "'");

	}

	@Test(priority = 5)

	public void TC05_verifyPasswordFieldMasksInput() {

		WaitUtils wait = new WaitUtils(driver);

		HomePage home = new HomePage(driver);

		home.openLoginModal();

		wait.waitForVisible(By.id("loginpassword"));

		String type = driver.findElement(By.id("loginpassword")).getAttribute("type");

		Assert.assertEquals(type, "password", "Password field should mask input.");

		System.out.println("PASSED: TC05 - Password masking. Evidence: type='" + type + "'");

	}

}
