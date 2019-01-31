package pageFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import functionLibrary.Const;
import functionLibrary.sharedFunctions;

public class ServicenowLoginPage extends sharedFunctions{
	
	private WebDriver driver;
	private ExtentTest test;
	
	@FindBy(xpath="//input[@id='user_name']")
	private WebElement usernameField;
	
	@FindBy(id="user_password")
	private WebElement passwordField;
	
	@FindBy(id="sysverb_login")
	private WebElement loginBtn;
	
//	@FindBy(xpath="")
//	private WebElement ele;
	
	public void enterCredentialsAndLogin(String username, String password) throws InterruptedException {
		driver.switchTo().frame("gsft_main");
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(usernameField));
		test.info("Entering user<"+username+"/"+password+"> credentials.");
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		test.pass("Entered credentials successfully.");
		test.info("Clicking on login button.");
		loginBtn.click();		
		driver.switchTo().defaultContent();
		test.pass("User logged in successfully.");
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public ServicenowLoginPage(WebDriver driver, ExtentTest testInstance) {
		this.setDriver(driver);
		this.test = testInstance;
		driver.get(Const.servicenowPortalUrl);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}

}
