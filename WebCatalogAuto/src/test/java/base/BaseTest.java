package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest extends BaseSetup {
    protected WebDriver driver;

    @BeforeMethod
    public void initDriver() {
        driver = BaseSetup.getDriver();
    }

    public WebDriver getDriverInstance() {
        return driver;
    }
}