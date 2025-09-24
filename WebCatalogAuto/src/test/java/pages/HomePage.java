package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private final By homeSignInButton = By.xpath("//button[text()='Sign in']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickSignInButton() {
        click(homeSignInButton, 10);
    }
}