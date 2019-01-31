package functionLibrary;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import utilities.ExtentManager;
import utilities.Log;

public class sharedFunctions extends ExtentManager {

	protected String executingTestCaseName;
	private Log loggerInstance;
	protected ExtentReports extentInstance;
	protected ExtentTest testInstance;
	protected ExtentHtmlReporter htmlReporterInstance;
	public final String getCurrentlyLoggedInUser = System.getProperty("user.name");
	protected WebDriver webdriver = null;
	protected String runOnBrowser = null;
	protected Properties OR = null;
	protected String baseURL = "";
	private int proxyType = ProxyType.AUTODETECT.ordinal();

	@BeforeSuite
	public void CreateErrorRepFolder() throws Exception {
		Const.setDateFormatSettings(); // set the date format before starting each test
		Const.ErrorReportPath = Const.ReportPath + Const.DateFormat + "ErrorScreenshots";
	}

	@BeforeClass
	public void createExtentInstance() {
		loggerInstance = new Log();
		ExtentManager xtentMgr = new ExtentManager();
		extentInstance = xtentMgr.createExtentRep();

	}

	@Parameters({ "BrowserType" })
	@BeforeTest
	public void browserSetup(@Optional("chrome") String browser) {

		runOnBrowser = browser;
		switch (runOnBrowser) {
		case "firefox":
			/**
			 * For Firefox below 47.0.2 If you have selenium 3.4 version libraries then you
			 * cannot have firefox version below 48
			 */
			// For Firefox above 47.0.2
			System.setProperty("webdriver.gecko.driver", Const.firefoxDriverPath);
			System.setProperty("webdriver.log.logfile", "INFO");
			webdriver = new FirefoxDriver(createFirefoxProfile());
			break;
		case "chrome":
			webdriver = new ChromeDriver(createChromeCapabilites());
			break;
		case "ie":
			// Add Pop-up allowed in IE manually
			webdriver = new InternetExplorerDriver(createIECapabilities());
			break;
		default:
			Reporter.log("Driver Not Found");
			break;
		}
		webdriver.manage().window().maximize();
		webdriver.manage().deleteAllCookies();
		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		webdriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}

	public FirefoxOptions createFirefoxProfile() {
		// For Firefox above 47.0.2
		System.setProperty("webdriver.gecko.driver", Const.firefoxDriverPath);
		System.setProperty("webdriver.log.logfile", "INFO");
		// An implementation of the {#link WebDriver} interface that drives Firefox.
		FirefoxOptions firefoxOptions = new FirefoxOptions().setProfile(new FirefoxProfile());
		// Set the capability for marionnette
		firefoxOptions.setCapability("marionette", false);
		// Set Log Level to INFO
		firefoxOptions.setLogLevel(FirefoxDriverLogLevel.INFO);
		firefoxOptions.setAcceptInsecureCerts(true); // Set profile to accept untrusted certificates
		firefoxOptions.addPreference("browser.download.folderList", 2); // 0- Desktop, 1-Browser's Default Path , 2-
																		// Custom Download Path
		firefoxOptions.addPreference("plugin.scan.plid.all", false);
		firefoxOptions.addPreference("plugin.scan.Acrobat", "99");
		firefoxOptions.addPreference("browser.download.dir", Const.downloadsPath);
		firefoxOptions.addPreference("browser.download.manager.alertOnEXEOpen", false);
		firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk",
				"application/octet-stream;application/pdf");
		firefoxOptions.addPreference("browser.download.manager.showWhenStarting", false);
		firefoxOptions.addPreference("browser.download.manager.focusWhenStarting", false);
		firefoxOptions.addPreference("browser.helperApps.alwaysAsk.force", false);
		firefoxOptions.addPreference("browser.download.manager.alertOnEXEOpen", false);
		firefoxOptions.addPreference("browser.download.manager.closeWhenDone", false);
		firefoxOptions.addPreference("browser.download.manager.showAlertOnComplete", false);
		firefoxOptions.addPreference("browser.download.manager.useWindow", false);
		firefoxOptions.addPreference("browser.download.manager.showWhenStarting", false);
		firefoxOptions.addPreference("network.proxy.type", proxyType);
		firefoxOptions.addPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
		// Do NOT COMMENT Below line as it is required to download the PDF into download
		// location
		firefoxOptions.addPreference("plugin.disable_full_page_plugin_for_types", "application/pdf");
		return firefoxOptions;
	}

	public ChromeOptions createChromeCapabilites() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ChromeOptions chromeOptions = new ChromeOptions();
		LinkedHashMap<String, Object> chromePrefs = new LinkedHashMap<String, Object>();
		// Set ACCEPT_SSL_CERTS variable to true
