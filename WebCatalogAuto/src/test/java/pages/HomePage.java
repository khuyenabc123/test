package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.LogUtils;

public class HomePage extends BasePage {
    private final By imgLogo = By.xpath("//a[@aria-label=\"Home\"]");
    private final By btnLSignIn = By.xpath("//button[contains(@id,\"radix\")]//following-sibling::div/button[not(./a)]");
    private final By btnSubmitNewApp = By.xpath("//a[contains(@href,\"submit\")]");
    private final By btnAvatarMenu = By.xpath("//span[@data-slot=\"avatar\"]//ancestor::button");
    private final By txtAccountName = By.xpath("//div[@data-slot=\"dropdown-menu-content\"]//p[2]");
    private final By btnAccountSettings = By.xpath("//a[@data-slot=\"dropdown-menu-item\"]");
    private final By btnLogout = By.xpath("//button[@data-slot=\"dropdown-menu-item\"]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickImgLogo() {
        click(imgLogo, 10);
        LogUtils.info("Click home page logo");
    }

    public void clickBtnLSignIn() {
        click(btnLSignIn, 10);
        LogUtils.info("Click Sign in button");
    }

    public void clickBtnSubmitNewApp() {
        click(btnSubmitNewApp, 10);
        LogUtils.info("Click Submit New App button");
    }

    public void clickBtnAvatarMenu() {
        click(btnAvatarMenu, 10);
        LogUtils.info("Click Avatar menu button");
    }

    public String getTextAccountName() {
        LogUtils.info("Get Text Account Name");

        return getText(txtAccountName, 10);
    }

    public void clickBtnAccountSettings() {
        click(btnAccountSettings, 10);
        LogUtils.info("Click Account Settings button");
    }

    public void clickBtnLogout() {
        click(btnLogout, 10);
        LogUtils.info("Click Log out button");
    }

    public boolean isAvatarMenuDisplayed () {
        LogUtils.info("Check avatar is displayed");

        return isDisplayed(btnAvatarMenu, 10);
    }
}