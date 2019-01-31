package pageFactory;

import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import functionLibrary.sharedFunctions;
import utilities.ExcelUtility;

public class OpenIncidentPage extends sharedFunctions{
	
	private WebDriver driver;
	private ExtentTest test;
	
	@FindBy(xpath="//table[@id='incident_table']//span[contains(text(),'search')]/..")
	private WebElement enableSearchFields;

	@FindBy(xpath="//table[@id='incident_table']//input[@id='incident_table_header_search_control']")
	private WebElement searchReqNumberField;
	
	@FindBy(xpath="//span[text()='Resolution Information']")
	private WebElement resolutionInfoTab;
	
	@FindBy(xpath="//a[contains(@data-original-title,'Preview')]")
	private WebElement incidentPreviewIcon;
	
	@FindBy(xpath="//select[@id='incident.close_code']")
	private WebElement resolutionCode;
	
	@FindBy(xpath="//textarea[@id='incident.close_notes']")
	private WebElement resolutionNote;
	
	@FindBy(xpath="//button[@id='resolve_incident']")
	private WebElement resolveButton;

	@FindBy(xpath="//button[@id='close_incident']")
	private WebElement closeIncidentBtn;
	
	@FindBy(xpath="//div[@class='container-fluid']")
	private WebElement header;
	
	@FindBy(xpath="//div[@id='context_1']/div[text()='Create Problem']")
	private WebElement createProblemLink;
	
	@FindBys({ @FindBy(xpath="//tr[contains(@id,'row_incident.task_sla')]/td[3]")})
	private List<WebElement> slaList;
	
	@FindBy(xpath="//span[@aria-controls='incident.task_sla.task_list']")
	private WebElement slaTasksTab;
	
	@FindBy(xpath="//a[text()='Open Record']")
	private WebElement openRecordBtn;
	
	public void searchAndPreviewIncident(String incidentNum) {
		driver.switchTo().frame("gsft_main");
		test.info("Searching incident number on open incident page.");
		try {
			searchReqNumberField.clear();
			searchReqNumberField.sendKeys(incidentNum, Keys.ENTER);
		}
		catch(WebDriverException e) {
			enableSearchFields.click();
			waitTime();
			searchReqNumberField.clear();
			searchReqNumberField.sendKeys(incidentNum, Keys.ENTER);
		}
		incidentPreviewIcon.click();
		waitTime();
		openRecordBtn.click();
		test.pass("Search for incident number completed successfully.");
		driver.switchTo().defaultContent();
	}
	
	public void resolveIncident(String incidentNum) {
		searchAndPreviewIncident(incidentNum);
		waitTime();
		driver.switchTo().frame("gsft_main");
		test.info("Resolving incident.");
		test.info("Clicking on resolution info tab.");
		LinkedHashMap<String, String> dataMap = new ExcelUtility().getTestData("Incident");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", resolutionInfoTab);
		resolutionInfoTab.click();
		waitTime();
		test.info("Selecting resolution code.");
//		new Select(resolutionCode).selectByVisibleText(dataMap.get("Resolution code"));
		new Select(resolutionCode).selectByIndex(2);
		resolutionNote.sendKeys(dataMap.get("Resolution notes"));
		waitTime();
		test.info("Clicking on Resolve button.");
		resolveButton.click();
		waitTime();
		test.pass("Incident resolved successfully.");
		driver.switchTo().defaultContent();
	}
	
	public void closeIncident(String incidentNum) {
		searchAndPreviewIncident(incidentNum);
		driver.switchTo().frame("gsft_main");
		waitTime();
		test.info("Closing incident.");
		closeIncidentBtn.click();
		waitTime();
		test.pass("Incident closed successfully.");
		driver.switchTo().defaultContent();
	}
	
	public void launchCreateProblem(String incidentNum) {
		searchAndPreviewIncident(incidentNum);
		driver.switchTo().frame("gsft_main");
		waitTime();
		test.info("launching create problem from the incident.");
		new Actions(driver).moveToElement(header).contextClick().perform();
		waitTime();
		createProblemLink.click();
		waitTime();
		test.pass("Create problem page launched successfully.");
		driver.switchTo().defaultContent();
	}
	
	public void getSLAs(String incidentNum) {
		searchAndPreviewIncident(incidentNum);
		driver.switchTo().frame("gsft_main");
		waitTime();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", slaTasksTab);
		slaTasksTab.click();
		waitTime();
		test.info("Getting list of SLA's from the incident.");
		if(slaList.size()>0) {
			for(WebElement ele : slaList) {
				String sla = ele.getText();
				test.pass("SLA associated with the incident :"+sla);
				waitTime();
			}
		}
		else {
			test.pass("No SLA is associated with the incident.");
		}
		waitTime();
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
	public OpenIncidentPage(WebDriver driver, ExtentTest testInstance) {
		this.setDriver(driver);
		this.test = testInstance;
		PageFactory.initElements(driver, this);
	}
}

