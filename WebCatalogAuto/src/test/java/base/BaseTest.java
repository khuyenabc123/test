package base;

import org.openqa.selenium.WebDriver;

public abstract class BaseTest extends BaseSetup {
    protected WebDriver driver;

    public WebDriver getDriverInstance() {
        return getDriver();
    }
}
