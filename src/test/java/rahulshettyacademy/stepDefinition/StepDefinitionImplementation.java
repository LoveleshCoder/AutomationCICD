package rahulshettyacademy.stepDefinition;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponant.BaseTest;
import rahulshettyacademy.pageObject.CartPage;
import rahulshettyacademy.pageObject.CheckOutPage;
import rahulshettyacademy.pageObject.ConfirmationPage;
import rahulshettyacademy.pageObject.LandingPage;
import rahulshettyacademy.pageObject.ProductCatalogue;

public class StepDefinitionImplementation extends BaseTest{
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage details;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws Exception {
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		productCatalogue = landingPage.LoginApplication(username, password);
	}
	
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) {
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	//@And 
	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.isItemPresent(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.clickCheckOutBtn();
		checkOutPage.selectCountry("india");
		details = checkOutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_on_ConfirmationPage(String string) throws Exception {
		boolean verification = details.verifyConfirmationMessage(string);
		Assert.assertTrue(verification);
		driver.close();
	}

	@Then("{string} message is displayed")
	public void message_is_displayed(String stringArg) {
		Assert.assertEquals(stringArg , landingPage.getErrorMessage());
		driver.close();
	}
	
	
}
