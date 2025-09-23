package utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.readers.ConfigReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CaptureHelpers {

    public static String captureScreenShot(WebDriver driver) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String timestamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
        String destinationPath = ConfigReader.getProperty("screenshotPath") + "screenshot_" + timestamp + ".png";

        File destinationFile = new File(destinationPath);

        try {
            Files.createDirectories(destinationFile.getParentFile().toPath());
            Files.copy(srcFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return destinationPath;
    }

    public static void logWithScreenShot(WebDriver driver, Status status, String message) {
        String screenshotPath = captureScreenShot(driver);

        try {
            ExtentManager.getTest().log(status, message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            ExtentManager.getTest().log(Status.WARNING, "Could not attach screenshot: " + e.getMessage());
        }
    }
}
