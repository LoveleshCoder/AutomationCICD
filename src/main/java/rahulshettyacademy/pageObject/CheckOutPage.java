package rahulshettyacademy.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractClass.AbstractComponant;

public class CheckOutPage extends AbstractComponant {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".actions a")
	WebElement finalSubmit;

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".action__submit")
	WebElement submit;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;

	By waitForCountryListAppearance = By.cssSelector(".ta-results");

	By waitForFinalOrderDetails = By.cssSelector(".hero-primary");

	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(waitForCountryListAppearance);
		selectCountry.click();
	}

	public ConfirmationPage submitOrder() {
		finalSubmit.click();
		waitForElementToAppear(waitForFinalOrderDetails);
		ConfirmationPage details = new ConfirmationPage(driver);
		return details;
	}
}
