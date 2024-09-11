package utils;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderCalling {
	
	@DataProvider(name="Demo")
	public String[][] getData() throws IOException{
		
		ExcelUtility excel=new ExcelUtility();
		
		int row=excel.getRowNumber("Demo1");
		int col=excel.getCellNumber("Demo1",row);
		
		String dest[][]=new String[row][col];
		
		for(int i=1;i<=row;i++) {
			for (int j=0;j<col;j++) {
				dest[i-1][j]=excel.getCellData("Demo1",i, j);
			}
		}
		
		return dest;
		
	}

}
