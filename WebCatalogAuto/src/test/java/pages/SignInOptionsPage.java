package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInOptionsPage extends BasePage {
    private final By signInWithEmailOption = By.xpath("//a[text()=\"Continue with Email\"]");

    public SignInOptionsPage(WebDriver driver) {
        super(driver);
    }

    public void clickContinueWithEmail() {
        click(signInWithEmailOption, 10);
    }
}
