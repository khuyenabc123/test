package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BasePage {
    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void click(By locator, int timeoutSeconds) {
        try {
            WebElement element = waitForVisibility(locator, timeoutSeconds);
            scrollToElement(element);
            element.click();
        } catch (Exception e) {
            logger.error("Failed to click element at locator: {}", locator, e);
        }
    }

    public void type(By locator, String text, int timeoutSeconds) {
        try {
            WebElement element = waitForVisibility(locator, timeoutSeconds);
            scrollToElement(element);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            logger.error("Failed to type '{}' into element at locator: {}", text, locator, e);
        }
    }

    public String getText(By locator, int timeoutSeconds) {
        try {
            WebElement element = waitForVisibility(locator, timeoutSeconds);
            scrollToElement(element);
            return element.getText();
        } catch (Exception e) {
            logger.error("Failed to get text from element at locator: {}", locator, e);
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
            logger.error("Failed to select '{}' from dropdown at locator: {}", text, locator, e);
        }
    }

    public void selectByIndex(By locator, int index, int timeoutSeconds) {
        try {
            WebElement dropdown = waitForVisibility(locator, timeoutSeconds);
            scrollToElement(dropdown);
            Select select = new Select(dropdown);
            select.selectByIndex(index);
        } catch (Exception e) {
            logger.error("Failed to select index '{}' from dropdown at locator: {}", index, locator, e);
        }
    }

    public boolean isDisplayed(By locator, int timeoutSeconds) {
        try {
            WebElement element = waitForVisibility(locator, timeoutSeconds);
            scrollToElement(element);
            return element.isDisplayed();
        } catch (Exception e) {
            logger.error("Failed to check if element is displayed at locator: {}", locator, e);
            return false;
        }
    }

    public boolean isEnabled(By locator, int timeoutSeconds) {
        try {
            WebElement element = waitForVisibility(locator, timeoutSeconds);
            scrollToElement(element);
            return element.isEnabled();
        } catch (Exception e) {
            logger.error("Failed to check if element is enabled at locator: {}", locator, e);
            return false;
        }
    }

    public boolean isSelected(By locator, int timeoutSeconds) {
        try {
            WebElement element = waitForVisibility(locator, timeoutSeconds);
            scrollToElement(element);
            return element.isSelected();
        } catch (Exception e) {
            logger.error("Failed to check if element is selected at locator: {}", locator, e);
            return false;
        }
    }

    public WebElement waitForVisibility(By locator, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(BaseSetup.getDriver(), Duration.ofSeconds(timeoutSeconds));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element not visible at locator: {} within {} seconds", locator, timeoutSeconds, e);
            throw e;
        }
    }

    public void scrollToElement(WebElement element) {
        try {
            new Actions(BaseSetup.getDriver()).scrollToElement(element).perform();
        } catch (Exception e) {
            logger.error("Failed to scroll to element: {}", element, e);
        }
    }
}
