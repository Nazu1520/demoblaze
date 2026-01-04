package tests;

import base.BaseTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignupPage;
import utils.WaitUtils;

public class SignupTests extends BaseTest {
	@BeforeMethod
	public void goToHomeLoggedOut() {
		driver.navigate().to("https://www.demoblaze.com");
		new WaitUtils(driver).waitForVisible(By.id("login2"));
	}

	@Test(priority = 6)
	public void TC06_verifySignupWithUniqueCredentials() {
		WaitUtils wait = new WaitUtils(driver);
		HomePage home = new HomePage(driver);
		home.openSignupModal();
		wait.waitForVisible(By.id("sign-username"));
		String randomUser = "user" + System.currentTimeMillis();
		SignupPage signup = new SignupPage(driver);
		signup.signup(randomUser, "pass123");
		wait.waitForAlert();
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		alert.accept();

		Assert.assertTrue(text.toLowerCase().contains("sign up"), "Expected sign up success message. Actual: " + text);
		System.out.println("PASSED: TC06 - Sign-up with unique user. Evidence: alert='" + text + "'");
	}

	@Test(priority = 7)
	public void TC07_verifySignupWithExistingUsername() {
		WaitUtils wait = new WaitUtils(driver);
		HomePage home = new HomePage(driver);
		home.openSignupModal();
		wait.waitForVisible(By.id("sign-username"));
		SignupPage signup = new SignupPage(driver);
		signup.signup("existingUser", "pass123"); // create this once using TC06
		wait.waitForAlert();
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		alert.accept();
		Assert.assertTrue(text.toLowerCase().contains("already"),
				"Expected 'user already exists' style message. Actual: " + text);
		System.out.println("PASSED: TC07 - Sign-up with existing username. Evidence: alert='" + text + "'");
	}

	@Test(priority = 8)
	public void TC08_verifySignupWithEmptyFields() {
		WaitUtils wait = new WaitUtils(driver);
		HomePage home = new HomePage(driver);
		home.openSignupModal();
		wait.waitForVisible(By.id("sign-username"));
		SignupPage signup = new SignupPage(driver);
		signup.signup("", "");
		wait.waitForAlert();
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		alert.accept();

		Assert.assertTrue(text.length() > 0, "Expected validation alert for empty fields, got empty text.");
		System.out.println("PASSED: TC08 - Sign-up with empty fields. Evidence: alert='" + text + "'");
	}
}