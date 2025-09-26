package testcases;

import base.BaseTest;
import listeners.TestListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import utils.AssertUtils;
import utils.TestDataFile;
import utils.TestDataProvider;

@Listeners(TestListener.class)
public class SignInTest extends BaseTest {
    private HomePage home;
    private SignUpPage signUp;
    private SignInPage signIn;
    private YopmailPage yopmail;

    @BeforeMethod
    public void setUpPages() {
        home = new HomePage();
        signUp = new SignUpPage();
        signIn = new SignInPage();
        yopmail = new YopmailPage();
    }

    @Test(dataProvider = "testData", dataProviderClass = TestDataProvider.class)
    @TestDataFile(file = "${loginDataFile}", sheet = "SignIn")
    public void testSignInWithValidEmail(String email) {
        // Step 1: Go to sign-in
        home.clickBtnLSignIn();
        signUp.clickSignInLink();
        signIn.clickContinueWithEmail();

        signIn.enterEmail(email);
        signIn.clickContinueButton();

        // Step 2: Save the app window handle
        String appWindow = base.DriverFactory.getDriver().getWindowHandle();

        // Step 3: Open Yopmail in a new tab and get its handle
        String yopmailWindow = yopmail.openYopmailTab(appWindow);

        // Step 4: Get OTP from Yopmail
        String otp = yopmail.getOTPFromMail(email, yopmailWindow);

        // Step 5: Close Yopmail tab and switch back to app window
        yopmail.closeYopmailTab(yopmailWindow, appWindow);

        // Step 6: Enter OTP in the app
        signIn.enterOtp(otp);
        signIn.clickSignInButton();

        AssertUtils.assertTrue(home.isAvatarMenuDisplayed(),
                "User should land on dashboard after login");
    }

    @Test
    public void submitEmptyEmail() {
        home.clickBtnLSignIn();
        signUp.clickSignInLink();
        signIn.clickContinueWithEmail();

        signIn.enterEmail("");
        signIn.clickContinueButton();

        boolean test = signIn.isOTPInputDisplayed();

        AssertUtils.assertFalse(test,
                "OTP input should not be displayed");
    }
}