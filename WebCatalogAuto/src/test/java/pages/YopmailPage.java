package pages;

import base.BasePage;
import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import utils.LogUtils;
import utils.readers.ConfigReader;

import java.util.Set;
import java.util.UUID;

public class YopmailPage extends BasePage {
    private final By iframeInbox = By.id("ifinbox");
    private final By iframeMail = By.id("ifmail");
    private final By latestMail = By.cssSelector("div.m");
    private final By mailBody = By.id("mail");

    private final String yopMailUrl = ConfigReader.getProperty("yopMailLoginUrl");

    public YopmailPage() {
        super();
    }

    public void openInbox(String email) {
        DriverFactory.getDriver().get(yopMailUrl + email);
    }

    public String getLatestOtp() {
        WebDriver driver = DriverFactory.getDriver();

        driver.switchTo().frame(driver.findElement(iframeInbox));
        driver.findElement(latestMail).click();

        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(iframeMail));

        String body = driver.findElement(mailBody).getText();
        driver.switchTo().defaultContent();

        LogUtils.info("Get latest OTP code in mail");

        return body.replaceAll(".*?(\\d{6}).*", "$1");
    }

    public String getOTPFromMail(String email, String appWindow) {

        openInbox(email);

        String otp = null;
        long endTime = System.currentTimeMillis() + 10000;

        while (System.currentTimeMillis() < endTime) {
            try {
                otp = getLatestOtp();
                if (otp != null && otp.matches("\\d{6}")) {
                    break;
                }
            } catch (Exception e) {
                // Ignore until OTP appears
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            openInbox(email);
        }

        if (otp == null) {
            throw new RuntimeException("Timeout waiting for OTP for: " + email);
        }

        LogUtils.info("Get OTP code: " + otp);
        return otp;
    }

    public String openYopmailTab(String appWindow) {
        ((JavascriptExecutor) driver).executeScript("window.open('https://yopmail.com', '_blank');");
        Set<String> allWindows = driver.getWindowHandles();
        for (String handle : allWindows) {
            if (!handle.equals(appWindow)) {
                driver.switchTo().window(handle);
                return handle;
            }
        }
        return null;
    }

    public void closeYopmailTab(String yopmailHandle, String appWindow) {
        if (yopmailHandle != null) {
            driver.switchTo().window(yopmailHandle).close();
        }
        driver.switchTo().window(appWindow);
    }

    public String generateRandomYopMail() {
        String randomPart = UUID.randomUUID().toString().replace("-", "");
        randomPart = randomPart.substring(0, 10);
        return randomPart + "@yopmail.com";
    }
}