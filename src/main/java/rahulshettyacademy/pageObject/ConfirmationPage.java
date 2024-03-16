package rahulshettyacademy.pageObject;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractClass.AbstractComponant;

public class ConfirmationPage extends AbstractComponant {
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage;

	public boolean verifyConfirmationMessage(String message) throws IOException {
		boolean match = confirmationMessage.getText().equalsIgnoreCase(message);
		return match;
	}

}
