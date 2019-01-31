package pageFactory;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.google.common.collect.Iterators;

import functionLibrary.sharedFunctions;
import utilities.ExcelUtility;

public class CreateChangeRequestPage extends sharedFunctions{
	
	private WebDriver driver;
	private ExtentTest test;
	private LinkedHashMap<String, String> dataMap;
	
	@FindBy(xpath="//table[@id='change_request_table']//input[@aria-label='Search number']")
	private WebElement searchReqNumberField;
	
	@FindBy(xpath="//a[span[text()='Preview']]")
	private WebElement requestPreviewIcon;
	
	@FindBy(xpath="//table[@id='change_request_table']//span[text()='Search']/..")
	private WebElement enableSearchFields;
	
	@FindBy(xpath="//a[contains(text(),'Normal')]")
	private WebElement normalChangeRequestLink;
	
	@FindBy(xpath="//input[@id='change_request.number']")
	private WebElement changeRequestNum; //value
	
	@FindBy(xpath="//input[@id='change_request.short_description']")
	private WebElement shortDescriptionField;
	
	@FindBy(xpath="//select[@id='change_request.approval']")
	private WebElement approvalStatus;
	
	@FindBy(xpath="//select[@id='change_request.state']")
	private WebElement reqState;
	
	@FindBy(xpath="//input[@id='sys_display.change_request.assigned_to']")
	private WebElement assignedToField;
	
	@FindBy(xpath="//div[contains(@class,'fieldmsg notification')]")
	private WebElement fieldErrorMsg;
	
	@FindBy(xpath="//select[@id='change_request.risk']")
	private WebElement riskDdwn;
	
	@FindBy(xpath="(//button[text()='Fill Out Risk Assessment'])[1]")
	private WebElement fillOutRiskAssBtn;
	
	@FindBy(xpath="//iframe[@class='gb_iframe']")
	private WebElement riskAssesFrame;
	
	@FindBys({ @FindBy(xpath="(//div[contains(@id,'QUESTION')])[1]//input")})
	private List<WebElement> riskAssesQuestion1;
	
	@FindBys({ @FindBy(xpath="(//div[contains(@id,'QUESTION')])[3]//input")})
	private List<WebElement> riskAssesQuestion2;
	
	@FindBys({ @FindBy(xpath="(//div[contains(@id,'QUESTION')])[5]//input")})
	private List<WebElement> riskAssesQuestion3;
	
	@FindBys({ @FindBy(xpath="(//div[contains(@id,'QUESTION')])[7]//input")})
	private List<WebElement> riskAssesQuestion4;
	
	@FindBy(xpath="//button[@id='post_survey']")
	private WebElement riskAssesSurveySubmitBtn;
	
	@FindBy(xpath="(//button[text()='Execute Risk Calculation'])[1]")
	private WebElement executeRiskCalBtn;
	
	@FindBy(xpath="//button[text()='Request Approval']")
	private WebElement requestApprovalBtn;
	
	//----------Change Request--------------
	
	@FindBys({ @FindBy(xpath="//span[@tab_caption='Change Request']//span[@mandatory='true' and contains(@aria-label,'Mandatory - must')]/following-sibling::span[1]")})
	private List<WebElement> changeReqMandatoryLabels;
	
	@FindBy(xpath="//input[@id='sys_display.change_request.assignment_group']")
	private WebElement assignmentGroupField;
	
	@FindBy(xpath="//input[@id='sys_display.change_request.u_change_management_group']")
	private WebElement changeMgntGroupField;
	
	@FindBy(xpath="//input[@id='sys_display.change_request.cmdb_ci']")
	private WebElement configItemField;
	
	@FindBy(xpath="//select[@id='change_request.u_cmdb_update_required']")
	private WebElement cmdbDdwn;
	
	@FindBy(xpath="//input[@id='sys_display.change_request.u_primary_area']")
	private WebElement primaryAreaField;
	
	@FindBy(xpath="//select[@id='change_request.u_environment']")
	private WebElement environmentDdwn;
	
	@FindBy(xpath="//select[@id='change_request.category']")
	private WebElement categoryDdwn;
	
