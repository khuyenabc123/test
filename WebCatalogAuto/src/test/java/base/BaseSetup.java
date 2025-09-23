package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.readers.ConfigReader;

import java.lang.reflect.Method;
import java.time.Duration;

public class BaseSetup {
    private static WebDriver driver;

    static String driverPath = ConfigReader.getProperty("driverPath");
    static String browser = ConfigReader.getProperty("browser");
    static String appURL = ConfigReader.getProperty("url");

    public static WebDriver getDriver() {
        return driver;
    }

    private void setDriver(String browserType, String appURL) {
        switch (browserType) {
            case "chrome":
                driver = initChromeDriver(appURL);
                break;
            case "firefox":
                break;
            case "edge":
                break;
            default:
                System.out.println("Browser: " + browserType + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver(appURL);
        }
    }

    private static WebDriver initChromeDriver(String appURL) {
        System.out.println("Launching Chrome browser...");

        System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(appURL);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        return driver;
    }

    @BeforeMethod
    public void setUp(Method method, ITestContext context) {
        setDriver(browser, appURL);
        context.setAttribute("driver", driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
