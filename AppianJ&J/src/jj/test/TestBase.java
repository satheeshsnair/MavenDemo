package jj.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utilities.ExtentManager;

public class TestBase {
	protected WebDriver driver;
	protected String url;
	protected ExtentReports report;
	protected ExtentTest test;

	@BeforeSuite
	public void setup() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\satheeshnair\\Desktop\\infocampus\\Softwares\\Selenium Jars\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		url = "https://jnjtrain.appiancloud.com/suite/portal/login.jsp";
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	public void initializeReport() {
		ExtentManager xtMgr = new ExtentManager();
		report = xtMgr.createExtentRep();
		test = report.createTest(super.getClass().getSimpleName(), "Application to verify");
		test.log(Status.PASS, "Started using extent reports");
	}

//	@AfterTest
//	public void getResult(ITestResult result) {
//		if (result.getStatus() == ITestResult.FAILURE) {
//			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
//			test.fail(result.getThrowable());
//		} else if (result.getStatus() == ITestResult.SUCCESS) {
//			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
//		} else {
//			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
//			test.skip(result.getThrowable());
//		}
//	}

	@AfterSuite
	public void tearDown() {
		//driver.quit();
		// to write or update test information to reporter
		report.flush();
	}
}
