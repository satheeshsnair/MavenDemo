package pageFactory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import functionLibrary.sharedFunctions;


public class ServicenowHomePage extends sharedFunctions{
	
	private WebDriver driver;
	private ExtentTest test;

	@FindBy(xpath="//input[@id='filter']")
	private WebElement filterField;
	
	@FindBy(xpath="//span[text()='Incident']/../following-sibling::ul//div[text()='Create New']/../..")
	private WebElement createNewIncidentLink;
	
	@FindBy(xpath="//span[text()='Incident']/../following-sibling::ul//div[text()='Open']/../..")
	private WebElement openIncidentLink;
	
	@FindBy(xpath="//a//div[text()='Requested Items']")
	private WebElement requestedItemsLink;
	
	@FindBy(xpath="//a//div[text()='Items']")
	private WebElement openRecordItemsLink;
	
	@FindBy(id="user_info_dropdown")
	private WebElement userMenu;
	
	@FindBy(xpath="//a[text()='Impersonate User']")
	private WebElement impersonateUser;
	
	@FindBy(xpath="//span[text()='Search for user']")
	private WebElement searchForUserLink;
	
	@FindBy(xpath="//a[span[text()='Self-Service']]/following-sibling::ul//div[text()='My Approvals']")
	private WebElement selfSerMyApprovals;
	
	@FindBy(xpath="//a[text()='Logout']")
	private WebElement logOutBtn;
	
	@FindBy(xpath="//a[span[text()='Problem']]/following-sibling::ul//div[text()='Create New']")
	private WebElement createNewProblemLink;
	
	@FindBy(xpath="//a[span[text()='Change']]/following-sibling::ul//div[text()='Create New']")
	private WebElement createNewChangeLink;
	
	@FindBy(xpath="//a[span[text()='Change']]/following-sibling::ul//div[text()='Open']")
	private WebElement openChangeLink;
	
	@FindBy(xpath="//a[span[text()='Knowledge']]/following-sibling::ul//div[text()='Create New']")
	private WebElement createNewKnowledgeLink;
	
	@FindBy(xpath="//a[span[text()='Asset']]/following-sibling::ul//div[text()='All Assets']")
	private WebElement allAssetsLink;
	
//	@FindBy(xpath="")
//	private WebElement ele;
	
