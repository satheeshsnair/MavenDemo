package pageFactory;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import functionLibrary.sharedFunctions;

public class ServiceCatalogItemsPage extends sharedFunctions{
	private WebDriver driver;
	private ExtentTest test;
	
	@FindBy(xpath="//table[@id='sc_req_item_table']//input[@aria-label='Search number']")
	private WebElement searchReqNumberField;
	
	@FindBy(xpath="//a[span[text()='Preview']]")
	private WebElement requestPreviewIcon;
	
	@FindBy(xpath="//span[@class='tab_caption_text' and contains(text(),'Approvers')]")
	private WebElement approversTab;
	
	@FindBy(xpath="//span[@class='tab_caption_text' and contains(text(),'Catalog')]")
	private WebElement catalogTaskTab;
	
	@FindBy(xpath="//button[@id='close_sc_task']")
	private WebElement closeTaskBtn;
	
	@FindBy(xpath="//select[@id='sc_req_item.state']")
	private WebElement reqItemState;
	
	@FindBys({ @FindBy(xpath="//tr[contains(@id,'row_sc_req_item.sysapproval_approver')]/td[3]/a")})
	private List<WebElement> approvalState;
	
	@FindBys({ @FindBy(xpath="//tr[contains(@id,'row_sc_req_item.sysapproval_approver')]/td[4]/a")})
	private List<WebElement> approverName;
	
	@FindBys({ @FindBy(xpath="//tr[contains(@id,'row_sc_req_item.sc_task.request_item')]/td[5]")})
	private List<WebElement> taskStateList;
	
	@FindBys({ @FindBy(xpath="//tr[contains(@id,'row_sc_req_item.sc_task.request_item')]/td[3]/a")})
	private List<WebElement> taskNumberList;
	
	public void closeOpenTasks() {
		driver.switchTo().frame("gsft_main");
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(reqItemState));
		String currState = new Select(reqItemState).getFirstSelectedOption().getText();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", catalogTaskTab);
		test.info("Clicking on catalog tasks tab.");
		catalogTaskTab.click();
		waitTime();
		List<WebElement> taskState = taskStateList;
		List<WebElement> taskNumber = taskNumberList;
		if(taskState.size()>0) {
			while(!currState.equalsIgnoreCase("Closed Complete")) {
				waitTime();
				taskState = taskStateList;
				taskNumber = taskNumberList;
				waitTime();
				for(int i=0; i<taskState.size(); i++) {
					String state = taskState.get(i).getText();
					if(state.equalsIgnoreCase("Open")) {
						test.info("Opening the open catalog task.");
						taskNumber.get(i).click();
						try {
							driver.switchTo().alert().accept();
						}
						catch(WebDriverException e) {
						}
						test.info("Clicking on close button to close the task.");
						closeTaskBtn.click();
						test.pass("Closed "+i+" task successfully.");
					}
					waitTime();
					currState = new Select(reqItemState).getFirstSelectedOption().getText();
				}
			}
		}
		driver.switchTo().defaultContent();
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
	
	public void searchAndPreviewRequest(String reqNumber) {
		driver.switchTo().frame("gsft_main");
		test.info("Searching the request number on service catalog items page.");
		searchReqNumberField.sendKeys(reqNumber, Keys.ENTER);
		waitTime();
		test.pass("Search for request number completed successfully.");
		test.info("Previewing the request number on service catalog items page.");
		requestPreviewIcon.click();
		test.pass("Preview for request number completed successfully.");
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
	public ServiceCatalogItemsPage(WebDriver driver, ExtentTest testInstance) {
		this.setDriver(driver);
		this.test = testInstance;
		PageFactory.initElements(driver, this);
	}

}
