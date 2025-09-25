package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LogUtils;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
    }

    public void click(By locator, int timeoutSeconds) {
        try {
            WebElement element = waitForVisibility(locator, timeoutSeconds);
            scrollToElement(element);
            element.click();
        } catch (Exception e) {
            LogUtils.error("Failed to click element at locator: " + e.getMessage());
        }
    }

    public void type(By locator, String text, int timeoutSeconds) {
        WebElement element = waitForVisibility(locator, timeoutSeconds);
        scrollToElement(element);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator, int timeoutSeconds) {
        return waitForVisibility(locator, timeoutSeconds).getText();
    }

    public boolean isDisplayed(By locator, int timeoutSeconds) {
        try {
            return waitForVisibility(locator, timeoutSeconds).isDisplayed();
        } catch (Exception e) {
            LogUtils.error("Element not displayed: " + e.getMessage());
            return false;
        }
    }

    public WebElement waitForVisibility(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void scrollToElement(WebElement element) {
        new Actions(driver).scrollToElement(element).perform();
    }

    public void reloadPage() {
        driver.navigate().refresh();
    }

    public boolean isEnabled(By locator, int timeoutSeconds) {
        try {
            return waitForVisibility(locator, timeoutSeconds).isEnabled();
        } catch (Exception e) {
            LogUtils.error("Element not enabled: " + e.getMessage());
            return false;
        }
    }
}