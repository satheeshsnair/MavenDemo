package pageFactory;

import java.util.LinkedHashMap;

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

public class CreateAssetPage extends sharedFunctions{
	
	private WebDriver driver;
	private ExtentTest test;
	
	@FindBy(xpath="//button[@id='sysverb_new']")
	private WebElement newButton;
	
	@FindBy(xpath="//a[contains(text(),'Hardware')]")
	private WebElement hardwareLnk;
	
	@FindBy(xpath="//input[@id='alm_hardware.asset_tag']")
	private WebElement assetTagField;
	
	@FindBy(xpath="//input[@id='alm_hardware.serial_number']")
	private WebElement serialNumField;
	
	@FindBy(xpath="//input[@id='sys_display.alm_hardware.model_category']")
	private WebElement modelCategoryField;
	
	@FindBy(xpath="//input[@id='sys_display.alm_hardware.model']")
	private WebElement modelField;
	
	@FindBy(xpath="//input[@id='sys_display.alm_hardware.u_charge_code']")
	private WebElement chargeCodeField;
	
	@FindBy(xpath="//input[@id='sys_display.alm_hardware.u_hardware_asset_management']")
	private WebElement hardwareAssetMgntField;
	
	//---------------General Tab------------------------
	
	@FindBy(xpath="//input[@id='sys_display.alm_hardware.owned_by']")
	private WebElement ownedByField;
	
	@FindBy(xpath="//input[@id='sys_display.alm_hardware.vendor']")
	private WebElement vendorField;
	
	@FindBy(xpath="//input[@id='sys_display.alm_hardware.location']")
	private WebElement locationField;
	
	@FindBy(xpath="//input[@id='alm_hardware.install_date']")
	private WebElement installField;
	
	//---------------Financial Tab------------------------
	
	@FindBy(xpath="//span[text()='Financial']")
	private WebElement financialTab;
	
	@FindBy(xpath="//select[@id='alm_hardware.acquisition_method']")
	private WebElement acquisitionMethodDdwn; //Rental
	
	//---------------Contracts Tab------------------------
	
	@FindBy(xpath="//span[text()='Contracts']")
	private WebElement contractsTab;
	
	@FindBy(xpath="//select[@id='alm_hardware.u_contractual_scope']")
	private WebElement contractualScopeDdwn; //MaaS
	
	@FindBy(xpath="//button[@id='sysverb_insert']")
	private WebElement submitButton;
	
//	@FindBy(xpath="")
//	private WebElement ele;
	
	public String createAsset() {
		LinkedHashMap<String, String> dataMap= new ExcelUtility().getTestData("Asset");
		driver.switchTo().frame("gsft_main");
		test.info("Creating new asset by clicking on New button");
		newButton.click();
		test.pass("New asset category page launched successfully");
		test.info("Selecting Hardware link");
		hardwareLnk.click();
		test.pass("New asset form page launched successfully");
		String assetName = "Asset"+randomWord(5);
		test.info("Entering field values in the new asset form");
		assetTagField.sendKeys(assetName);
		serialNumField.sendKeys(randomNumber());
		setFieldValues(modelCategoryField, dataMap.get("Model category"));
		setFieldValues(modelField, dataMap.get("Model"));
		setFieldValues(chargeCodeField, dataMap.get("Charge code"));
		setFieldValues(hardwareAssetMgntField, dataMap.get("Hardware Asset Management"));
		setFieldValues(ownedByField, dataMap.get("Owned by"));
		setFieldValues(vendorField, dataMap.get("Vendor"));
		setFieldValues(locationField, dataMap.get("Location"));
		installField.sendKeys(getTimeStamp(dataMap.get("Installed")));
		financialTab.click();
		new Select(acquisitionMethodDdwn).selectByVisibleText(dataMap.get("Acquisition method"));
		contractsTab.click();
		new Select(contractualScopeDdwn).selectByVisibleText(dataMap.get("Contractual Scope"));
		submitButton.click();
		test.pass("Creation of asset completed successfully, asset name is "+assetName);
		return assetName;
	}
	
	public void setFieldValues(WebElement ele, String value) {
		ele.sendKeys(value);
		waitTime();
		new Actions(driver).sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();
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
	public CreateAssetPage(WebDriver driver, ExtentTest testInstance) {
		this.setDriver(driver);
		this.test = testInstance;
		PageFactory.initElements(driver, this);
	}

}
