package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import jj.pages.Login;

public class Utilites {
	
	public WebDriver driver;
	public Utilites utilities;
	public int i,j,rowcount,demand;
	public String uname, sow;
	public String pwd;
	static XSSFRow row;
	static XSSFWorkbook wb;
	static XSSFSheet sh1;
	static String Excel = "C:\\Users\\satheeshnair\\Desktop\\infocampus\\Selenium Source Code\\AppianJ&J\\testdata\\Testdata.xlsx";
	static String PassSnapShots = "C:\\Users\\satheeshnair\\Desktop\\infocampus\\Selenium Source Code\\AppianJ&J\\testdata\\passedsnaps\\passedsnaps";
	static String FailedSnapShops="C:\\Users\\satheeshnair\\Desktop\\infocampus\\Selenium Source Code\\AppianJ&J\\testdata\\failedsnaps\\failedsnaps";
	
	public Login login;
	public ExtentTest test;
		
	public Utilites(WebDriver driver, ExtentTest test) 
	{
		this.driver = driver;
		this.test = test;
	}
	
	public void readexcel(int sheetnum, int row, int column) throws Exception
	{
		try
		{
			FileInputStream fis = new FileInputStream(Excel);
			wb = new XSSFWorkbook(fis);
			sh1=wb.getSheetAt(sheetnum);
			rowcount = sh1.getLastRowNum();
			uname = sh1.getRow(row).getCell(column).getStringCellValue();
			pwd = sh1.getRow(row).getCell(column).getStringCellValue();
			//sow = sh1.getRow(row).getCell(column).getStringCellValue();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void WriteExcel(int sheetnum, int row, int column, String value) throws IOException
	{
		try {
				FileInputStream fis = new FileInputStream(Excel);
				wb = new XSSFWorkbook(fis);
				sh1=wb.getSheetAt(sheetnum);
				sh1.getRow(row).createCell(column).setCellValue(value);
				FileOutputStream fos = new FileOutputStream(Excel);
				wb.write(fos);
				test.pass("Wrote to excel:" + fos);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
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
		test.pass("Deamind ID entered" + demand);
		return column;
	}
	
	public void date_excel(int sheetnum, int row, int column, By locator) throws IOException 
	{
		FileInputStream fis = new FileInputStream(Excel);
		wb = new XSSFWorkbook(fis);
		sh1=wb.getSheetAt(sheetnum);
		Date cellDate = sh1.getRow(row).getCell(column).getDateCellValue();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(cellDate); // Now use today date.
		//c.add(Calendar.DATE); // Adding 7 days
		String output = sdf.format(c.getTime());
		driver.findElement(locator).sendKeys(output);
		test.pass("Entered Date" + output);
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
	
	public String SOW(int sheetnum, int row, int column)
	{
		FileInputStream fis;
		String SOW = null;
		try {
			fis = new FileInputStream(Excel);
			wb = new XSSFWorkbook(fis);
			sh1=wb.getSheetAt(sheetnum);
			SOW = sh1.getRow(row).getCell(column).getStringCellValue();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//test.pass("SOW entered" + SOW);
		return SOW;
	}
}
