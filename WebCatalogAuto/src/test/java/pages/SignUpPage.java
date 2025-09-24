package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage extends BasePage {
    By signInLink = By.xpath("//a[text()='Sign in']");

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public void clickSignInLink() {
        click(signInLink, 10);
    }
}
