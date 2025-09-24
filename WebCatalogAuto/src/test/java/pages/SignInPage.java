package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.LogUtils;
import utils.readers.ConfigReader;

import static java.lang.Integer.parseInt;

public class SignInPage extends BasePage {

    Integer defaultTimeout = parseInt(ConfigReader.getProperty("defaultTimeout"));

    By emailInput = By.id("email");
    By continueButton = By.xpath("//button[@type=\"submit\"]");

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        type(emailInput, email, defaultTimeout);
        LogUtils.info("Enter email: " +  email);
    }

    public void clickContinueButton() {
        click(continueButton, defaultTimeout);
        LogUtils.info("Click Continue button");
    }
}