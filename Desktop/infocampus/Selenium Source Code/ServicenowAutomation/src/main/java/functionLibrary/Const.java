package functionLibrary;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public final class Const {

	public static int minWaitTime = 3000;

//	public final static String pageUrl = "https://zurichstg.service-now.com/login.do";
	public final static String servicenowPortalUrl = "https://dev22930.service-now.com";
	public final static String sUsername = "manoj";
	public final static String sPassword = "Manoj@123";
	public final static String servicenowUrl = "https://zurichstg.service-now.com";
//	public final static String servicenowPortalUrl = "https://zurichstg.service-now.com/ewp";
//	public final static String sUsername = "mkumar";
//	public final static String sPassword = "Zurich1";
	public final static String defUser = "Manoj Kumar";
	// for additional accuracy use the String format for date as
	// "ddmmyyyy_hhmmss_sss_"
	public static final String DateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss_").format(new Date());
	public final static String WEEK_NUMBER = new SimpleDateFormat("w").format(new java.util.Date());
	// For Report start time and End time format
	private static SimpleDateFormat DateFormatSettings;
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
	public static final String fs = File.separator;
	public static final String configPath = System.getProperty("user.dir") + fs + "src" + fs + "main" + fs + "resources"
			+ fs + "configuration" + fs;
	public static final String testDataPath = System.getProperty("user.dir") + fs + "src" + fs + "test" + fs
			+ "resources" + fs + "testData" + fs;
	public static String ReportPath = System.getProperty("user.dir") + fs + "target" + fs + "ExtentReports" + fs
			+ "Week" + Const.WEEK_NUMBER + fs;
	public static final String downloadsPath = System.getProperty("user.dir") + fs + "downloads" + fs;
	public static String ErrorReportPath = System.getProperty("user.dir") + fs + "target" + fs + "ExtentReports" + fs
			+ "Week" + Const.WEEK_NUMBER + fs;
	// Driver path location
	public static final String firefoxDriverPath = configPath + fs + "geckodriver_win32_v0.17.0.exe";
	public static final String chromeDriverPath = configPath + fs + "chromedriver_win32_v2.42.exe";
	public static final String IEDriverPath = configPath + fs + "IEDriverServer_Win32_v3.4.0.exe";

	private Const() {
		throw new IllegalAccessError("Const class");
	}

	/**
	 * @return the dateFormatSettings
	 */
	public static SimpleDateFormat getDateFormatSettings() {
		return DateFormatSettings;
	}

	/**
	 * @param dateFormatSettings the dateFormatSettings to set
	 */
	public static void setDateFormatSettings() {
		DateFormatSettings = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.ENGLISH);
	}
}
