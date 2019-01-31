package pageFactory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import functionLibrary.sharedFunctions;

public class MyApprovalPage extends sharedFunctions{
	
	private WebDriver driver;
	private ExtentTest test;
	
	@FindBy(xpath="//table[@id='sysapproval_approver_table']//span[text()='Search']/..")
	private WebElement enableSearchFields;

	@FindBy(xpath="//table[@id='sysapproval_approver_table']//td[@name='sysapproval']//input")
	private WebElement searchReqNumberField;
	
	@FindBy(xpath="//a[span[text()='Preview']]")
	private WebElement requestPreviewIcon;
	
	@FindBy(id="approve")
	private WebElement approveBtn;
	
//	@FindBy(xpath="")
//	private WebElement ele;
	
	public void searchAndPreviewRequest(String reqNumber) {
		driver.switchTo().frame("gsft_main");
		test.info("Searching request number on my approvals page.");
		try {
			searchReqNumberField.sendKeys(reqNumber, Keys.ENTER);
		}
		catch(WebDriverException e) {
			enableSearchFields.click();
			waitTime();
			searchReqNumberField.sendKeys(reqNumber, Keys.ENTER);
		}
		requestPreviewIcon.click();
		test.pass("Search for request number completed successfully.");
		driver.switchTo().defaultContent();
	}
	
	public void approveRqst() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("gsft_main");
		new WebDriverWait(driver, 40).until(ExpectedConditions.visibilityOf(approveBtn));
		test.info("Clicking on Approve button.");
		approveBtn.click();
		test.pass("Clicked on Approve button successfully.");
		driver.switchTo().defaultContent();
	}
	
	public void waitTime() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public MyApprovalPage(WebDriver driver, ExtentTest testInstance) {
		this.setDriver(driver);
		this.test = testInstance;
		PageFactory.initElements(driver, this);
	}

}
