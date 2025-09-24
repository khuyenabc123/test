package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
}
