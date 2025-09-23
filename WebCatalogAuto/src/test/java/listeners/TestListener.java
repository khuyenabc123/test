package listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.CaptureHelpers;
import utils.ExtentManager;
import utils.LogUtils;

public class TestListener implements ITestListener {
    private String testName;

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName()
                : result.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext context) {
        LogUtils.info("Start testing " + context.getName());

        testName = context.getName();
        ExtentManager.getTestReport(testName);
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info(getTestName(result) + " test is starting...");

        ExtentTest test = ExtentManager.getTestReport(testName).createTest(result.getMethod().getMethodName());
        ExtentManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info(getTestName(result) + " test is passed.");

        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
        CaptureHelpers.logWithScreenShot(driver, Status.PASS, "Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error(getTestName(result) + " test is failed.");

        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
        CaptureHelpers.logWithScreenShot(driver, Status.FAIL, "Test Failed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.warn(getTestName(result) + " test is skipped.");

        ExtentManager.getTest().log(Status.SKIP, "Test Skipped: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        LogUtils.info("End testing " + context.getName());

        ExtentManager.getTestReport(testName).flush();
    }
}
