package pages;

import base.BasePage;
import utils.LogUtils;
import utils.readers.ConfigReader;
import org.openqa.selenium.By;
import static java.lang.Integer.parseInt;

public class HomePage extends BasePage {
    private final By imgLogo = By.xpath("//img[@class='logo']");
    private final By inputUsername = By.xpath("//input[@name='username']");
    private final By inputPassword = By.xpath("//input[@name='password']");
    private final By btnLogin = By.xpath("//input[@value='Log In']");
    private final By txtWelcome = By.xpath("//p[@class='smallText']");
    private final By lnkRegister = By.xpath("//a[text()='Register']");

    Integer time = parseInt(ConfigReader.getProperty("defaultTimeout"));

    private By lnkAccountServices(String lnkName) {
        return By.xpath(String.format("//a[@href and text()='%s']", lnkName));
    }

    public void clickImgLogo() {
        click(imgLogo, time);
        LogUtils.info("Click page logo");
    }

    public void enterUsername(String username) {
        type(inputUsername, username, time);
        LogUtils.info("Enter Username: " + username);
    }

    public void enterPassword(String password) {
        type(inputPassword, password, time);
        LogUtils.info("Enter Password: " + password);
    }

    public void clickBtnLogin() {
        click(btnLogin, time);
        LogUtils.info("Click Login button");
    }

    public void login(String username, String password) {
        try {
            enterUsername(username);
            enterPassword(password);
            clickBtnLogin();
        } catch (Exception e) {
            LogUtils.info("Login Failed");
        }
    }

    public String getTxtWelcome() {
        return getText(txtWelcome, time);
    }

    public void clickLnkRegister() {
        click(lnkRegister, time);
        LogUtils.info("Click Register link");
    }

    public void clickLnkLogOut() {
        click(lnkAccountServices("Log Out"), time);
        LogUtils.info("Click Log Out link");
    }
}