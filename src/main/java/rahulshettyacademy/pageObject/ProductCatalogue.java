
package rahulshettyacademy.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractClass.AbstractComponant;

public class ProductCatalogue extends AbstractComponant {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List<WebElement> products =
	// driver.findElements(By.cssSelector("[class*='col-lg-4']"));
	// PageFactory
	@FindBy(css = "[class*='col-lg-4']") // This aanotation will get driver detail from PageFactory.initElements(driver,
											// this);
	List<WebElement> products;

	@FindBy(css = ".ng-animating") // This aanotation will get driver detail from PageFactory.initElements(driver,
									// this);
	WebElement spinner;

	By productBy = By.cssSelector("[class*='col-lg-4']");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By waitBefore = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement productSorted = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return productSorted;
	}

	public void addProductToCart(String ProductName) {
		WebElement prod = getProductByName(ProductName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(waitBefore);
		// ng-animating
		waitForElementToDisappear(spinner);
	}
}
