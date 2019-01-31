package utilities;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import functionLibrary.Const;

public class ExcelUtility {
	
	public LinkedHashMap<String, String> getTestData(String sheetName) {
		LinkedHashMap<String, String> testData = new LinkedHashMap<String, String>();
		XSSFWorkbook wb = null;
		try {
			FileInputStream myExcel = new FileInputStream(Const.testDataPath+"/ServicenowTestData.xlsx");
			wb = new XSSFWorkbook(myExcel);
			XSSFSheet sh = wb.getSheet(sheetName);
			int rowcount = sh.getPhysicalNumberOfRows();
			int colCount = 0;
			for (int i = 0; i < rowcount; i++) {
				testData.put(sh.getRow(i).getCell(colCount).getStringCellValue(),
						sh.getRow(i).getCell(colCount + 1).getStringCellValue());
				colCount = 0;
			}
			System.out.println(testData);
		}
		catch (IOException e) {
			System.out.println("Exception thrown while reading excel");
			e.printStackTrace();
		}
		finally {
			if (wb != null) {
				try {
					wb.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return testData;
	}
	
	public String getData(String sheetName, int rowNum, int colNum) throws Exception{

		FileInputStream fis=new FileInputStream(Const.testDataPath);
		Workbook wb=WorkbookFactory.create(fis);
		System.out.println(sheetName);
		Sheet sh=wb.getSheet(sheetName);
		Row row=sh.getRow(rowNum);
		Cell cel=row.getCell(colNum);
		return cel.getStringCellValue();
	}

	public void setData(String sheetName, int rowNum, int colNum, String dataToSet) throws Exception{

		FileInputStream myExcel = new FileInputStream(Const.testDataPath);
		Workbook wb=WorkbookFactory.create(myExcel);
		Sheet sh=wb.getSheet(sheetName);
		Row row=sh.getRow(rowNum);
		Cell cel=row.createCell(colNum);
		cel.setCellType(cel.CELL_TYPE_STRING);
		cel.setCellValue(dataToSet);
		
		FileOutputStream fos = new FileOutputStream(Const.testDataPath);
		wb.write(fos);		
	}
}