package regression;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentTest;

import pages.AddNewPosition;
import pages.Login;
import utilities.Utilites;

public class test {
	
	public static WebDriver driver;
	String i;
	public int j,rowcount;
	public static int GetrowCount,K,L;
	int GetIntvalue;
	public static String GetTestCaseName,GetMethodName, GetValue;
	public String uname;
	public String pwd, sow;
	static XSSFRow row;
	static XSSFWorkbook wb;
	static XSSFSheet sh1;
	//static String Excel = "C:\\Users\\satheeshnair\\Desktop\\infocampus\\Selenium Source Code\\AppianJ&J\\testdata\\Testdata.xlsx";
	static String file="C:\\Users\\satheeshnair\\Desktop\\TestdataByMethod.xlsx";
	
	public static ExtentTest test;
	public AddNewPosition np;
	static Utilites utils = new Utilites(driver, test);
	//static int demandid = utils.demandid(1, 1, 0);
	protected String url;
	Login login;
	
//	public void setup() throws Exception {
//		System.setProperty("webdriver.chrome.driver","C:\\Users\\satheeshnair\\Desktop\\infocampus\\Softwares\\Selenium Jars\\chromedriver.exe");
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--start-maximized");
//		driver = new ChromeDriver(options);
//		url = "https://jnjtest.appiancloud.com/suite/portal/login.jsp";
//		driver.get(url);
//		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//		login = new Login(driver,test); //initialize objectl
//		login.username(0,1,0); //sheet num, row , column
//		login.password(0,1,1); //sheet num, row , column
//		login.login();
//		//upload();
//		logout();
//	}
	
//	public void WriteExcel(int sheetnum, int row, int column, String value) throws IOException
//	{
////		FileInputStream fis = new FileInputStream(Excel);
////		wb = new XSSFWorkbook(fis);
////		sh1=wb.getSheetAt(sheetnum);
////		System.out.println(sh1.getRow(1).getCell(1).getStringCellValue());
////		sh1.getRow(1).createCell(2).setCellValue("PASS");
////		FileOutputStream fos = new FileOutputStream(Excel);
////		wb.write(fos);
////		System.out.println(sh1.getRow(1).getCell(2).getStringCellValue());
//	}
	
	
//	public  String test1(int sheetnum, int row, int column) throws IOException 
//	{
//		FileInputStream fis;
//		String SOW = null;
//		try {
//			fis = new FileInputStream(Excel);
//			wb = new XSSFWorkbook(fis);
//			sh1=wb.getSheetAt(sheetnum);
//			SOW = sh1.getRow(row).getCell(column).getStringCellValue();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(SOW);
//		//return column;
//		return SOW;
//	}
//	
//	public void test2(int sheetnum, int row, int column) throws IOException 
//	{
//		FileInputStream fis = new FileInputStream(Excel);
//		wb = new XSSFWorkbook(fis);
//		sh1=wb.getSheetAt(sheetnum);
//		Date cellDate = sh1.getRow(row).getCell(column).getDateCellValue();
//		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//		Calendar c = Calendar.getInstance();
//		c.setTime(cellDate); // Now use today date.
//		//c.add(Calendar.DATE); // Adding 7 days
//		String output = sdf.format(c.getTime());
//		System.out.println(output);
//		
//	}
//	
//	public void click() throws InterruptedException 
//	{
//		Thread.sleep(2000);
//	}
//	
//	public void upload() throws InterruptedException
//	{
//		Thread.sleep(3000);
//		WebElement element = driver.findElement(By.xpath("//*[@id='uploadname1']"));
//		Thread.sleep(3000);
//		element.click();
//		element.sendKeys("C:\\Users\\satheeshnair\\Desktop\\Dummy1.docx");
//	}
//	public void readexcel() throws Exception
//	{
//		try
//		{
//			FileInputStream fis = new FileInputStream(Excel);
//			wb = new XSSFWorkbook(fis);
//			sh1=wb.getSheetAt(1);
//			rowcount = sh1.getLastRowNum();
//			//uname = sh1.getRow(1).getCell(0).getStringCellValue();
//			//pwd = sh1.getRow(1).getCell(1).getStringCellValue();
//			sow = sh1.getRow(1).getCell(3).getStringCellValue();
//			System.out.println(uname + pwd + sow);
//		}
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//	}
//	public void logout() throws InterruptedException
//	{
//		driver.findElement(By.xpath("//a[@class='gwt-Anchor pull-down-toggle']/span")).click();
//		Thread.sleep(3000);
//		driver.findElement(By.xpath("(//a[@class='gwt-Anchor pull-down-menu-item']/span)[6]")).click();
//		
//	}
	
