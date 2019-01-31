package pageFactory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import functionLibrary.sharedFunctions;

public class RequestedItemsPage extends sharedFunctions{
	private WebDriver driver;
	private ExtentTest test;
	
	@FindBy(xpath="//table[@id='sc_req_item_table']//input[@aria-label='Search number']")
	private WebElement searchReqNumberField;
	
	@FindBy(xpath="//a[span[text()='Preview']]")
	private WebElement requestPreviewIcon;
	
//	@FindBy(xpath="")
//	private WebElement ele;
	
	public void searchAndPreviewRequest(String reqNumber) {
		test.info("Serarching request number on Requested Items page.");
		searchReqNumberField.sendKeys(reqNumber, Keys.ENTER);
		test.pass("Search for request number completed successfully.");
		test.info("Previewing the request number on Requested Items page.");
		requestPreviewIcon.click();
		test.pass("Preview for request number completed successfully.");
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	public RequestedItemsPage(WebDriver driver, ExtentTest testInstance) {
		this.setDriver(driver);
		this.test = testInstance;
		PageFactory.initElements(driver, this);
	}
}
