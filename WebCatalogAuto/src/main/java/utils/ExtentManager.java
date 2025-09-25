package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import utils.readers.ConfigReader;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentTest getTest() {
        return test;
    }

    public static void setTest(ExtentTest testInstance) {
        test = testInstance;
    }

    public static ExtentReports getTestReport(String testName) {
        if(extent == null) {
            String timestamp = new SimpleDateFormat("ddMMyyy_HHmmss").format(new Date());
            String reportFileName = ConfigReader.getProperty("reportPath") + testName + "_" + timestamp + ".html";

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFileName);
            sparkReporter.config().setDocumentTitle("Test Report");
            sparkReporter.config().setReportName("Test Execution Report");
            sparkReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }

        return extent;
    }
}
