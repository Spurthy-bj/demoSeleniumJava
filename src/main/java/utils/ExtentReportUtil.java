package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.BaseClass;

public class ExtentReportUtil implements ITestListener {
	private static ExtentSparkReporter spark;
	private static ExtentReports extent;
	private static ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html" + timestamp;

		spark = new ExtentSparkReporter(reportPath);
		extent = new ExtentReports();
		extent.attachReporter(spark);

		// Configuration (optional)
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("My Test Report");
		spark.config().setReportName("framework report");

		// Set system info
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("userName", System.getProperty("user.name"));
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Java version", System.getProperty("java.version"));

		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("browser", browser);

		List<String> includedGroup = context.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroup.isEmpty()) {

			extent.setSystemInfo("includedGroups", includedGroup.toString());
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest = extent.createTest(result.getTestClass().getName());
		extentTest.log(Status.PASS, result.getName() + " successfully executed");
		try {

			String imgPath = new BaseClass().takeScreenshot(result.getName());
			extentTest.addScreenCaptureFromPath(imgPath);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest = extent.createTest(result.getTestClass().getName());
		extentTest.log(Status.FAIL, result.getName() + " failed");
		extentTest.log(Status.INFO, result.getThrowable().getMessage());
		try {

			String imgPath = new BaseClass().takeScreenshot(result.getName());
			extentTest.addScreenCaptureFromPath(imgPath);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
