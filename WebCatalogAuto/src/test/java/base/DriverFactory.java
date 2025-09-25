package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.readers.ConfigReader;

import java.time.Duration;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final String driverPath = ConfigReader.getProperty("driverPath");

    public static WebDriver createDriver(String browserType, String appURL) {
        WebDriver drv = switch (browserType.toLowerCase()) {
            case "firefox" -> initFirefoxDriver();
            case "edge" -> initEdgeDriver();
            default -> initChromeDriver();
        };

        setupDriver(drv, appURL);
        driver.set(drv);
        return drv;
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void removeDriver() {
        driver.remove();
    }

    private static WebDriver initChromeDriver() {
        System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver");
        return new ChromeDriver();
    }

    private static WebDriver initFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver");
        return new FirefoxDriver();
    }

    private static WebDriver initEdgeDriver() {
        System.setProperty("webdriver.edge.driver", driverPath + "msedgedriver");
        return new EdgeDriver();
    }

    private static void setupDriver(WebDriver driver, String appURL) {
        driver.manage().window().maximize();
        driver.get(appURL);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
}