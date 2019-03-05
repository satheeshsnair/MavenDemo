package reusableLibrary;

import java.awt.AWTException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.ExtentManager;

public class DriverFactory extends ExtentManager {

	protected String executingTestScenarioName;
	protected ExtentReports extentInstance;
	protected ExtentTest parentTestInstance;
	protected ExtentTest testInstance;
	public final String getCurrentlyLoggedInUser = System.getProperty("user.name");
	protected WebDriver driver = null;
	protected String runOnBrowser = null;
	private int proxyType = ProxyType.AUTODETECT.ordinal();

	@BeforeSuite
	/**
	 * Function to change the Error Screenshot folder name before the Suite starts
	 * 
	 * @author saikiran.nataraja
	 */
	public void CreateErrorRepFolder() throws Exception {
		PathConstants.setDateFormatSettings(); // set the date format before starting each test
	}

	/**
	 * This is to create an extent manager instance before every new class
	 * 
	 * @author saikiran.nataraja
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Parameters({ "BrowserType" })
	@BeforeClass
	public void createExtentInstance(@Optional("chrome") String browser) throws IOException, InterruptedException {
		ExtentManager xtentMgr = new ExtentManager();
		extentInstance = xtentMgr.createExtentRep();
		// Initialize the excel report
		Thread.sleep(PathConstants.minWaitTime);
		String baseURL = "https://jnjtest.appiancloud.com/suite/portal/login.jsp";
		runOnBrowser = browser;
		driver = new ChromeDriver(createChromeCapabilites());
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.get(baseURL);
		// Initialize the error manager instance
		executingTestScenarioName = super.getClass().getSimpleName();
		// Create testInstance
		parentTestInstance = extentInstance.createTest(executingTestScenarioName,
				"'" + executingTestScenarioName + "' is used to check details in Application.");
		parentTestInstance.assignAuthor(getCurrentlyLoggedInUser);
		parentTestInstance.assignCategory("Regression_" + executingTestScenarioName);
	}

	/**
	 * Function to create chrome capabilities
	 * 
	 * @author saikirannataraja
	 * @returns chromeoptions
	 */
	public ChromeOptions createChromeCapabilites() {
		ChromeOptions chromeOptions = new ChromeOptions();
		LinkedHashMap<String, Object> chromePrefs = new LinkedHashMap<String, Object>();
		// Set ACCEPT_SSL_CERTS variable to true
		chromePrefs.put(CapabilityType.ACCEPT_SSL_CERTS, true);
		chromePrefs.put(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.extensions_to_open", "pdf");
		chromePrefs.put("--always-authorize-plugins", false);
		chromePrefs.put("download.prompt_for_download", true);
		chromePrefs.put("credentials_enable_service", false);
		chromePrefs.put("profile.password_manager_enabled", false);
		chromeOptions.setExperimentalOption("prefs", chromePrefs);
		chromeOptions.setCapability("network.proxy.type", proxyType);
		chromeOptions.addArguments("--start-maximized");
		chromeOptions.addArguments("disable-infobars");
		// Optional, if not specified, WebDriver will search your path for chromedriver.
		System.setProperty("webdriver.chrome.driver", PathConstants.chromeDriverPath);
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		return chromeOptions;
	}

	/**
	 * Function to return browser version
	 * 
	 * @author saikiran.nataraja
	 * @return browser name and version
	 */
	public String getBrowserVersion() {
		String browser_version;
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browsername = cap.getBrowserName();
		String GetuAgent = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent;");
		// This block to find out IE Version number
		if ("internet explorer".equalsIgnoreCase(browsername)) {
			// uAgent return as "MSIE 8.0 Windows" for IE8
			if (GetuAgent.contains("MSIE") && GetuAgent.contains("Windows")) {
				browser_version = GetuAgent.substring(GetuAgent.indexOf("MSIE") + 5, GetuAgent.indexOf("Windows") - 2);
			} else if (GetuAgent.contains("Trident/7.0")) {
				browser_version = "11.0";
			} else {
				browser_version = "0.0";
			}
		} else if ("firefox".equalsIgnoreCase(browsername)) {
			browser_version = GetuAgent.substring(GetuAgent.indexOf("Firefox")).split(" ")[0].replace("/", "-");
			browser_version = browser_version.replace("Firefox-", "");
		} else { // Browser version for Chrome and Opera
			browser_version = cap.getVersion();
		}
		String browserversion = browser_version.substring(0, browser_version.indexOf('.'));
		return browsername + " browser (Version: " + browserversion + " )";
	}


	/**
	 * Function to initialize Extent report and it must be called only @Test
	 * annotation
	 * 
	 * @author saikiran.nataraja
	 */
	public void initializeExtentReport(String stepName) {
		// Instantiating the ExtentReports
		// Create testInstance
		testInstance = parentTestInstance.createNode(stepName,
				"'" + stepName + "' is being executed.");
		
	}


	@AfterClass
	public void tearDownFunction() throws Exception {
		if (driver != null) {
			Thread.sleep(2000);
			driver.close();
		}
		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec("taskkill /F /IM chromedriver.exe");
		} catch (IOException e) {
			// clean up state...
			Thread.currentThread().interrupt();
		}
		// write all resources to report file
		extentInstance.flush();
	}

	/**
	 * Capture the operations on Test completion @param testResult @author
	 * saikiran.nataraja @throws AWTException @throws IOException @throws
	 */
	@AfterMethod
	public void operationsOnTestCompletion(ITestResult testResult) throws AWTException, IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			Reporter.log(" - FAILED.", true);
			// Extent Reports error message
			testInstance.log(Status.FAIL, "Failure Stack Trace: " + testResult.getThrowable().getMessage());
			// adding screenshots to log
		} else if (testResult.getStatus() == ITestResult.SKIP) {
			Reporter.log(" - SKIPPED.", false);
			testInstance.log(Status.SKIP, "Test skipped: " + testResult.getThrowable().getMessage());
		} else {
			Reporter.log(" - PASSED.", false);
			testInstance.log(Status.PASS, "'" + executingTestScenarioName + "' is passed based on the test criteria.");
		}

	}

}
