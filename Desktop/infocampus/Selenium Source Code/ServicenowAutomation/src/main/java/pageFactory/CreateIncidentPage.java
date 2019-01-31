package pageFactory;

import java.util.LinkedHashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;

import functionLibrary.sharedFunctions;
import utilities.ExcelUtility;

public class CreateIncidentPage extends sharedFunctions{
	
	private WebDriver driver;
	private ExtentTest test;

	@FindBy(xpath="(//span[@class='label-text' and text()='Caller']/ancestor::div[1]/following-sibling::div[1]//input)[last()]")
	private WebElement callerField;
	
	@FindBy(xpath="(//span[@class='label-text' and text()='Assignment group']/ancestor::div[1]/following-sibling::div[1]//input)[last()]")
	private WebElement assignmentGroupField;
	
	@FindBy(xpath="(//span[@class='label-text' and text()='Assigned to']/ancestor::div[1]/following-sibling::div[1]//input)[last()]")
	private WebElement assignedToField;
	
	@FindBy(xpath="(//span[@class='label-text' and text()='Configuration item']/ancestor::div[1]/following-sibling::div[1]//input)[last()]")
	private WebElement configurationItemField;
	
	@FindBy(xpath="//span[@class='label-text' and text()='Category']/ancestor::div[1]/following-sibling::div[1]/select")
	private WebElement categorySelect;
	
	@FindBy(xpath="//span[@class='label-text' and text()='Subcategory']/ancestor::div[1]/following-sibling::div[1]/select")
	private WebElement subcategorySelect;
	
	@FindBy(xpath="(//span[@class='label-text' and text()='Short description']/ancestor::div[1]/following-sibling::div[1]//input)[last()]")
	private WebElement shortDescriptionField;
	
	@FindBy(xpath="//span[text()='Closure Information']")
	private WebElement closureInfoTab;
	
	@FindBy(xpath="//select[contains(@id,'categorization_tier_1')]")
	private WebElement resolutionCatT1select;
	
	@FindBy(xpath="//select[contains(@id,'categorization_tier_2')]")
	private WebElement resolutionCatT2select;
	
	@FindBy(xpath="//select[contains(@id,'categorization_tier_3')]")
	private WebElement resolutionCatT3select;
	
	@FindBy(xpath="//select[contains(@id,'cause_code')]")
	private WebElement causeCodeSelect;
	
	@FindBy(xpath="//select[contains(@id,'close_code')]")
	private WebElement resolutionCodeSelect;
	
	@FindBy(xpath="//select[contains(@id,'resolution_method')]")
	private WebElement resolutionMethodSelect;
	
	@FindBy(xpath="//button[@id='resolve_incident_custom']")
	private WebElement resolveButton;
	
	@FindBy(xpath="//button[text()='Submit']")
	private WebElement submitButton;
	
	@FindBy(xpath="(//table[@id='incident_table']//tr[contains(@id,'row_incident')])[1]/td[3]/a")
	private WebElement incidentNum;
	
	public void submitIncident() {
		driver.switchTo().frame("gsft_main");
		test.info("Getting data from the data sheet.");
		LinkedHashMap<String, String> dataMap = new ExcelUtility().getTestData("Incident_NewRecord");
		test.info("Entering data in the mandatory fields.");
		callerField.sendKeys(dataMap.get("Caller"), Keys.TAB);
		configurationItemField.sendKeys(dataMap.get("Configuration item"), Keys.TAB);
		selFromDdwn(categorySelect, dataMap.get("Category"));
		selFromDdwn(subcategorySelect, dataMap.get("Subcategory"));
		shortDescriptionField.sendKeys(dataMap.get("Short description"), Keys.TAB);
		assignmentGroupField.sendKeys(dataMap.get("Assignment group"), Keys.TAB);
		assignedToField.sendKeys(dataMap.get("Assigned To"));
		waitTime();
		new Actions(driver).sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();
		test.info("Clicking on resolve button.");
		resolveButton.click();
		test.pass("Error message generated successfully for the missing fields.");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", closureInfoTab);
		test.info("Entering data in remaining mandatory fields.");
		closureInfoTab.click();
		selFromDdwn(resolutionCatT1select, dataMap.get("Resolution categorization tier 1"));
		selFromDdwn(resolutionCatT2select, dataMap.get("Resolution categorization tier 2"));
		selFromDdwn(resolutionCatT3select, dataMap.get("Resolution categorization tier 3"));
		selFromDdwn(causeCodeSelect, dataMap.get("Cause code"));
		selFromDdwn(resolutionCodeSelect, dataMap.get("Resolution code"));
		selFromDdwn(resolutionMethodSelect, dataMap.get("Resolution method"));
		test.pass("Entered test data successfully.");
		submitButton.click();
		waitTime();
		test.pass("Submitted incident #"+incidentNum.getText()+" successfully.");
		driver.switchTo().defaultContent();
	}
	
	public void selFromDdwn(WebElement ele, String dataToSel) {
		new Select(ele).selectByVisibleText(dataToSel);
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
	public CreateIncidentPage(WebDriver driver, ExtentTest testInstance) {
		this.setDriver(driver);
		this.test = testInstance;
		PageFactory.initElements(driver, this);
	}
}
