package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.bouncycastle.asn1.dvcs.Data;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponant.BaseTest;
import rahulshettyacademy.pageObject.CartPage;
import rahulshettyacademy.pageObject.CheckOutPage;
import rahulshettyacademy.pageObject.ConfirmationPage;
import rahulshettyacademy.pageObject.OrderHistoryPage;
import rahulshettyacademy.pageObject.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ADIDAS ORIGINAL";
	
	@Test(dataProvider = "getData", groups = "Purchase")
	public void submitOrder(HashMap<String, String> input) throws IOException {
		// TODO Auto-generated method stub
		// WebDriverManager.ChromeDriver().setup();
		ProductCatalogue productCatalogue = lp.LoginApplication(input.get("mail"), input.get("password"));

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.isItemPresent(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.clickCheckOutBtn();

		checkOutPage.selectCountry("india");
		ConfirmationPage details = checkOutPage.submitOrder();
 
		boolean verification = details.verifyConfirmationMessage("THANKYOU FOR THE ORDER.");
		Assert.assertTrue(verification);

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		// ADIDAS ORIGINAL

		ProductCatalogue productCatalogue = lp.LoginApplication("lovely@gmail.com", "Love@123#123");
		OrderHistoryPage OrderPage = productCatalogue.goToOrderHistory();
		Boolean match = OrderPage.isItemPresentInOrderHistory(productName);
		Assert.assertTrue(match);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException{
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurcahseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		/*HashMap<String, String> map = new HashMap<String, String>();
		map.put("mail", "lovely@gmail.com");
		map.put("password", "Love@123#123");
		map.put("product", "ADIDAS ORIGINAL");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("mail", "anshika@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("product", "ZARA COAT 3");*/
	}

}
