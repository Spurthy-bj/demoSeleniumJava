package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public static final String path = System.getProperty("user.dir") + "//src/test//resources//DemoTestData.xlsx";
//	private String sheetName ;
	private FileInputStream file;
	private XSSFWorkbook workBook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;
	private String data;
	
	public int getRowNumber(String sheetName) throws IOException {

		file = new FileInputStream(path);
		workBook = new XSSFWorkbook(file);
		sheet = workBook.getSheet(sheetName);

		int rowCount = sheet.getLastRowNum();

		return rowCount;

	}

	public int getCellNumber(String sheetName,int rowNumber) throws IOException {
		file = new FileInputStream(path);
		workBook = new XSSFWorkbook(file);
		sheet = workBook.getSheet(sheetName);

		row = sheet.getRow(rowNumber);
		int cellCount = row.getLastCellNum();

		return cellCount;

	}

	public String getCellData(String sheetName,int rowNumber, int colNum) throws IOException {
		file = new FileInputStream(path);
		workBook = new XSSFWorkbook(file);
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rowNumber);
		cell=row.getCell(colNum);
		
		DataFormatter format=new DataFormatter();
		
		try {
		data=format.formatCellValue(cell);
		}
		catch(Exception e) {
			data="";
		}
		
		return data;
	}

}
