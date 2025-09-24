package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class YopmailPage extends BasePage {
    private final By iframeInbox = By.id("ifinbox");
    private final By iframeMail = By.id("ifmail");
    private final By latestMail = By.cssSelector("div.m");
    private final By mailBody = By.id("mail");

    public YopmailPage(WebDriver driver) {
        super(driver);
    }

    public void openInbox(String email) {
        driver.get("https://yopmail.com/en/?login=" + email);
    }

    public String getLatestOtp() {
        driver.switchTo().frame(driver.findElement(iframeInbox));
        driver.findElement(latestMail).click();

        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(iframeMail));

        String body = driver.findElement(mailBody).getText();
        driver.switchTo().defaultContent();

        return body.replaceAll(".*?(\\d{6}).*", "$1");
    }

    public void openYopmailTab(String appWindow) {
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("window.open('about:blank','_blank');");

        // Switch to the new tab
        java.util.Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(appWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public String getOTPFromMail(String email, String appWindow) {
        openInbox(email);
        String otp = getLatestOtp();

        driver.close();
        driver.switchTo().window(appWindow);

        return otp;
    }
}
