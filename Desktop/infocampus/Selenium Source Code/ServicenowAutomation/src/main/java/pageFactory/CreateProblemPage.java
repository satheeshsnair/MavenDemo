package pageFactory;

import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import functionLibrary.sharedFunctions;
import utilities.ExcelUtility;

public class CreateProblemPage extends sharedFunctions  {
	
	private WebDriver driver;
	private ExtentTest test;
	
	@FindBy(xpath="//input[@id='problem.number']")
	private WebElement problemNumber;
	
	@FindBy(xpath="//input[@id='sys_display.problem.business_service']")
	private WebElement businessServiceField;
	
	@FindBy(xpath="//input[@id='sys_display.problem.cmdb_ci']")
	private WebElement configurationItemField;
	
//	@FindBy(xpath="")
//	private WebElement ele;
//	@FindBy(xpath="")
//	private WebElement ele;
//	@FindBy(xpath="")
//	private WebElement ele;
//	@FindBy(xpath="")
//	private WebElement ele;
	
	@FindBy(xpath="//button[@id='sysverb_update']")
	private WebElement updateBtn;
	
//	@FindBy(xpath="//select[@id='problem.u_problem_type']")
//	private WebElement problemTypeDdwn;
//	
//	@FindBy(xpath="//select[@id='problem.priority']")
//	private WebElement problemPriorityDdwn;
//	
//	@FindBy(xpath="(//span[@class='label-text' and text()='Configuration item']/ancestor::div[1]/following-sibling::div[1]//input)[last()]")
//	private WebElement configurationItemField;
//	
//	@FindBy(xpath="(//span[@class='label-text' and text()='Service Executive']/ancestor::div[1]/following-sibling::div[1]//input)[last()]")
//	private WebElement serviceExecutiveField;
//	
//	@FindBy(xpath="(//span[@class='label-text' and text()='Problem Manager Group']/ancestor::div[1]/following-sibling::div[1]//input)[last()]")
//	private WebElement problemManagerGroupField;
//	
//	@FindBy(xpath="(//span[@class='label-text' and text()='Assigned to']/ancestor::div[1]/following-sibling::div[1]//input)[last()]")
//	private WebElement assignedToField;
//	
//	@FindBy(xpath="(//span[@class='label-text' and text()='External Ticket Reference']/ancestor::div[1]/following-sibling::div[1]//input)[last()]")
//	private WebElement externalTicketRefField;
//	
//	@FindBy(xpath="(//span[@class='label-text' and text()='Originating Incident']/ancestor::div[1]/following-sibling::div[1]//input)[last()]")
//	private WebElement originatingIncidentField;
//	
//	@FindBy(xpath="(//span[@class='label-text' and text()='SI Point of Contact']/ancestor::div[1]/following-sibling::div[1]//input)[last()]")
//	private WebElement siPointOfContField;
//	
//	@FindBy(xpath="(//span[@class='label-text' and text()='Problem Requestor']/ancestor::div[1]/following-sibling::div[1]//input)[last()]")
//	private WebElement problemRequestorField;
//	
//	@FindBy(xpath="(//span[@class='label-text' and text()='Short description']/ancestor::div[1]/following-sibling::div[1]//input)[last()]")
//	private WebElement shortDescField;
//	
//	@FindBy(xpath="//span[@class='tab_caption_text' and text()='Notes']")
//	private WebElement notesTab;
//	
//	@FindBy(xpath="//span[@class='tab_caption_text' and text()='SE Approval']")
//	private WebElement approvalTab;
//	
//	@FindBy(xpath="//span[@class='tab_caption_text' and text()='Tower Approval']")
//	private WebElement towerApprovalTab;
//	
//	@FindBy(xpath="//textarea[@id='problem.work_notes']")
//	private WebElement workNotesField;
//	
//	@FindBy(xpath="//textarea[@id='problem.work_around']")
//	private WebElement workAroundField;
//	
//	@FindBy(xpath="(//span[@class='label-text' and text()='SE Approval Group']/ancestor::div[1]/following-sibling::div[1]//input)[last()]")
//	private WebElement seApprovalGrpField;
//	
//	@FindBy(xpath="(//span[@class='label-text' and text()='SE Approver']/ancestor::div[1]/following-sibling::div[1]//input)[last()]")
//	private WebElement seApproverField;
//	
//	@FindBy(xpath="(//span[@class='label-text' and text()='Tower Lead']/ancestor::div[1]/following-sibling::div[1]//input)[last()]")
//	private WebElement towerLeadField;
//	
//	@FindBy(xpath="//button[@id='sysverb_insert']")
//	private WebElement submitButton;
	
//	@FindBy(xpath="")
//	private WebElement ele;
//	@FindBy(xpath="")
//	private WebElement ele;
	
	public String createProblem() {
		LinkedHashMap<String, String> dataMap = new ExcelUtility().getTestData("Incident");
		driver.switchTo().frame("gsft_main");
		waitTime();
		String problem = problemNumber.getAttribute("value");
		test.info("launching create problem from the incident.");
		waitTime();
		businessServiceField.sendKeys(dataMap.get("Business service"));
		waitTime();
		new Actions(driver).sendKeys(Keys.ARROW_DOWN,Keys.ENTER).perform();
		waitTime();
		configurationItemField.clear();
		configurationItemField.sendKeys(dataMap.get("Configuration item"));
		waitTime();
		waitTime();
		new Actions(driver).sendKeys(Keys.ARROW_DOWN,Keys.ENTER).perform();
		waitTime();
		updateBtn.click();
		waitTime();
		test.pass("Problem created successfully : "+problem);
		driver.switchTo().defaultContent();
		return problem;
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

	public CreateProblemPage(WebDriver driver, ExtentTest testInstance) {
		this.setDriver(driver);
		this.test = testInstance;
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}

}