	@FindBy(xpath="//select[@id='change_request.u_subcategory']")
	private WebElement subcategoryDdwn;
	
	@FindBy(xpath="//select[@id='change_request.u_action']")
	private WebElement actionDdwn;
	
	@FindBy(xpath="//select[@id='change_request.u_timing_reason']")
	private WebElement timingRsnDdwn;
	
	//-----------Planning Tab--------------
	
	@FindBy(xpath="//span[@class='tab_caption_text' and text()='Planning']")
	private WebElement planningTab;
	
	@FindBys({ @FindBy(xpath="//span[@tab_caption='Planning']//span[@mandatory='true' and contains(@aria-label,'Mandatory - must')]/following-sibling::span[1]")})
	private List<WebElement> planningTabLabels;
	
	@FindBy(xpath="//textarea[@id='change_request.justification']")
	private WebElement preImptestEvidenceField;
	
	@FindBy(xpath="//textarea[@id='change_request.implementation_plan']")
	private WebElement implementationPlanField;
	
	@FindBy(xpath="//textarea[@id='change_request.test_plan']")
	private WebElement postImplementationPlanField;
	
	@FindBy(xpath="//textarea[@id='change_request.backout_plan']")
	private WebElement backoutPlan;
	
	//-------------Schedule Tab-----------------
	
	@FindBy(xpath="//span[@class='tab_caption_text' and text()='Schedule']")
	private WebElement scheduleTab;
	
	@FindBys({ @FindBy(xpath="//span[@tab_caption='Schedule']//span[@mandatory='true' and contains(@aria-label,'Mandatory - must')]/following-sibling::span[1]")})
	private List<WebElement> scheduleTabLabels;
	
	@FindBy(xpath="//input[@id='change_request.start_date']")
	private WebElement plannedStartDate; //29.10.2018 14:40:57
	
	@FindBy(xpath="//input[@id='change_request.end_date']")
	private WebElement plannedEndDate; //29.10.2018 14:40:57
	
	@FindBy(xpath="//input[@id='change_request.work_start']")
	private WebElement actualStartDate;
	
	@FindBy(xpath="//input[@id='change_request.work_end']")
	private WebElement actualEndDate;
	
	//--------------Closure Information--------------
	
	@FindBy(xpath="//span[@class='tab_caption_text' and text()='Closure Information']")
	private WebElement closureInfoTab;
	
	@FindBys({ @FindBy(xpath="//span[@tab_caption='Closure Information']//span[@mandatory='true' and contains(@aria-label,'Mandatory - must')]/following-sibling::span[1]")})
	private List<WebElement> closureInfoLabels;
	
	@FindBy(xpath="//select[@id='change_request.close_code']")
	private WebElement closeCodeDdwn;
	
	@FindBy(xpath="//textarea[@id='change_request.close_notes']")
	private WebElement closeNotesField;
	
	@FindBy(xpath="//button[text()='Save']")
	private WebElement saveButton;
	
	@FindBy(xpath="//button[@id='sysverb_update']")
	private WebElement updateButton;
	
	@FindBy(xpath="//button[text()='Implement']")
	private WebElement implementButton;
	
	@FindBy(xpath="//button[text()='Review']")
	private WebElement reviewButton;
	
//	@FindBy(xpath="")
//	private WebElement ;
	
	@FindBy(xpath="//span[@class='tab_caption_text' and contains(text(),'Approvers')]")
	private WebElement approversTab;
	
	@FindBys({ @FindBy(xpath="//tr[contains(@id,'row_change_request.sysapproval_approver')]/td[3]/a")})
	private List<WebElement> approvalState;
	
	@FindBys({ @FindBy(xpath="//tr[contains(@id,'row_change_request.sysapproval_approver')]/td[4]/a")})
	private List<WebElement> approverName;
	
//	@FindBy(xpath="")
//	private WebElement ;
//	@FindBys({ @FindBy(xpath="")})
//	private List<WebElement> ;
	
