package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import functionLibrary.sharedFunctions;

public class ChangeRequestPage extends sharedFunctions{
	
	private WebDriver driver;
	private ExtentTest test;
	
	
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	public ChangeRequestPage(WebDriver driver, ExtentTest testInstance) {
		this.setDriver(driver);
		this.test = testInstance;
		PageFactory.initElements(driver, this);
	}

}
