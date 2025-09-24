package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OTPPage extends BasePage {
    private final By otpInput = By.xpath("//input[@id=\"code\"]");
    private final By signInButton = By.xpath("//button[@type=\"submit\"]");

    public OTPPage(WebDriver driver) {
        super(driver);
    }

    public void enterOtp(String otp) {
        type(otpInput, otp, 10);
    }

    public void clickSignInButton() {
        click(signInButton, 10);
    }
}
