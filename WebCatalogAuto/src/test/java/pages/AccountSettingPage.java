package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.LogUtils;

public class AccountSettingPage extends BasePage {
    private By lnkAccountTab(String tabName) {
        return By.xpath(String.format("//a[contains(@href,'account/'%s'')]]", tabName));
    }

    private final By inputName = By.xpath("//input[@name=\"name\"]");
    private final By selectCountry = By.xpath("//div[@data-slot=\"form-item\"]//button");
    private final By txtSelectedCountry = By.xpath("//div[@data-slot=\"form-item\"]//button/span");
    private final By optionNotSelected = By.xpath("//select/option[not(@selected)]");
    private final By inputEmail = By.xpath("//input[@name=\"email\"]");
    private final By btnUpdate = By.xpath("//button[@type=\"submit\"]");
    private final By textSuccessMessage = By.xpath("//li[@tabindex=\"0\"]//div[@data-title]");
    private final By btnDeleteAccount = By.xpath("//div[@data-slot=\"card\"]//button");

    public void clickTabProfile() {
        LogUtils.info("Click Profile Tab");
        click(lnkAccountTab("profile"), 10);
    }

    public void clickTabEmail() {
        LogUtils.info("Click Email address Tab");
        click(lnkAccountTab("email"), 10);
    }

    public void clickTabTeam() {
        LogUtils.info("Click Team Tab");
        click(lnkAccountTab("team"), 10);
    }

    public void clickTabBilling() {
        LogUtils.info("Click Billing Tab");
        click(lnkAccountTab("billing"), 10);
    }

    public void clickTabAccount() {
        LogUtils.info("Click Account Tab");
        click(lnkAccountTab("account"), 10);
    }

    public void enterName(String name) {
        LogUtils.info("Enter Name: " + name);
        type(inputName, name, 10);
    }

    public void selectCountry() {
        LogUtils.info("Open Country dropdown");
        click(selectCountry, 10);
    }

    public String getTextSelectedCountry() {
        LogUtils.info("Get Selected Country text");
        return getText(txtSelectedCountry, 10);
    }

    public void clickOptionNotSelected() {
        LogUtils.info("Click a Not Selected Option");
        click(optionNotSelected, 10);
    }

    public void enterEmail(String email) {
        LogUtils.info("Enter Email address: " + email);
        type(inputEmail, email, 10);
    }

    public void clickBtnUpdate() {
        LogUtils.info("Click Update button");
        click(btnUpdate, 10);
    }

    public String getTextSuccessMessage() {
        LogUtils.info("Get Success message text");
        return getText(textSuccessMessage, 10);
    }

    public void clickBtnDeleteAccount() {
        LogUtils.info("Click Delete Account button");
        click(btnDeleteAccount, 10);
    }

    public boolean isBtnUpdateEnabled() {
        LogUtils.info("Check button update is enabled");

        return isEnabled(btnUpdate, 10);
    }
}
