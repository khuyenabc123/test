package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    @FindBy(id = "email")
    private WebElement errorMsgText;

    @FindBy(id = "username")
    private WebElement userName;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "passwordConfirm")
    private WebElement passwordConfirm;

    @FindBy(xpath = "//label[@for=\"remember\"]")
    private WebElement rememberChkBox;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String email, String password) throws Exception {
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(password);
        enterUserName("test556");
        clickLogIn();
    }

    public boolean verifyLogInWithoutRememberMe() {
        enterEmail("test");
        enterPassword("pass");
        clickLogIn();

        return getErrorMessage().contains("incorrect");
    }

    public void enterEmail(String email) {
        if (emailInput.isDisplayed())
            emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        if (passwordInput.isDisplayed())
            passwordInput.sendKeys(password);
    }

    public void enterConfirmPassword(String password) {
        if (passwordConfirm.isDisplayed())
            passwordConfirm.sendKeys(password);
    }

    public void enterUserName(String password) {
        if (userName.isDisplayed())
            userName.sendKeys(password);
    }

    public void checkedRememberChkBox(String password) {
        if (rememberChkBox.isDisplayed())
            rememberChkBox.click();
    }

    public void clickLogIn() {
        if (loginButton.isDisplayed()) {
            loginButton.click();
        }
    }

    public String getErrorMessage() {
        String strErrorMsg = null;

        if (errorMsgText.isDisplayed() && errorMsgText.isEnabled())
            strErrorMsg = errorMsgText.getText();
        return strErrorMsg;
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
                        .equals("complete");
            }
        };

        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
}