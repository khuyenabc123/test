package testcases;

import base.BaseTest;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import utils.TestDataFile;
import utils.TestDataProvider;

@Listeners(TestListener.class)
public class SignInTest extends BaseTest {
    private HomePage home;
    private SignUpPage signUp;
    private SignInOptionsPage options;
    private SignInPage signIn;
    private OTPPage otpPage;
    private YopmailPage yopmail;

    @BeforeMethod
    public void setUpPages() {
        WebDriver driver = getDriverInstance();

        home = new HomePage(driver);
        signUp = new SignUpPage(driver);
        options = new SignInOptionsPage(driver);
        signIn = new SignInPage(driver);
        otpPage = new OTPPage(driver);
        yopmail = new YopmailPage(driver);
    }

    @Test(dataProvider = "testData", dataProviderClass = TestDataProvider.class)
    @TestDataFile(file = "${loginDataFile}", sheet = "SignIn")
    public void testSignInWithValidEmail(String email) {
        home.clickSignInButton();
        signUp.clickSignInLink();
        options.clickContinueWithEmail();

        signIn.enterEmail(email);
        signIn.clickContinueButton();

        yopmail.openInbox(email);
        String otp = yopmail.getLatestOtp();

        driver.navigate().back();
        otpPage.enterOtp(otp);
        otpPage.clickSignInButton();

        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"),
                "User should land on dashboard after login");
    }
}
