package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import functionLibrary.sharedFunctions;

public class ServiceCatalogPage extends sharedFunctions{
	
	private WebDriver driver;
	private ExtentTest test;
	
	@FindBy(xpath="//input[@title='Search']")
	private WebElement searchField;
	
	@FindBy(xpath="//button[@title='Search']")
	private WebElement searchButton;
	
	@FindBy(xpath="//span[text()='Cloud Services Production']")
	private WebElement cloudServicesProductionLink;
	
//	@FindBy(xpath="")
//	private WebElement ele;
	
	public void launchCloudServiceProductionForm() {
		new WebDriverWait(driver, 40).until(ExpectedConditions.visibilityOf(searchField));
		test.info("Launching Cloud Services Production page.");
		searchField.sendKeys("Cloud Services Production");
		searchButton.click();
		new WebDriverWait(driver, 40).until(ExpectedConditions.visibilityOf(cloudServicesProductionLink));
		cloudServicesProductionLink.click();
		test.pass("Launched Cloud Services Production page successfully.");
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	public ServiceCatalogPage(WebDriver driver, ExtentTest testInstance) {
		this.setDriver(driver);
		this.test = testInstance;
		PageFactory.initElements(driver, this);
	}
}
