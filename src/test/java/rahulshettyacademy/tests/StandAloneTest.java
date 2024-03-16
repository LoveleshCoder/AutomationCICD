package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import rahulshettyacademy.pageObject.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// WebDriverManager.ChromeDriver().setup();
		// New Comments are added
		String productName = "ADIDAS ORIGINAL";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.findElement(By.id("userEmail")).sendKeys("lovely@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Love@123#123");
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='col-lg-4']")));
		List<WebElement> products = driver.findElements(By.cssSelector("[class*='col-lg-4']"));
		WebElement productSorted = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		productSorted.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List<WebElement> orderedItemList = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = orderedItemList.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		driver.findElement(By.cssSelector(".form-group input")).sendKeys("ind");
		List<WebElement> countryList = driver.findElements(By.cssSelector(".list-group-item"));
		List<WebElement> countrySelect = countryList.stream().filter(country -> country
				.findElement(By.cssSelector(".list-group-item span")).getText().equalsIgnoreCase("India"))
				.collect(Collectors.toList());
		countrySelect.get(0).click();
		driver.findElement(By.cssSelector(".actions a")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C:\\Users\\khata\\eclipse-workspace\\SeleniumFrameworkDesign/output.png"));
		String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		List<WebElement> orderIds = driver
				.findElements(By.xpath("//tr[@class='ng-star-inserted']/td[@class='em-spacer-1']/label"));
		orderIds.stream()
				.forEach(orderId -> System.out.println("Order ID for " + productName + " is " + orderId.getText()));
		driver.quit();
	}

}
