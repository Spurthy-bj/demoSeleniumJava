package testCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.BlazeDemoPage;
import utils.DataProviderCalling;

public class BlazeDemoTest  extends BaseClass{
	private BlazeDemoPage blazeDemoPage;
	

	@Test(dataProvider="Demo", dataProviderClass=DataProviderCalling.class)
	public void findflight(String from,String to) {
		blazeDemoPage = new BlazeDemoPage(driver);
		blazeDemoPage.selectDestination(from, to);
		log.info("find flight");
		driver.navigate().back();
		System.out.println(properties.getProperty("url"));
	}

	

}
