package org.framework;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static org.framework.DriverFactory.getChromeDriver;

public class Screenshotter implements AfterTestExecutionCallback {

    static WebDriver driver = getChromeDriver();

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (context.getExecutionException().isPresent()) { // if the test execution has failed
            String baseFileName = context.getRequiredTestClass().getSimpleName() + "-"
                    + context.getRequiredTestMethod().getName()
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("-yyMMdd-HHmmss"));
            File targetFile = new File("C:\\TestScreenshots\\" + baseFileName + ".png");
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(scrFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            targetFile.setReadable(true, false);
        }
    }
}