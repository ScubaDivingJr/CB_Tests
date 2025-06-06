package org.framework;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.pages.BasePage;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.framework.*;

public class Screenshotter extends BasePage implements ITestListener {

    WebDriver driver = DriverFactory.getInstance("chrome").getDriver();
    @Override
    public void onTestFailure(ITestResult result) {
        if (result.getStatus() == 2) { // if the test execution has failed
            String baseFileName = result.getTestName() + "-"
                    + result.getTestClass().getName()
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("-yyMMdd-HHmmss"));

            File targetFile = new File("C:\\TestScreenshots\\" + baseFileName + ".png");

            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                Files.copy(scrFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            targetFile.setReadable(true, false);
        }
    }
}