package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import jj.pages.Login;

public class Utilites {
	
	public WebDriver driver;
	public Utilites utilities;
	public int i,j,rowcount,demand;
	public String uname;
	public String pwd;
	static XSSFRow row;
	static XSSFWorkbook wb;
	static XSSFSheet sh1;
	static String Excel = "C:\\Users\\satheeshnair\\Desktop\\infocampus\\Selenium Source Code\\AppianJ&J\\testdata\\Testdata.xlsx";
	static String PassSnapShots = "C:\\Users\\satheeshnair\\Desktop\\infocampus\\Selenium Source Code\\AppianJ&J\\testdata\\passedsnaps\\passedsnaps";
	static String FailedSnapShops="C:\\Users\\satheeshnair\\Desktop\\infocampus\\Selenium Source Code\\AppianJ&J\\testdata\\failedsnaps\\failedsnaps";
	
	public Login login;
		
	public Utilites(WebDriver driver) 
	{
		this.driver = driver;
	}
	public void readexcel(int sheetnum, int row, int column) throws Exception
	{
		FileInputStream fis = new FileInputStream(Excel);
		wb = new XSSFWorkbook(fis);
		sh1=wb.getSheetAt(sheetnum);
		rowcount = sh1.getLastRowNum();
		uname = sh1.getRow(row).getCell(column).getStringCellValue();
		pwd = sh1.getRow(row).getCell(column).getStringCellValue();
	}
	public int demandid(int sheetnum, int row, int column)
	{
		FileInputStream fis;
		try {
			fis = new FileInputStream(Excel);
			wb = new XSSFWorkbook(fis);
			sh1=wb.getSheetAt(sheetnum);
			rowcount = sh1.getLastRowNum();
			return demand = (int)sh1.getRow(row).getCell(column).getNumericCellValue();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return column;
	}
	public void WriteExcel(int sheetnum, int row, int column, String value) throws IOException
	{
		FileInputStream fis = new FileInputStream(Excel);
		wb = new XSSFWorkbook(fis);
		sh1=wb.getSheetAt(sheetnum);
		sh1.getRow(row).createCell(column).setCellValue(value);
		FileOutputStream fos = new FileOutputStream(Excel);
		wb.write(fos);
	}
	
	public void passsnaps(WebDriver driver) throws Exception
	{
		File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(PassSnapShots + timestamp()+".jpg");
		FileUtils.copyFile(scr,dest);
	}
	public void failsnaps(WebDriver driver) throws Exception
	{
		File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(FailedSnapShops + timestamp()+".jpg");
		FileUtils.copyFile(scr,dest);
	}
	public String timestamp() 
	{
	    return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new java.util.Date());
	}
	
	public void dropdown(By locator) throws InterruptedException
	{
		
		Thread.sleep(2000);
		WebElement element = driver.findElement(locator);
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//js.executeScript("arguments[0].click();", element);
		action.moveToElement(element).click().build().perform();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		action.sendKeys(Keys.ARROW_DOWN).build().perform();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		action.sendKeys(Keys.TAB).build().perform();
		Thread.sleep(2000);
	}
	public void state(By locator) throws InterruptedException
	{
		Thread.sleep(2000);
		WebElement element = driver.findElement(locator);
		Actions action = new Actions(driver);
		WebElement waits = (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(locator));
		action.moveToElement(element).click();
		action.perform();
		action.build();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		action.sendKeys(Keys.ARROW_DOWN).build().perform();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		action.sendKeys(Keys.TAB).build().perform();
		Thread.sleep(2000);
	}
	
	public void click(By locator) throws InterruptedException 
	{
		Thread.sleep(2000);
		driver.findElement(locator).click();
	}
	public void date(By locator, int days) throws InterruptedException
	{
		 Thread.sleep(2000);
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		 Calendar c = Calendar.getInstance();
		 c.setTime(new Date()); // Now use today date.
		 c.add(Calendar.DATE, days); // Adding 7 days
		 String output = sdf.format(c.getTime());
		 driver.findElement(locator).sendKeys(output);
	}
	public void sendkey(By locator, String value) throws InterruptedException 
	{
		Thread.sleep(2000);
		driver.findElement(locator).sendKeys(value);
	}
	public boolean isXpathExists(By xpath) {
		try {
			 driver.findElement(xpath);
		}catch (Exception e) {
			return false;
		}
		return true;
	}
}
