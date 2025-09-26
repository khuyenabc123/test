package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
            LogUtils.error("Failed to click element at locator: {}"+ e);
        }
    }

    public void type(By locator, String text, int timeoutSeconds) {
        try {
            WebElement element = waitForVisibility(locator, timeoutSeconds);
            scrollToElement(element);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            LogUtils.error("Failed to type '{}' into element at locator: {}"+ e);
        }
    }

    public String getText(By locator, int timeoutSeconds) {
        try {
            WebElement element = waitForVisibility(locator, timeoutSeconds);
            scrollToElement(element);
            return element.getText();
        } catch (Exception e) {
            LogUtils.error("Failed to get text from element at locator: {}"+ e);
            return "";
        }
    }

    public void selectByText(By locator, String text, int timeoutSeconds) {
        try {
            WebElement dropdown = waitForVisibility(locator, timeoutSeconds);
            scrollToElement(dropdown);
            Select select = new Select(dropdown);
            select.selectByVisibleText(text);
        } catch (Exception e) {
            LogUtils.error("Failed to select '{}' from dropdown at locator: {}"+ e);
        }
    }

    public void selectByIndex(By locator, int index, int timeoutSeconds) {
        try {
            WebElement dropdown = waitForVisibility(locator, timeoutSeconds);
            scrollToElement(dropdown);
            Select select = new Select(dropdown);
            select.selectByIndex(index);
        } catch (Exception e) {
            LogUtils.error("Failed to select index '{}' from dropdown at locator: {}" + e);
        }
    }

    public boolean isDisplayed(By locator, int timeoutSeconds) {
        try {
            WebElement element = waitForVisibility(locator, timeoutSeconds);
            scrollToElement(element);
            return element.isDisplayed();
        } catch (Exception e) {
            LogUtils.error("Failed to check if element is displayed at locator: {}"+ e);
            return false;
        }
    }

    public boolean isEnabled(By locator, int timeoutSeconds) {
        try {
            WebElement element = waitForVisibility(locator, timeoutSeconds);
            scrollToElement(element);
            return element.isEnabled();
        } catch (Exception e) {
            LogUtils.error("Failed to check if element is enabled at locator: {}"+ e);
            return false;
        }
    }

    public boolean isSelected(By locator, int timeoutSeconds) {
        try {
            WebElement element = waitForVisibility(locator, timeoutSeconds);
            scrollToElement(element);
            return element.isSelected();
        } catch (Exception e) {
            LogUtils.error("Failed to check if element is selected at locator: {}"+ e);
            return false;
        }
    }

    public WebElement waitForVisibility(By locator, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(timeoutSeconds));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            LogUtils.error("Element not visible at locator: {} within {} seconds" + e);
            throw e;
        }
    }

    public void scrollToElement(WebElement element) {
        try {
            new Actions(DriverFactory.getDriver()).scrollToElement(element).perform();
        } catch (Exception e) {
            LogUtils.error("Failed to scroll to element: {}"+ e);
        }
    }

    public void reloadPage() {
        try {
            DriverFactory.getDriver().navigate().refresh();
            LogUtils.info("Page reloaded successfully.");
        } catch (Exception e) {
            LogUtils.error("Failed to reload the page: " + e);
        }
    }
}