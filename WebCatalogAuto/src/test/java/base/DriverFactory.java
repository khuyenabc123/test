package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import utils.readers.ConfigReader;

import java.time.Duration;

public class DriverFactory {
    private static String driverPath = ConfigReader.getProperty("driverPath");

    public static WebDriver createDriver(String browserType, String appURL) {

        return switch (browserType.toLowerCase()) {
            case "chrome" -> initChromeDriver(appURL);
            case "firefox" -> initFirefoxDriver(appURL);
            case "edge" -> initEdgeDriver(appURL);
            default -> {
                System.out.println("Browser: " + browserType + " is invalid. Launching Chrome as default...");
                yield initChromeDriver(appURL);
            }
        };
    }

    private static WebDriver initChromeDriver(String appURL) {
        System.out.println("Launching Chrome browser...");
        System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        return setupDriver(driver, appURL);
    }

    private static WebDriver initFirefoxDriver(String appURL) {
        System.out.println("Launching Firefox browser...");
        System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver");
        WebDriver driver = new FirefoxDriver();
        return setupDriver(driver, appURL);
    }

    private static WebDriver initEdgeDriver(String appURL) {
        System.out.println("Launching Edge browser...");
        System.setProperty("webdriver.edge.driver", driverPath + "msedgedriver");
        WebDriver driver = new EdgeDriver();
        return setupDriver(driver, appURL);
    }

    private static WebDriver setupDriver(WebDriver driver, String appURL) {
        driver.manage().window().maximize();
        driver.navigate().to(appURL);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        return driver;
    }
}