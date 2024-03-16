package rahulshettyacademy.pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractClass.AbstractComponant;

public class OrderHistoryPage extends AbstractComponant {
	WebDriver driver;

	public OrderHistoryPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css="tr td:nth-child(3)")
	List<WebElement> orderedItemList;

	public Boolean isItemPresentInOrderHistory(String productName) {
		Boolean match = orderedItemList.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
		return match;
	}
}
