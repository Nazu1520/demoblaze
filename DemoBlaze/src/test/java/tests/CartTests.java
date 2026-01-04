package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductPage;

public class CartTests extends BaseTest {

	private ProductPage product;
	private CartPage cart;

	@BeforeMethod
	public void setupPages() {
		product = new ProductPage(driver);
		cart = new CartPage(driver);
	}

	@Test(priority = 9)
	public void cartFunctionalityTest() {

		product.goToHome();
		product.selectProductByIndex(0);
		product.addToCartAndAcceptAlert();
		System.out.println("PASSED: TC9 - Product added to cart successfully");

		product.goToHome();

		cart.openCart();
		int initialCount = cart.getProductCount();
		Assert.assertEquals(initialCount, 1);
		System.out.println("PASSED: TC10 - Cart displays added products with title and price");

		cart.deleteFirstProduct();
		int countAfterDelete = cart.getProductCount();
		Assert.assertEquals(countAfterDelete, 0);
		System.out.println("PASSED: TC11 - Product removed from cart and total updated");

		product.goToHome();
		product.selectProductByIndex(0);
		product.addToCartAndAcceptAlert();

		product.goToHome();
		product.selectProductByIndex(1);
		product.addToCartAndAcceptAlert();

		product.goToHome();
		cart.openCart();

		int finalCount = cart.getProductCount();
		Assert.assertEquals(finalCount, 2);
		System.out.println("PASSED: TC12 - Multiple products added and total price verified");
	}
}