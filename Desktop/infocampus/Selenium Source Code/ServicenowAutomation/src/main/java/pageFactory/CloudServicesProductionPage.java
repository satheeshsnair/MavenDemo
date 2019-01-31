package pageFactory;

import java.util.LinkedHashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import functionLibrary.sharedFunctions;
import utilities.ExcelUtility;

public class CloudServicesProductionPage extends sharedFunctions{
	
	private WebDriver driver;
	private ExtentTest test;
	
	@FindBy(xpath="//label[contains(text(),'Affected User')]/following-sibling::span")
	private WebElement affectedUserLink;
	
	@FindBy(xpath="//label[contains(text(),'Affected User')]/following-sibling::input[contains(@id,'search')]")
	private WebElement affectedUserSearchField;
	
	@FindBy(xpath="//label[contains(text(),'Contact Phone')]/following-sibling::span//input")
	private WebElement contactPhoneField;
	
	@FindBy(xpath="//label[text()='Server Owner']/following-sibling::span")
	private WebElement serverOwnerLink;
	
	@FindBy(xpath="//label[text()='Server Owner']/following-sibling::input[contains(@id,'search')]")
	private WebElement serverOwnerSearchField;
	
	@FindBy(xpath="//label[text()='Application Owner']/following-sibling::span")
	private WebElement applicationOwnerLink;
	
	@FindBy(xpath="//label[text()='Application Owner']/following-sibling::input[contains(@id,'search')]")
	private WebElement applicationOwnerSearchField;
	
	@FindBy(xpath="//label[contains(text(),'Application Owner Email')]/following-sibling::span//input")
	private WebElement applicationOwnerEmailField;
	
	@FindBy(xpath="//label[contains(text(),'Application Owner Phone')]/following-sibling::span//input")
	private WebElement applicationOwnerPhoneField;
	
	@FindBy(xpath=" //label[text()='Cost Center to Charge']/following-sibling::span")
	private WebElement costCenterToChargeLink;
	
	@FindBy(xpath="//label[text()='Cost Center to Charge']/following-sibling::input[contains(@id,'search')]")
	private WebElement costCenterToChargeSearchField;
	
	@FindBy(xpath="//label[text()='Cost Center Approval Manager']/following-sibling::span")
	private WebElement costCenterApprovalManagerLink;
	
	@FindBy(xpath="//label[text()='Cost Center Approval Manager']/following-sibling::input[contains(@id,'search')]")
	private WebElement costCenterApprovalManagerSearchField;
	
	@FindBy(xpath="//label[text()='Charge Code']/following-sibling::span")
	private WebElement chargeCodeLink;
	
	@FindBy(xpath="//label[text()='Charge Code']/following-sibling::input[contains(@id,'search')]")
	private WebElement chargeCodeSearchField;
	
	@FindBy(xpath="//label[contains(text(),'Project Name')]/following-sibling::span//input")
	private WebElement projectNameField;
	
	@FindBy(xpath="//label[contains(text(),'Order Title')]/following-sibling::span//input")
	private WebElement orderTitleField;
	
	@FindBy(xpath="//label[text()='Application ID']/following-sibling::span")
	private WebElement applicationIdLink;
	
	@FindBy(xpath="//label[text()='Application ID']/following-sibling::input[contains(@id,'search')]")
	private WebElement applicationIdSearchField;
	
	@FindBy(xpath="//label[contains(text(),'Feasibility Study ID')]/following-sibling::span//input")
	private WebElement feasibilityStudyIdField;
	
	@FindBy(xpath="//label[text()='Server Size']/following-sibling::span")
	private WebElement serverSizeLink;
	
	@FindBy(xpath=".//*[@id='s2id_autogen19_search']")
	private WebElement serverSizeSearchField;
	
	@FindBy(xpath="//label[text()='Server Type']/following-sibling::span")
	private WebElement serverTypeLink;
	
	@FindBy(xpath=".//*[@id='s2id_autogen20_search']")
	private WebElement serverTypeSearchField;
	