	public void launchCreateAllAssets() {
		waitTime();
		test.info("Launching all assets page.");
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(filterField));
		filterField.sendKeys("All Assets");
		new Actions(driver).sendKeys(Keys.ENTER).perform();
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(allAssetsLink));
		allAssetsLink.click();
		new Actions(driver).moveToElement(allAssetsLink).sendKeys(Keys.ENTER).perform();
		test.info("Launched all assets page successfully.");
	}
	
	public void launchCreateKnowledgeForm() {
		waitTime();
		test.info("Launching create knowledge page.");
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(filterField));
		filterField.sendKeys("Knowledge");
		new Actions(driver).sendKeys(Keys.ENTER).perform();
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(createNewKnowledgeLink));
		createNewKnowledgeLink.click();
		new Actions(driver).moveToElement(createNewKnowledgeLink).sendKeys(Keys.ENTER).perform();
		test.info("Launched create knowledge page successfully.");
	}
	
	public void launchCreateChangeForm() {
		waitTime();
		test.info("Launching create change page.");
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(filterField));
		filterField.sendKeys("Change");
		new Actions(driver).sendKeys(Keys.ENTER).perform();
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(createNewChangeLink));
		createNewChangeLink.click();
		new Actions(driver).moveToElement(createNewChangeLink).sendKeys(Keys.ENTER).perform();
		test.info("Launched create change page successfully.");
	}
	
	public void launchOpenChangeReq() {
		waitTime();
		test.info("Launching create change page.");
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(filterField));
		filterField.sendKeys("Change");
		new Actions(driver).sendKeys(Keys.ENTER).perform();
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(openChangeLink));
		openChangeLink.click();
		new Actions(driver).moveToElement(openChangeLink).sendKeys(Keys.ENTER).perform();
		test.info("Launched create change page successfully.");
	}
	
	public void launchCreateProblemForm() {
		waitTime();
		test.info("Launching create problems page.");
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(filterField));
		filterField.sendKeys("Problem");
		new Actions(driver).sendKeys(Keys.ENTER).perform();
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(createNewProblemLink));
		createNewProblemLink.click();
		new Actions(driver).moveToElement(createNewProblemLink).sendKeys(Keys.ENTER).perform();
		test.info("Launched create problem page successfully.");
	}
	
	public void launchMyApprovals() {
		waitTime();
		test.info("Launching my approvals page.");
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(filterField));
		filterField.sendKeys("My Approvals");
		new Actions(driver).sendKeys(Keys.ENTER).perform();
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(selfSerMyApprovals));
		selfSerMyApprovals.click();
		new Actions(driver).moveToElement(selfSerMyApprovals).sendKeys(Keys.ENTER).perform();
		test.info("Launched my approvals page successfully.");
	}
	
	public void impersonateUser(String userName) {
		waitTime();
		test.info("Clicking on User menu.");
		userMenu.click();
		waitTime();
		new Actions(driver).moveToElement(impersonateUser).perform();
		waitTime();
		test.info("Clicking on impersonate user fromt the menu.");
		impersonateUser.click();
		waitTime();
		test.info("Searching user <"+userName+"> from the user list.");
		new Actions(driver).click(searchForUserLink).sendKeys(userName).perform();
		waitTime();
		new Actions(driver).sendKeys(Keys.ENTER).perform();
		waitTime();
		try {
			driver.switchTo().alert().accept();
		}
		catch(WebDriverException e) {
		}	
		waitTime();
		test.pass("User impersonated successfully.");
	}
	
	public void launchCreateIncidentForm() {
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(filterField));
		test.info("Launching create incident page.");
		filterField.sendKeys("Incident");
		new Actions(driver).sendKeys(Keys.ENTER).perform();
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(createNewIncidentLink));
		createNewIncidentLink.click();
		new Actions(driver).moveToElement(createNewIncidentLink).sendKeys(Keys.ENTER).perform();
		test.pass("Launched create incident page successfully.");
	}
	
	public void launchOpenIncident() {
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(filterField));
		test.info("Launching create incident page.");
		filterField.sendKeys("Incident");
		new Actions(driver).sendKeys(Keys.ENTER).perform();
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(openIncidentLink));
		openIncidentLink.click();
		new Actions(driver).moveToElement(openIncidentLink).sendKeys(Keys.ENTER).perform();
		test.pass("Launched create incident page successfully.");
	}
	
	public void launchServiceCatalogItems() {
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(filterField));
		test.info("Launching service catalog items page.");
		filterField.sendKeys("Open Records");
		new Actions(driver).sendKeys(Keys.ENTER).perform();
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(openRecordItemsLink));
		openRecordItemsLink.click();
		new Actions(driver).moveToElement(openRecordItemsLink).sendKeys(Keys.ENTER).perform();
		test.pass("Launched service catalog items page successfully.");
	}
	
	public void launchRequestedItems() {
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(filterField));
		test.info("Launching requested items page.");
		filterField.sendKeys("Requests");
		new Actions(driver).sendKeys(Keys.ENTER).perform();
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(requestedItemsLink));
		createNewIncidentLink.click();
		new Actions(driver).moveToElement(requestedItemsLink).sendKeys(Keys.ENTER).perform();
		test.pass("Launched requested items page successfully.");
	}
	
	public void logoutUser() {
		test.info("Logging out from the application.");
		driver.switchTo().defaultContent();
		waitTime();
		userMenu.click();
		waitTime();
		new Actions(driver).moveToElement(logOutBtn).perform();
		waitTime();
		logOutBtn.click();
		waitTime();
		try {
			driver.switchTo().alert().accept();
		}
		catch(WebDriverException e) {
		}	
		test.pass("User logged out successfully.");
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

	public ServicenowHomePage(WebDriver driver, ExtentTest testInstance) {
		this.setDriver(driver);
		this.test = testInstance;
		PageFactory.initElements(driver, this);
	}
	
}
