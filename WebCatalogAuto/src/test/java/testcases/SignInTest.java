package testcases;

import base.BaseSetup;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.SignInPage;
import utils.LogUtils;
import utils.readers.ExcelReader;

@Listeners(TestListener.class)
public class SignInTest extends BaseSetup {

    private WebDriver driver;

    public SignInPage signinPage;

    public ExcelReader excel = new ExcelReader();

    @BeforeClass
    public void setUp() {
        driver = getDriver();
    }

    @Test
    public void signIn() throws Exception {
        LogUtils.info("Running login test ...");

        System.out.println(driver);

        signinPage = new SignInPage(driver);

        excel.setExcelFile("src/test/resources/data.xlsx", "SignIn");

        String email = excel.getCellData(1, 0);

        signinPage.signIn(email);
    }
}
