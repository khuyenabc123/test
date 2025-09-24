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

import java.time.Duration;

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
        // Step 1: Go to sign-in
        home.clickBtnLSignIn();
        signUp.clickSignInLink();
        options.clickContinueWithEmail();

        signIn.enterEmail(email);
        signIn.clickContinueButton();

        // Step 2: Save the app window handle
        String appWindow = driver.getWindowHandle();

        // Step 3: Open Yopmail in a new tab
        yopmail.openYopmailTab(appWindow);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Step 4: Get OTP from Yopmail
        String otp = yopmail.getOTPFromMail(email, appWindow);

        // Step 5: Enter OTP and verify login
        otpPage.enterOtp(otp);
        otpPage.clickSignInButton();

        Assert.assertTrue(home.isAvatarMenuDisplayed(),
                "User should land on dashboard after login");
    }
}
