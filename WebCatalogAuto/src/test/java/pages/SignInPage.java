package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.LogUtils;
import utils.readers.ConfigReader;

import java.time.Duration;

import static java.lang.Integer.parseInt;

public class SignInPage extends BasePage {
    private final WebDriver driver;

    Integer defaultTimeout = parseInt(ConfigReader.getProperty("defaultTimeout"));

    By homeSignInButton = By.xpath("//button[text()=\"Sign in\"]");
    By signInLink = By.xpath("//a[text()=\"Sign in\"]");
    By signInWithEmailOption = By.xpath("//a[text()=\"Continue with Email\"]");
    By emailInput = By.id("email");
    By continueButton = By.xpath("button[@type=\"submit\"]");
    By otpInput = By.xpath("input[@id=\"code\"]");
    By signInButton = By.xpath("button[@type=\"submit\"]");

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void signIn(String email) {
        try {
            redirectToSignInPageFromHomePage();
            redirectToSignInPageOptions();
            redirectToSignInPage();
            enterEmail(email);
            clickContinueButton();
        } catch (Exception e) {
            LogUtils.error("Sign in failed: " + e.getMessage());
        }
    }

    public void redirectToSignInPageFromHomePage() {
        click(homeSignInButton, defaultTimeout);
        LogUtils.info("Click on Sign in button in Home page");

    }

    public void redirectToSignInPageOptions() {
        click(signInLink, defaultTimeout);
        LogUtils.info("Click on Sign in link in Sign up page");

    }

    public void redirectToSignInPage() {
        click(signInWithEmailOption, defaultTimeout);
        LogUtils.info("Click on Sign in with email option");

    }

    public void enterEmail(String email) {
        type(emailInput, email, defaultTimeout);
        LogUtils.info("Enter email: " +  email);
    }

    public void clickContinueButton() {
        click(continueButton, defaultTimeout);
        LogUtils.info("Click Continue button");
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