//		chromePrefs.put(CapabilityType.ACCEPT_SSL_CERTS, true);
//		chromePrefs.put(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.extensions_to_open", "pdf");
		chromePrefs.put("--always-authorize-plugins", false);
		chromePrefs.put("download.prompt_for_download", true);
		chromePrefs.put("download.default_directory", Const.downloadsPath);
		chromePrefs.put("credentials_enable_service", false);
		chromePrefs.put("profile.password_manager_enabled", false);
		chromeOptions.setExperimentalOption("prefs", chromePrefs);
		chromeOptions.setCapability("network.proxy.type", proxyType);
		chromeOptions.addArguments("--start-maximized");
		chromeOptions.addArguments("disable-infobars");
		// Optional, if not specified, WebDriver will search your path for chromedriver.
		System.setProperty("webdriver.chrome.driver", Const.chromeDriverPath);
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		return chromeOptions;
	}

	public InternetExplorerOptions createIECapabilities() {
		InternetExplorerOptions ieOptions = new InternetExplorerOptions();
		// Set ACCEPT_SSL_CERTS variable to true
		ieOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		ieOptions.setCapability("network.proxy.type", ProxyType.AUTODETECT.ordinal());
		// Added to avoid the Protected Mode settings for all zones
		ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		ieOptions.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "about:blank");
		ieOptions.setCapability(InternetExplorerDriver.SILENT, true);
		ieOptions.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		ieOptions.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		System.setProperty("webdriver.ie.driver", Const.IEDriverPath);
		return ieOptions;
	}

	public String getBrowserVersion() {
		String browser_version;
		Capabilities cap = ((RemoteWebDriver) webdriver).getCapabilities();
		String browsername = cap.getBrowserName();
		String GetuAgent = (String) ((JavascriptExecutor) webdriver).executeScript("return navigator.userAgent;");
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
		return Captialize(browsername) + " browser (Version: " + browserversion + " )";
	}


	public String Captialize(String RequiredWord) {
		return RequiredWord.substring(0, 1).toUpperCase()
				+ RequiredWord.substring(1, RequiredWord.length()).toLowerCase();
	}
	
	public void initializeExtentReport() {
		// Instantiating the ExtentReports
		executingTestCaseName = super.getClass().getSimpleName();
		loggerInstance.startTestCase(executingTestCaseName);

		// Create testInstance
		testInstance = extentInstance.createTest(executingTestCaseName,
				"'" + executingTestCaseName + "' is used to check details in Application.");
		testInstance.assignAuthor(getCurrentlyLoggedInUser);
		testInstance.assignCategory("RegressionTestCases_Test");
	}
	
	public static String randomNumber() {
		Random rand = new Random();
		return String.format("%09d", rand.nextInt(1000000));
	}
	
	public static String randomWord(int charCount) {
		String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyz";
		StringBuilder builder = new StringBuilder();
		while (charCount-- != 0) {
		int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
	
	public static String getTimeStamp(String date) {
		String genDate = date;
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
		if(date.equalsIgnoreCase("CURRENT_DATE")) {
			genDate = sdf.format(new Date());
		}else if(date.contains("+")) {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.DATE, Integer.parseInt(date.split("\\+")[1]));
			genDate = sdf.format(c.getTime());
		}else if(date.contains("-")) {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.DATE, -Integer.parseInt(date.split("\\-")[1]));
			genDate = sdf.format(c.getTime());
		}
		return genDate;
	}

	@AfterTest
	public void closeSession() {
		if (webdriver != null) {
			webdriver.close();
		}
		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec("taskkill /F /IM geckodriver_win32_v0.17.0.exe");
			rt.exec("taskkill /F /IM chromedriver_win32_v2.42.exe");
			rt.exec("taskkill /F /IM IEDriverServer_Win32_v3.4.0.exe");
		} catch (IOException e) {
			// clean up state...
			Thread.currentThread().interrupt();
		}
	}

	@AfterClass
	public void tearDownFunction() {
		loggerInstance.endTestCase(executingTestCaseName);
		// write all resources to report file
		extentInstance.flush();

	}

	@AfterMethod
	public void operationsOnTestCompletion(ITestResult testResult) throws AWTException, IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			Reporter.log(" - FAILED.", true);
			// Create Error Screenshot Directory if doesnot exists
			File dir = new File(Const.ErrorReportPath);
			dir.mkdirs();
			BufferedImage image = new Robot()
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			// Here the screenshot path is reduced to a maximum of 20 literals
			File imagePath = new File(Const.ErrorReportPath + Const.fs + Const.DateFormat + Captialize(runOnBrowser)
					+ "_" + executingTestCaseName.substring(0, Math.min(executingTestCaseName.length(), 20)) + ".jpeg");
			try {
				ImageIO.write(image, "JPG", imagePath);
			} catch (IOException e) {
				throw e;
			}
			// Extent Reports take screenshot
			testInstance.log(Status.FAIL, "Failure Stack Trace: " + testResult.getThrowable().getMessage());
			// Check below line if the screenshot is NOT displayed properly
			String relativeErrImgPath = imagePath.getAbsoluteFile().toString().replace(Const.ReportPath,
					"." + Const.fs);
			Reporter.log(relativeErrImgPath, true);
			// adding screenshots to log
			testInstance.log(Status.INFO, "Refer below Snapshot: ",
					MediaEntityBuilder.createScreenCaptureFromPath(relativeErrImgPath).build());
		} else if (testResult.getStatus() == ITestResult.SKIP) {
			Reporter.log(" - SKIPPED.", false);
			testInstance.log(Status.SKIP, "Test skipped: " + testResult.getThrowable().getMessage());
		} else {
			Reporter.log(" - PASSED.", false);
			testInstance.log(Status.PASS, "'" + executingTestCaseName + "' is passed based on the test criteria.");
		}

	}

}
