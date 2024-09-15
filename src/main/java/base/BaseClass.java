package base;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	protected static WebDriver driver;
	public static Logger log;
	protected Properties properties = new Properties();
	private static FileReader propertyFile;

	@BeforeClass
	@Parameters({ "browser" })
	public void setUp1(String br) throws IOException {
		log = LogManager.getLogger(this.getClass());
		final String propertyFilePath = "./src//test//resources//config.properties";

		propertyFile = new FileReader(propertyFilePath);
		properties.load(propertyFile);

//		ChromeOptions options=new ChromeOptions();
//		options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
//		options.addArguments("--headless=new");

		// switch statements for chrome (browser)
		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver(options);
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get(properties.getProperty("url"));

		log.info("before");

	}

	public String takeScreenshot(String screenShotname) {
		if (driver == null) {
			throw new IllegalStateException("Driver is not initialized.");
		}
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "/screenshots/" + screenShotname + "_" + timestamp
				+ ".jpg";

		File targetFile = new File(targetFilePath);
		try {
			// Copy the screenshot to the desired location
			FileUtils.copyFile(sourceFile, targetFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return targetFilePath;
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