	public LinkedHashMap<String, WebElement>  createChangeReqEleDictionary() {
		LinkedHashMap<String, WebElement> map = new LinkedHashMap<>();
		map.put("Short description", shortDescriptionField);
		map.put("Assignment group", assignmentGroupField);
		map.put("Change Management Group", changeMgntGroupField);
		map.put("Configuration item", configItemField);
		map.put("CMDB update required", cmdbDdwn);
		map.put("Primary Area", primaryAreaField);
		map.put("Environment", environmentDdwn);
		map.put("Category", categoryDdwn);
		map.put("Subcategory", subcategoryDdwn);
		map.put("Action", actionDdwn);
		map.put("Timing Reason", timingRsnDdwn);
		return map;
	}
	
	public LinkedHashMap<String, WebElement>  createPlannedTabDictionary() {
		LinkedHashMap<String, WebElement> map = new LinkedHashMap<>();
		map.put("Pre-Implementation Test Evidence", preImptestEvidenceField);
		map.put("Implementation plan", implementationPlanField);
		map.put("Post-Implementation Test plan", postImplementationPlanField);
		map.put("Backout plan", backoutPlan);
		return map;
	}
	
	public LinkedHashMap<String, WebElement>  createScheduleTabDictionary() {
		LinkedHashMap<String, WebElement> map = new LinkedHashMap<>();
		map.put("Planned start date", plannedStartDate);
		map.put("Planned end date", plannedEndDate);
		map.put("Actual start date", actualStartDate);
		map.put("Actual end date", actualEndDate);
		return map;
	}
	
	public LinkedHashMap<String, WebElement>  createClosureTabDictionary() {
		LinkedHashMap<String, WebElement> map = new LinkedHashMap<>();
		map.put("Close code", closeCodeDdwn);
		map.put("Close notes", closeNotesField);
		return map;
	}
	
	public String createChangeRequest(){
		driver.switchTo().frame("gsft_main");
		test.info("Creating new normal change request.");
		normalChangeRequestLink.click();
		test.pass("New change request page launched successfully.");
		dataMap = new ExcelUtility().getTestData("ChangeRequest");
		waitTime();
		String changeReqNum = changeRequestNum.getAttribute("value");
		System.out.println("Req#"+changeReqNum);
		test.info("Change Request Number: "+changeReqNum);
		test.info("Entering test data in the short description.");
		shortDescriptionField.sendKeys(dataMap.get("Short description"));
		setAssignedTo();
		test.info("Clicking on Save button.");
		saveButton.click();
		waitTime();
		String appStatus = getSelectedOption(approvalStatus);
		System.out.println("AppStatus : "+appStatus);
		test.info("Request current approval status is "+appStatus);
		if(!appStatus.equalsIgnoreCase("Requested")) {
			test.info("Entering test data in mandatory fields.");
			String error = fieldErrorMsg.getText();
			if(error.equalsIgnoreCase("Please fillout and execute a risk assessment to generate a risk value.")) {
				fillOutRiskAssBtn.click();
				waitTime();
				driver.switchTo().frame(riskAssesFrame);
				waitTime();
				test.info("Filling risk assessment form.");
				for(WebElement el: riskAssesQuestion1) {
					if(el.getAttribute("value").equalsIgnoreCase(dataMap.get("Risk assesment question1"))) {
						new Actions(driver).moveToElement(el).click().build().perform();
						break;
					}
				}
				for(WebElement el: riskAssesQuestion2) {
					if(el.getAttribute("value").equalsIgnoreCase(dataMap.get("Risk assesment question2"))) {
						new Actions(driver).moveToElement(el).click().build().perform();
						break;
					}
				}
				for(WebElement el: riskAssesQuestion3) {
					if(el.getAttribute("value").equalsIgnoreCase(dataMap.get("Risk assesment question3"))) {
						new Actions(driver).moveToElement(el).click().build().perform();
						break;
					}
				}
				for(WebElement el: riskAssesQuestion4) {
					if(el.getAttribute("value").equalsIgnoreCase(dataMap.get("Risk assesment question4"))) {
						new Actions(driver).moveToElement(el).click().build().perform();
						break;
					}
				}
				riskAssesSurveySubmitBtn.click();
				waitTime();
				test.pass("Filled risk assessment form successfully.");
				driver.switchTo().frame("gsft_main");
				waitTime();
				test.info("Executing risk calculation.");
				executeRiskCalBtn.click();
				test.pass("Risk calculation executed successfully.");
			}
			test.info("Requesting approval.");
			requestApprovalBtn.click();
			waitTime();
			appStatus = getSelectedOption(approvalStatus);
			waitTime();
			fillMandatoryLabels();
			driver.switchTo().frame("gsft_main");
			requestApprovalBtn.click();
			waitTime();
			appStatus = getSelectedOption(approvalStatus);
			System.out.println("AppStatus End: "+appStatus);
		}
		driver.switchTo().defaultContent();
		return changeReqNum;
	}
	
