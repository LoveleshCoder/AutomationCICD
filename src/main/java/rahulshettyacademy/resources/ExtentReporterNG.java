package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	static ExtentReports report;

	public ExtentReports getReportObject() {
		// ExtentReports & ExtentSparkReporter
		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Error Validation Result");
		reporter.config().setDocumentTitle("Test Results");

		report = new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("Tester", "Lovelesh Rajesh Khatarkar");
		return report;
	}
}
