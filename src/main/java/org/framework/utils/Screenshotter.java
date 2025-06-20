package org.framework.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.framework.driver.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Screenshotter implements ITestListener {

    private static final Logger log = LogManager.getLogger(Screenshotter.class);

    @Override
    public void onTestFailure(ITestResult result) {

        WebDriver driver = DriverFactory.getInstance().getDriver();
        Path targetDir = Paths.get("target", "failureScreenshots");
        try {
            Files.createDirectories(targetDir); // ensures directory exists
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (driver == null) {
            log.error("Driver was null. Probably failed to initialize and the first test failed as a result. Unable to take screenshot.");
            return;
        }

        if (result.getStatus() == ITestResult.FAILURE) {
            String baseFileName = result.getMethod().getMethodName()
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("-yyMMdd-HHmmss"));

            File targetFile = targetDir.resolve(baseFileName + ".png").toFile();
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            try {
                Files.copy(scrFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                log.error("There was a problem writing screenshot to disk (path? rights?)");
            }
            targetFile.setReadable(true, false);
        }
    }
}