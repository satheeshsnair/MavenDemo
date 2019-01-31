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

public class NewIncidentPage extends sharedFunctions{
	
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
	
	@FindBy(xpath="//select[@id='incident.impact']")
	private WebElement incidentImpactDdwn;
	
	@FindBy(xpath="//select[@id='incident.urgency']")
	private WebElement incidentUrgencyDdwn;
	
	@FindBy(xpath="//span[@class='label-text' and text()='Category']/ancestor::div[1]/following-sibling::div[1]/select")
	private WebElement categorySelect;
	
	@FindBy(xpath="//span[@class='label-text' and text()='Subcategory']/ancestor::div[1]/following-sibling::div[1]/select")
	private WebElement subcategorySelect;
	
	@FindBy(xpath="(//span[@class='label-text' and text()='Short description']/ancestor::div[1]/following-sibling::div[1]//input)[last()]")
	private WebElement shortDescriptionField;
	
	@FindBy(xpath="//button[text()='Submit']")
	private WebElement submitButton;
	
	@FindBy(xpath="//input[@id='incident.number']")
	private WebElement incidentNum;
	
//	@FindBy(xpath="")
//	private WebElement ;
//	@FindBy(xpath="")
//	private WebElement ;
	
	public String submitIncident() {
		driver.switchTo().frame("gsft_main");
		waitTime();
		test.info("Getting data from the data sheet.");
		LinkedHashMap<String, String> dataMap = new ExcelUtility().getTestData("Incident");
		test.info("Entering data in the mandatory fields.");
		String incident = incidentNum.getAttribute("value");
		callerField.sendKeys(dataMap.get("Caller"), Keys.TAB);
		configurationItemField.sendKeys(dataMap.get("Configuration item"), Keys.TAB);
		selFromDdwn(categorySelect, dataMap.get("Category"));
		selFromDdwn(subcategorySelect, dataMap.get("Subcategory"));
		selFromDdwn(incidentImpactDdwn, dataMap.get("Impact"));
		selFromDdwn(incidentUrgencyDdwn, dataMap.get("Urgency"));
		shortDescriptionField.sendKeys(dataMap.get("Short description"), Keys.TAB);
		assignmentGroupField.sendKeys(dataMap.get("Assignment group"), Keys.TAB);
		assignedToField.sendKeys(dataMap.get("Assigned To"));
		waitTime();
		new Actions(driver).sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();
		test.pass("Entered test data successfully.");
		submitButton.click();
		waitTime();
		test.pass("Submitted incident #"+incident+" successfully.");
		driver.switchTo().defaultContent();
		return incident;
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
	public NewIncidentPage(WebDriver driver, ExtentTest testInstance) {
		this.setDriver(driver);
		this.test = testInstance;
		PageFactory.initElements(driver, this);
	}

}