	@FindBy(xpath="//label[text()='Operating System']/following-sibling::span")
	private WebElement operatingSystemLink;
	
	@FindBy(xpath=".//*[@id='s2id_autogen21_search']")
	private WebElement operatingSystemSearchField;
	
	@FindBy(xpath="//button[text()='Submit']")
	private WebElement submitButton;

	@FindBy(xpath="//*[contains(text(),'Requested Items')]/../following-sibling::div//td[@data-field='number']")
	private WebElement requestNumber;
	
//	@FindBy(xpath="")
//	private WebElement ele;
	
	public String submitCloudServicesProductionForm() {
		test.info("Getting data from the data sheet.");
		LinkedHashMap<String, String> dataMap = new ExcelUtility().getTestData("CloudServicesProduction");
		test.info("Entering test data in Cloud Services Production Form.");
		waitAndScrollToViewElement(affectedUserLink);
		affectedUserLink.click();
		searchAndSelect(affectedUserSearchField, dataMap.get("Affected User"));
		contactPhoneField.sendKeys(dataMap.get("Contact Person"));
		serverOwnerLink.click();
		searchAndSelect(serverOwnerSearchField, dataMap.get("Server Owner"));
		applicationOwnerLink.click();
		searchAndSelect(applicationOwnerSearchField, dataMap.get("Application Owner"));
//		applicationOwnerEmailField.sendKeys("");
		applicationOwnerPhoneField.sendKeys(dataMap.get("Application Owner Phone"));
		waitAndScrollToViewElement(costCenterToChargeLink);
		costCenterToChargeLink.click();
		searchAndSelect(costCenterToChargeSearchField, dataMap.get("Cost Center to Charge"));
		costCenterApprovalManagerLink.click();
		searchAndSelect(costCenterApprovalManagerSearchField, dataMap.get("Cost Center Approval Manager"));
		chargeCodeLink.click();
		searchAndSelect(chargeCodeSearchField, dataMap.get("Charge Code"));
		waitAndScrollToViewElement(projectNameField);
		projectNameField.sendKeys(dataMap.get("Project Name"));
		orderTitleField.sendKeys(dataMap.get("Order Title"));
		applicationIdLink.click();
		searchAndSelect(applicationIdSearchField, dataMap.get("Application ID"));
		feasibilityStudyIdField.sendKeys(dataMap.get("Feasibility Study ID"));
		waitAndScrollToViewElement(serverSizeLink);
		serverSizeLink.click();
		clickAndSearch(serverSizeLink, dataMap.get("Server Size"));
		serverTypeLink.click();
		clickAndSearch(serverTypeLink, dataMap.get("Server Type"));
		operatingSystemLink.click();
		clickAndSearch(operatingSystemLink, dataMap.get("Operating System"));
		test.pass("Entered details successfully.");
		waitAndScrollToViewElement(submitButton);
		submitButton.click();
		waitAndScrollToViewElement(requestNumber);
		System.out.println("Req# "+requestNumber.getText());
		test.pass("Cloud services request <"+requestNumber.getText()+"> submitted successfully.");
		return requestNumber.getText();
	}
	
	public void waitTime() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void searchAndSelect(WebElement searchField, String textToBeSearch) {
		searchField.sendKeys(textToBeSearch);
		waitTime();
		searchField.sendKeys(Keys.ENTER);
		waitTime();
	}
	public void clickAndSearch(WebElement searchField, String textToBeSearch) {
		new Actions(driver).click(searchField).sendKeys(textToBeSearch).perform();
		waitTime();
		new Actions(driver).sendKeys(Keys.ENTER).perform();
		waitTime();
	}
	public void waitForElement(WebElement ele) {
		new WebDriverWait(driver, 40).until(ExpectedConditions.visibilityOf(ele));
	}
	public void waitAndScrollToViewElement(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		new WebDriverWait(driver, 40).until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	public CloudServicesProductionPage(WebDriver driver, ExtentTest testInstance) {
		this.setDriver(driver);
		this.test = testInstance;
		PageFactory.initElements(driver, this);
	}

}
