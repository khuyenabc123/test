package testcases;

import base.BaseTest;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.SignInPage;
import utils.LogUtils;
import utils.TestDataFile;
import utils.TestDataProvider;

@Listeners(TestListener.class)
public class SignInTest extends BaseTest {

    public SignInPage signinPage;

    @Test(dataProvider = "testData", dataProviderClass = TestDataProvider.class)
    @TestDataFile(file = "${loginDataFile}", sheet = "SignIn")
    public void signIn(String email) throws Exception {
        LogUtils.info("Running login test ...");

        driver = getDriverInstance();
        signinPage = new SignInPage(driver);

        signinPage.signIn(email);
    }
}
