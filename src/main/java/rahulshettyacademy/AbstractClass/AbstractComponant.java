package rahulshettyacademy.AbstractClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageObject.CartPage;
import rahulshettyacademy.pageObject.OrderHistoryPage;

public class AbstractComponant {

	WebDriver driver;

	public AbstractComponant(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartClick;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement ordersClick;

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void waitForElementToDisappear(WebElement ele) {
		// Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public CartPage goToCartPage() {
		cartClick.click();
		CartPage check = new CartPage(driver);
		return check;
	}
	
	public OrderHistoryPage goToOrderHistory() {
		ordersClick.click();
		OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
		return orderHistoryPage;
	}

	
}
