package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.readers.ConfigReader;

import java.lang.reflect.Method;

public class BaseSetup {

    static String browser = ConfigReader.getProperty("browser");
    static String appURL = ConfigReader.getProperty("url");

    @BeforeMethod
    public void setUp(Method method, ITestContext context) {
        WebDriver driver = DriverFactory.createDriver(browser, appURL);
        context.setAttribute("driver", driver);
    }

    @AfterMethod
    public void tearDown() {
        WebDriver driver = DriverFactory.getDriver();
        if (driver != null) {
            driver.quit();
            DriverFactory.removeDriver();
        }
    }
}