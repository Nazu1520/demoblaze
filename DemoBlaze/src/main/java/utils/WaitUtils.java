package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class WaitUtils {

	private WebDriver driver;
	private WebDriverWait wait;

	public WaitUtils(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	public void click(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public void clickJS(By locator) {
		WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
	}

	public void type(By locator, String text) {
		WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		el.clear();
		el.sendKeys(text);
	}

	public String getText(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
	}

	public boolean isElementVisible(By locator) {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
		} catch (TimeoutException e) {
			return false;
		}
	}

	public void waitForElements(By locator) {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public List<WebElement> getElements(By locator) {
		waitForElements(locator);
		return driver.findElements(locator);
	}

	public Alert waitForAlert() {
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert() {
		wait.until(ExpectedConditions.alertIsPresent()).accept();
	}

	public WebElement waitForVisible(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForRowCountToReduce(By locator, int before) {
		wait.until(driver -> driver.findElements(locator).size() < before);
	}

	public void waitForModalToBeVisible(String modalId) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(modalId)));
	}

	public void waitForModalToClose(String modalId) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(modalId)));
	}

	public void waitForElementToDisappear(By locator) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException ignored) {
		}
	}
}