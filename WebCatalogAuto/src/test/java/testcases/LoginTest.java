package testcases;

import base.BaseSetup;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.LogUtils;
import utils.readers.ExcelReader;

@Listeners(TestListener.class)
public class LoginTest extends BaseSetup {
    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);

    private WebDriver driver;

    public LoginPage loginPage;

    public HomePage homePage;

    public ExcelReader excel = new ExcelReader();

    @BeforeClass
    public void setUp() {
        driver = getDriver();
    }

//    @Test(priority = 1)
//    public void redirectToLoginPage() throws Exception {
//        LogUtils.info("Redirecting to login page ...");
//
//        System.out.println(driver);
//
//        homePage = new HomePage(driver);
//
//        loginPage = homePage.redirectToLoginPage();
//    }

    @Test(priority = 2)
    public void login() throws Exception {
        LogUtils.info("Running login test ...");

        System.out.println(driver);

        loginPage = new LoginPage(driver);

        excel.setExcelFile("src/test/resources/data.xlsx", "Login");

        String email = excel.getCellData(1, 0);
        String password = excel.getCellData(1, 1);

//        loginPage.login("test5566@gmail.com", "Test123@");
    }
}
