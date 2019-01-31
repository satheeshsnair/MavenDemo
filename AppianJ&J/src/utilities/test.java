package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.math3.genetics.NPointCrossover;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

import jj.pages.AddNewPosition;

public class test {
	
	public static WebDriver driver;
	public int i,j,rowcount;
	public String uname;
	public String pwd;
	static XSSFRow row;
	static XSSFWorkbook wb;
	static XSSFSheet sh1;
	static String Excel = "C:\\Users\\satheeshnair\\Desktop\\infocampus\\Selenium Source Code\\AppianJ&J\\testdata\\Testdata.xlsx";
	
	public static ExtentTest test;
	public AddNewPosition np;
	static Utilites utils = new Utilites(driver, test);
	static int demandid = utils.demandid(1, 1, 0);
	
	
	
	public void WriteExcel(int sheetnum, int row, int column, String value) throws IOException
	{
//		FileInputStream fis = new FileInputStream(Excel);
//		wb = new XSSFWorkbook(fis);
//		sh1=wb.getSheetAt(sheetnum);
//		System.out.println(sh1.getRow(1).getCell(1).getStringCellValue());
//		sh1.getRow(1).createCell(2).setCellValue("PASS");
//		FileOutputStream fos = new FileOutputStream(Excel);
//		wb.write(fos);
//		System.out.println(sh1.getRow(1).getCell(2).getStringCellValue());
	}
	
//	@SuppressWarnings("unused")
//	public void test2(int sheetnum, int row, int column) throws IOException 
//	{
//		FileInputStream fis = new FileInputStream(Excel);
//		wb = new XSSFWorkbook(fis);
//		sh1=wb.getSheetAt(sheetnum);
//		//rowcount = sh1.getLastRowNum();
//		//Cell = sh1.getRow(row).getCell(column).getDateCellValue();
//		Cell = sh1.getRow(row).getCell(column).getDateCellValue();
//		//System.out.println(demand);
//		//pwd = sh1.getRow(row).getCell(column).getStringCellValue();
//		
//	}
	
	public void test2(int sheetnum, int row, int column) throws IOException 
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
		System.out.println(output);
		
	}
	
	public void click() throws InterruptedException 
	{
		Thread.sleep(2000);
		WebElement ele = driver.findElement(By.xpath("//span[contains(text(),'Position Title')]/../following-sibling::div//div[@role='listbox']"));
		System.out.println(ele.getTagName());
	}


	public static void main(String[] args) throws IOException, InterruptedException 
	{
		test t = new test();
		t.click();
		//t.test2(1,1,0);
		//System.out.println(cellDate);
	}

}
