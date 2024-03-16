package rahulshettyacademy.tests;

import java.io.IOException;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;


import rahulshettyacademy.TestComponant.BaseTest;
import rahulshettyacademy.TestComponant.Retry;
import rahulshettyacademy.pageObject.CartPage;
import rahulshettyacademy.pageObject.ProductCatalogue;

public class ErrorValidation extends BaseTest {
	@Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException {
		// TODO Auto-generated method stub
		// WebDriverManager.ChromeDriver().setup();
		String productName = "ADIDAS ORIGINAL";
		lp.LoginApplication("lovely@gmail.com", "Love@123123");
		Assert.assertEquals("Incorrect email password.", lp.getErrorMessage());

	}
	@Test
	public void ProductErrorValidation() throws IOException {
		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue = lp.LoginApplication("anshika@gmail.com", "Iamking@000");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.isItemPresent("ADIDAS ORIGINALL");
		Assert.assertFalse(match);
	}
	

}
