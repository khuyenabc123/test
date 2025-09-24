package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.readers.ConfigReader;

import java.lang.reflect.Method;

public class BaseSetup {
    private static WebDriver driver;

    static String browser = ConfigReader.getProperty("browser");
    static String appURL = ConfigReader.getProperty("url");

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setUp(Method method, ITestContext context) {
        driver = DriverFactory.createDriver(browser, appURL);
        context.setAttribute("driver", driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}