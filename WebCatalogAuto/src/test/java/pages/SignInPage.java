package pages;

import base.BasePage;
import org.openqa.selenium.By;
import utils.LogUtils;
import utils.readers.ConfigReader;

public class SignInPage extends BasePage {

    int defaultTimeout = Integer.parseInt(ConfigReader.getProperty("defaultTimeout"));

    By signInWithEmailOption = By.xpath("//a[contains(@href, 'email')]");
    By emailInput = By.id("email");
    By continueButton = By.xpath("//button[@type='submit']");
    By otpInput = By.id("code");
    By signInButton = By.xpath("//button[@type='submit']");

    public void clickContinueWithEmail() {
        click(signInWithEmailOption, defaultTimeout);
        LogUtils.info("Click continue with Email");
    }

    public void enterEmail(String email) {
        type(emailInput, email, defaultTimeout);
        LogUtils.info("Enter email: " + email);
    }

    public void clickContinueButton() {
        click(continueButton, defaultTimeout);
        LogUtils.info("Click Continue button");
    }

    public void enterOtp(String otp) {
        type(otpInput, otp, defaultTimeout);
        LogUtils.info("Enter otp: " + otp);
    }

    public void clickSignInButton() {
        click(signInButton, defaultTimeout);
        LogUtils.info("Click on sign in button");
    }

    public boolean isOTPInputDisplayed() {
        LogUtils.info("Check otp input is displayed");
        return isDisplayed(otpInput, 10);
    }
}