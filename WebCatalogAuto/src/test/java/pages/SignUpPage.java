package pages;

import base.BasePage;
import org.openqa.selenium.By;

import utils.LogUtils;

public class SignUpPage extends BasePage {
    private final By inputEmail = By.id("email");
    private final By btnContinue = By.xpath("//button[@data-slot=\"button\"]");
    private final By lnkJoinForFree = By.xpath("//a[contains(@href, \"signup/email?redirectUrl\")]");
    private final By inputOTP = By.id("code");
    private final By btnSignIn = By.xpath("//button[@data-slot=\"button\"]");
    private final By signInLink = By.xpath("//a[contains(@href, \"login\") and not(contains(@href, \"login-with\"))]");
    private final By signUnWithEmailOption = By.xpath("//a[contains(@href, \"email\")]");

    public void enterEmail(String email) {
        LogUtils.info("Enter Email address: " + email);
        type(inputEmail, email, 10);
    }

    public void clickBtnContinue() {
        LogUtils.info("Click Continue button");
        click(btnContinue, 10);
    }

    public void clickLnkJoinForFree() {
        LogUtils.info("Click Join for free link");
        click(lnkJoinForFree, 10);
    }

    public void enterOTP(String otp) {
        LogUtils.info("Enter OTP: " + otp);
        type(inputOTP, otp, 10);
    }

    public void clickBtnSignIn() {
        LogUtils.info("Click Sign in button");
        click(btnSignIn, 10);
    }

    public void clickSignInLink() {
        click(signInLink, 10);
    }
}