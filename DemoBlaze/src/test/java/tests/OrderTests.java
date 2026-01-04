package tests;

import base.BaseTest;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.OrderPage;
import pages.ProductPage;

public class OrderTests extends BaseTest {
	private ProductPage product;
	private CartPage cart;
	private OrderPage order;

	@BeforeMethod
	public void setupOrder() {
		product = new ProductPage(driver);
		cart = new CartPage(driver);
		order = new OrderPage(driver);

		product.goToHome();
		product.selectProductByIndex(0);
		product.addToCartAndAcceptAlert();
		product.goToHome(); // This navigate helps clear any lingering states
		cart.openCart();
	}

	@Test(priority = 10)
	public void TC13_placeOrderWithValidDetails() {
		order.openPlaceOrder();
		order.fillValidOrderDetails();
		order.clickPurchase();

		Assert.assertTrue(order.isConfirmationPopupVisible(), "❌ Confirmation popup not visible");
		String message = order.getConfirmationMessage();
		Assert.assertTrue(message.contains("Thank you"), "❌ 'Thank you' text missing");
		
		order.confirmOrder(); // Closes the modal to prevent intercepting next tests
		System.out.println("PASSED: TC13 - Placed order with valid details");
	}

	@Test(priority = 11)
	public void TC14_placeOrderWithEmptyForm() {
		order.openPlaceOrder();
		order.clickPurchase();
		
		// Empty form validation typically triggers a native browser alert
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		alert.accept();
		
		Assert.assertTrue(
				alertText.toLowerCase().contains("please fill") || alertText.toLowerCase().contains("required"),
				"❌ Validation alert expected. Got: " + alertText);

		System.out.println("PASSED: TC14 - Form validation working");
	}

	@Test(priority = 12)
	public void TC15_verifyOrderConfirmationDetails() {
		order.openPlaceOrder();
		order.fillValidOrderDetails();
		order.clickPurchase();
		
		
		Assert.assertTrue(order.isConfirmationPopupVisible(), "❌ Confirmation popup not visible");
		String message = order.getConfirmationMessage();
		
		System.out.println("Confirmation Data: " + message);
		Assert.assertTrue(message.contains("Thank you"), "❌ 'Thank you' missing");
		Assert.assertTrue(message.contains("Id") || message.contains("Amount"), "❌ Order details missing");

		order.confirmOrder(); 
		System.out.println("PASSED: TC15 - Order confirmation verified");
	}
}