	public String GetData_Method(String TestCaseName,String MethodName)
    {
           try {
           //LogGeneration.GenerateLog("ExpectedTestcasename and ExpectedMethodname: "+TestCaseName +" and "+MethodName);             
           FileInputStream inputStream = new FileInputStream(file);
           wb = new XSSFWorkbook(inputStream);             
           sh1=wb.getSheetAt(0);
           //Find number of rows in excel file
        GetrowCount = sh1.getLastRowNum()-sh1.getFirstRowNum();   
        System.out.println(GetrowCount);
        //LogGeneration.GenerateLog("Total no of rows:"+GetrowCount);
           for(K=1;K < GetrowCount+1;K++)
           {
                  for (L = 0; L <2; L++) 
                  {
                        GetTestCaseName=sh1.getRow(K).getCell(L).getStringCellValue();
                        //LogGeneration.GenerateLog("TestCaseName:"+GetTestCaseName);
                        if (TestCaseName.equals(GetTestCaseName))
                           {
                               GetMethodName=sh1.getRow(K).getCell(L=L+1).getStringCellValue();
                               //LogGeneration.GenerateLog("MethodName:"+GetMethodName);
                               if(MethodName.equals(GetMethodName))
                               {
                                      
                                       GetValue=sh1.getRow(K).getCell(L=L+1).getStringCellValue();
                                       System.out.println(GetValue);
                                     // LogGeneration.GenerateLog("TestCase Name:"+GetTestCaseName+" MethodName:"+GetMethodName +" and Value:"+GetValue ); 
                                }
                            }
                        
                  }
                  
                  
           }
           
    }
    catch(Exception e)
    {
           System.out.println(e); 
     }
           return GetValue;
                  
           }

	
	public int demandid(String TestCaseName,String MethodName )
	{
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			wb = new XSSFWorkbook(fis);
			sh1=wb.getSheetAt(0);
			rowcount = sh1.getLastRowNum();
			GetrowCount = sh1.getLastRowNum()-sh1.getFirstRowNum();   
	        System.out.println(GetrowCount);
	        //LogGeneration.GenerateLog("Total no of rows:"+GetrowCount);
	           for(K=1;K < GetrowCount+1;K++)
	           {
	              for (L = 0; L <2; L++) 
	                {
	                  GetTestCaseName=sh1.getRow(K).getCell(L).getStringCellValue();
	                  //LogGeneration.GenerateLog("TestCaseName:"+GetTestCaseName);
	                  if (TestCaseName.equals(GetTestCaseName))
	                    {
	                	  GetMethodName=sh1.getRow(K).getCell(L=L+1).getStringCellValue();
	                      //LogGeneration.GenerateLog("MethodName:"+GetMethodName);
	                     if(MethodName.equals(GetMethodName))
	                       {
	                        GetIntvalue=(int) sh1.getRow(K).getCell(L=L+1).getNumericCellValue();
	                        System.out.println(GetIntvalue);
	                        // LogGeneration.GenerateLog("TestCase Name:"+GetTestCaseName+" MethodName:"+GetMethodName +" and Value:"+GetValue ); 
	                        }
	                      }
	                  }
	           }
	    }
	    catch(Exception e)
	    {
	           System.out.println(e); 
	    }
		return GetIntvalue;
	}
	public void date(String TestCaseName,String MethodName )
	{
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			wb = new XSSFWorkbook(fis);
			sh1=wb.getSheetAt(0);
			rowcount = sh1.getLastRowNum();
			GetrowCount = sh1.getLastRowNum()-sh1.getFirstRowNum();   
	        //LogGeneration.GenerateLog("Total no of rows:"+GetrowCount);
	           for(K=1;K < GetrowCount+1;K++)
	           {
	              for (L = 0; L <2; L++) 
	                {
	                  GetTestCaseName=sh1.getRow(K).getCell(L).getStringCellValue();
	                  //LogGeneration.GenerateLog("TestCaseName:"+GetTestCaseName);
	                  if (TestCaseName.equals(GetTestCaseName))
	                    {
	                	  GetMethodName=sh1.getRow(K).getCell(L=L+1).getStringCellValue();
	                      //LogGeneration.GenerateLog("MethodName:"+GetMethodName);
	                     if(MethodName.equals(GetMethodName))
	                       {
	                    	 Date cellDate =sh1.getRow(K).getCell(L=L+1).getDateCellValue();
	                    	 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	                 		Calendar c = Calendar.getInstance();
	                 		c.setTime(cellDate); // Now use today date.
	                 		//c.add(Calendar.DATE); // Adding 7 days
	                 		String output = sdf.format(c.getTime());
	                 		System.out.println(output + " "+"date");
	                 		//driver.findElement(locator).sendKeys(output);
	                 		//test.pass("Entered Date" + output);
	                        // LogGeneration.GenerateLog("TestCase Name:"+GetTestCaseName+" MethodName:"+GetMethodName +" and Value:"+GetValue ); 
	                        }
	                      }
	                  }
	           }
	    }
	    catch(Exception e)
	    {
	           System.out.println(e); 
	    }
		
	}
	
	public static void main(String[] args) throws Exception 
	{
		test t = new test();
		//t.click();
		//t.test1(1,1,3);
//		t.GetData_Method("CreateSOW", "Uname");
//		t.GetData_Method("CreateSOW", "Password");
//		t.demandid("CreateSOW", "SOW");
//		t.GetData_Method("CreateSOW", "test");
		t.date("CreateSOW", "date");
//		t.readexcel();
		
		//System.out.println(cellDate);
	}

}
