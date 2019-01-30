package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class test {
	
	public static WebDriver driver;
	public int i,j,rowcount;
	public String uname;
	public String pwd;
	static XSSFRow row;
	static XSSFWorkbook wb;
	static XSSFSheet sh1;
	static String Excel = "C:\\Users\\satheeshnair\\Desktop\\infocampus\\Selenium Source Code\\AppianJ&J\\testdata\\Testdata.xlsx";
	
	static Utilites utils = new Utilites(driver);
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
	
	@SuppressWarnings("unused")
	public void test2(int sheetnum, int row, int column) throws IOException 
	{
		FileInputStream fis = new FileInputStream(Excel);
		wb = new XSSFWorkbook(fis);
		sh1=wb.getSheetAt(sheetnum);
		rowcount = sh1.getLastRowNum();
		int demand = (int)sh1.getRow(row).getCell(column).getNumericCellValue();
		//System.out.println(demand);
		//pwd = sh1.getRow(row).getCell(column).getStringCellValue();
		
	}

	public static void main(String[] args) throws IOException 
	{
		test t = new test();
		t.test2(0,1,3);
		System.out.println(demandid);
	}

}
