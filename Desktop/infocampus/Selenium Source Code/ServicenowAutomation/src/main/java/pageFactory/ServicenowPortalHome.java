package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import functionLibrary.sharedFunctions;

public class ServicenowPortalHome extends sharedFunctions{
	
	private WebDriver driver;
	private ExtentTest test;
	
	@FindBy(xpath="//span[text()='Service Catalog']")
	private WebElement serviceCatalogTab;
	
//	@FindBy(xpath="")
//	private WebElement ele;
	
	public void launchServiceCatalog() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(serviceCatalogTab));
		test.info("launching service catalog page.");
		serviceCatalogTab.click();
		test.pass("launched service catalog page successfully.");
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	public ServicenowPortalHome(WebDriver driver, ExtentTest testInstance) {
		this.setDriver(driver);
		this.test = testInstance;
		PageFactory.initElements(driver, this);
	}
}
