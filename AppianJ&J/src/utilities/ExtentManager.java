package utilities;

import java.io.File;

import org.testng.Reporter;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class ExtentManager {
	
	private static ExtentReports extent;
	private static ExtentTest test;
	private static ExtentHtmlReporter htmlReporter;
	private static String filePath;
	
	public ExtentManager(){
		filePath= System.getProperty("user.dir")+File.separator+"ExecutionReports"+File.separator+"AutomationTestReport.html";
		//Create ExtentReport Directory if doesn't exists
		File dir = new File(System.getProperty("user.dir")+File.separator+"ExecutionReports"+File.separator);
		//If SecurityManager.checkWrite(java.lang.String) method denies write access to the file.Hence made the directory writable
		if(!(dir.setWritable(true))){
			Reporter.log("Exception in ExtentManager function"); 
		}
		dir.mkdirs();
	}
	
	public ExtentReports createExtentRep(){
		if (extent != null)
            return extent; //avoid creating new instance of HTML file if it is not null
		htmlReporter=getHtmlReporter();
        extent = new ExtentReports();		
        extent.setSystemInfo("Operating System", "Windows 10");
        extent.setSystemInfo("Selenium Version", "3.14.159");
        // class view:
        extent.setAnalysisStrategy(AnalysisStrategy.CLASS);
		extent.attachReporter(htmlReporter);
		return extent;
	}
 
	private ExtentHtmlReporter getHtmlReporter() {
		Reporter.log(filePath,false);
		htmlReporter = new ExtentHtmlReporter(new File(filePath));		
        htmlReporter.loadXMLConfig(System.getProperty("user.dir")+File.separator+"src"+File.separator+"utilities"+File.separator+"extent.xml");
        htmlReporter.setAppendExisting(false);    
        return htmlReporter;
	}
	
		
	public ExtentTest createTest(String name, String description){
		test = extent.createTest(name, description);
		return test;
	}
}