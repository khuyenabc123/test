package pages;

import framework.constants.FrameWorkConstants;
import framework.core.BasePage;
import org.apache.logging.log4j.*;
import org.openqa.selenium.By;

public class HomePage extends BasePage {
    private static final Logger logger = LogManager.getLogger(HomePage.class);

    private final By imgLogo = By.xpath("//img[@class='logo']");
    private final By inputUsername = By.xpath("//input[@name='username']");
    private final By inputPassword = By.xpath("//input[@name='password']");
    private final By btnLogin = By.xpath("//input[@value='Log In']");
    private final By txtWelcome = By.xpath("//p[@class='smallText']");
    private final By lnkRegister = By.xpath("//a[text()='Register']");

    private By lnkAccountServices(String lnkName) {
        return By.xpath(String.format("//a[@href and text()='%s']", lnkName));
    }

    public void clickImgLogo() {
        click(imgLogo, FrameWorkConstants.DEFAULT_TIMEOUT);
        logger.info("Click page logo");
    }

    public void enterUsername(String username) {
        type(inputUsername, username, FrameWorkConstants.DEFAULT_TIMEOUT);
        logger.info("Enter Username: " + username);
    }

    public void enterPassword(String password) {
        type(inputPassword, password, FrameWorkConstants.DEFAULT_TIMEOUT);
        logger.info("Enter Password: " + password);
    }

    public void clickBtnLogin() {
        click(btnLogin, FrameWorkConstants.DEFAULT_TIMEOUT);
        logger.info("Click Login button");
    }

    public void login(String username, String password) {
        try {
            enterUsername(username);
            enterPassword(password);
            clickBtnLogin();
        } catch (Exception e) {
            logger.info("Login Failed", e.getMessage(), e);
        }
    }

    public String getTxtWelcome() {
        return getText(txtWelcome, FrameWorkConstants.DEFAULT_TIMEOUT);
    }

    public void clickLnkRegister() {
        click(lnkRegister, FrameWorkConstants.DEFAULT_TIMEOUT);
        logger.info("Click Register link");
    }

    public void clickLnkLogOut() {
        click(lnkAccountServices("Log Out"), FrameWorkConstants.DEFAULT_TIMEOUT);
        logger.info("Click Log Out link");
    }
}