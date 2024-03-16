package rahulshettyacademy.pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractClass.AbstractComponant;

public class CartPage extends AbstractComponant {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> orderedItemList;

	@FindBy(css = ".totalRow button")
	WebElement checkOutBtn;

	public Boolean isItemPresent(String productName) {
		Boolean match = orderedItemList.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
		return match;
	}

	public CheckOutPage clickCheckOutBtn() {
		checkOutBtn.click();
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
	}
}