	public void fillMandatoryLabels() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("gsft_main");
		waitTime();
		boolean mandatoryFlag = true;
		test.info("Entering test data in the mandatory fields.");
		while(mandatoryFlag) {
			waitTime();
			if(changeReqMandatoryLabels.size()>=1) {
				test.info("Remaining mandatory fields count under change request is "+changeReqMandatoryLabels.size());
				for(WebElement el: changeReqMandatoryLabels) {
					test.info("Entering '"+dataMap.get((el).getText())+"' in the "+el.getText()+" field.");
					if(dataMap.get((el).getText()).contains("CURRENT_DATE")) {
						performAction(createChangeReqEleDictionary().get((el).getText()), getTimeStamp(dataMap.get((el).getText())));
					}
					else {
						performAction(createChangeReqEleDictionary().get((el).getText()), dataMap.get((el).getText()));
					}
					test.pass("Entered '"+dataMap.get((el).getText())+"' in the "+el.getText()+" field successfully.");
				}
				waitTime();
			}
			planningTab.click();
			waitTime();
			if(planningTabLabels.size()>=1) {
				test.info("Remaining mandatory fields count under planning tab is "+planningTabLabels.size());
				for(WebElement el: planningTabLabels) {
					test.info("Entering '"+dataMap.get((el).getText())+"' in the "+el.getText()+" field.");
					if(dataMap.get((el).getText()).contains("CURRENT_DATE")) {
						performAction(createPlannedTabDictionary().get((el).getText()), getTimeStamp(dataMap.get((el).getText())));
					}
					else {
						performAction(createPlannedTabDictionary().get((el).getText()), dataMap.get((el).getText()));
					}
					test.pass("Entered '"+dataMap.get((el).getText())+"' in the "+el.getText()+" field successfully.");
				}
				waitTime();
			}
			scheduleTab.click();
			waitTime();
			if(scheduleTabLabels.size()>=1) {
				test.info("Remaining mandatory fields count under schedule tab is "+scheduleTabLabels.size());
				for(WebElement el: scheduleTabLabels) {
					test.info("Entering '"+dataMap.get((el).getText())+"' in the "+el.getText()+" field.");
					if(dataMap.get((el).getText()).contains("CURRENT_DATE")) {
						performAction(createScheduleTabDictionary().get((el).getText()), getTimeStamp(dataMap.get((el).getText())));
					}
					else {
						performAction(createScheduleTabDictionary().get((el).getText()), dataMap.get((el).getText()));
					}
					test.pass("Entered '"+dataMap.get((el).getText())+"' in the "+el.getText()+" field successfully.");
				}
				waitTime();
			}
			closureInfoTab.click();
			waitTime();
			if(closureInfoLabels.size()>=1) {
				test.info("Remaining mandatory fields count under closure information tab is "+closureInfoLabels.size());
				for(WebElement el: closureInfoLabels) {
					test.info("Entering '"+dataMap.get((el).getText())+"' in the "+el.getText()+" field.");
					if(dataMap.get((el).getText()).contains("CURRENT_DATE")) {
						performAction(createClosureTabDictionary().get((el).getText()), getTimeStamp(dataMap.get((el).getText())));
					}
					else {
						performAction(createClosureTabDictionary().get((el).getText()), dataMap.get((el).getText()));
					}
					test.pass("Entered '"+dataMap.get((el).getText())+"' in the "+el.getText()+" field successfully.");
				}
				waitTime();
			}
			saveButton.click();
			waitTime();
			if(changeReqMandatoryLabels.size()>=1) {
				mandatoryFlag = true;
			}
			else {
				mandatoryFlag = false;
				waitTime();
				planningTab.click();
				waitTime();
				if(planningTabLabels.size()>=1) {
					mandatoryFlag = true;
				}
				scheduleTab.click();
				waitTime();
				if(scheduleTabLabels.size()>=1) {
					mandatoryFlag = true;
				}
				closureInfoTab.click();
				waitTime();
				if(closureInfoLabels.size()>=1) {
					mandatoryFlag = true;
				}
			}
		}
		driver.switchTo().defaultContent();
	}
	
	public String getAssignedUser() {
		return dataMap.get("Assigned to");
	}
	
	public void setAssignedTo() {
		assignedToField.sendKeys(dataMap.get("Assigned to"));
		waitTime();
		new Actions(driver).sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();
	}
	
	public void searchAndPreviewRequest(String reqNumber) {
		driver.switchTo().frame("gsft_main");
		test.info("Searching the request number on changes open page.");
		try {
			searchReqNumberField.sendKeys(reqNumber, Keys.ENTER);
		}
		catch(WebDriverException e) {
			enableSearchFields.click();
			waitTime();
			searchReqNumberField.sendKeys(reqNumber, Keys.ENTER);
		}
		test.pass("Search for request number completed successfully.");
		test.info("Previewing the request number on changes page.");
		requestPreviewIcon.click();
		test.pass("Preview for request number completed successfully.");
		driver.switchTo().defaultContent();
	}
	
	public String reviewChangeRequest() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("gsft_main");
		test.info("Reviewing change request.");
		test.info("Clicking on Implement button.");
		implementButton.click();
		try {
			fillMandatoryLabels();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("gsft_main");
			test.info("Clicking on Implement button again after entering the values in mandatory fields.");
			implementButton.click();
		}
		catch(Exception e) {
		}
		driver.switchTo().defaultContent();
		driver.switchTo().frame("gsft_main");
		test.info("Clicking on Review button.");
		reviewButton.click();
		try {
			fillMandatoryLabels();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("gsft_main");
			test.info("Clicking on Review button again after entering the values in mandatory fields.");
			reviewButton.click();
		}
		catch(Exception e) {
		}
		String reqStatus = getSelectedOption(reqState);
		test.info("Current change request state is "+reqStatus);
		driver.switchTo().defaultContent();
		return reqStatus;
	}
	
	public void closeChangeRequest() {
		
	}
	
	public String getApprovalName() {
		driver.switchTo().frame("gsft_main");
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(approversTab));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", approversTab);
		test.info("Clicking on approvers tab.");
		approversTab.click();
		waitTime();
		List<WebElement> stateList = approvalState;
		List<WebElement> appList = approverName;
		String appName = null;
		for(int i=0; i<stateList.size(); i++) {
			String state = stateList.get(i).getText();
			if(state.equalsIgnoreCase("Requested")) {
				test.info("Getting the approver name.");
				appName = appList.get(i).getText();
				test.pass("Approver name retrieved successfully: "+appName);
				break;
			}
		}
		driver.switchTo().defaultContent();
		return appName;
	}
	
	public String getSelectedOption(WebElement ele) {
		return new Select(ele).getFirstSelectedOption().getText();
	}
	
	public void performAction(WebElement ele, String testData) {
		String tagName = ele.getTagName();
		if(tagName.equalsIgnoreCase("input")) {
			ele.sendKeys(testData);
			waitTime();
			new Actions(driver).sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();
		}
		else if(tagName.equalsIgnoreCase("select")) {
			new Select(ele).selectByVisibleText(testData);
		}
		else if(tagName.equalsIgnoreCase("textarea")) {
			ele.sendKeys(testData);
		}
		else {
			System.out.println("No action found for tagname :"+tagName);
		}
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
	public CreateChangeRequestPage(WebDriver driver, ExtentTest testInstance) {
		this.setDriver(driver);
		this.test = testInstance;
		PageFactory.initElements(driver, this);
	}
}
