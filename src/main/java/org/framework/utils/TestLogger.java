package org.framework.utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.framework.driver.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestLogger implements ITestListener, IInvokedMethodListener  {

    private static final Logger log = LogManager.getLogger(TestLogger.class);

    private static ExtentReports extentReports = ExtentReportsManager.getIntance();
    private static ThreadLocal<ExtentTest> tlExtentTest = new ThreadLocal<>();

    List<String> testResults = new ArrayList<>();
    Map<String, Integer> testResultAgg = new HashMap<>();

    @Override
    public void onStart(ITestContext context) {
        testResultAgg.put("PASSED", 0);
        testResultAgg.put("FAILED", 0);
        testResultAgg.put("SKIPPED", 0);
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extentReports.createTest(result.getMethod().getMethodName());
        tlExtentTest.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
       log.info("{} - PASSED.", result.getMethod().getQualifiedName());
       testResults.add(transformTimeStamp(result.getStartMillis()) + " - " + result.getMethod().getQualifiedName() + " - PASSED.");
       testResultAgg.put("PASSED", testResultAgg.get("PASSED") + 1);
       tlExtentTest.get().pass("Test passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("{} - FAILED.", result.getMethod().getQualifiedName());
        testResults.add(transformTimeStamp(result.getStartMillis()) + " - " + result.getMethod().getQualifiedName() + " - FAILED.");
        testResultAgg.put("FAILED", testResultAgg.get("FAILED") + 1);
        tlExtentTest.get().fail("Test failed.");

        WebDriver driver = DriverFactory.getInstance().getDriver();

        String screenshotPath = takeScreenshot(result.getMethod().getMethodName(), driver);

        if (screenshotPath != null) {
            log.info(screenshotPath);
            tlExtentTest.get().addScreenCaptureFromPath(screenshotPath);
        }
        tlExtentTest.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("{} - SKIPPED.", result.getMethod().getQualifiedName());
        testResults.add(transformTimeStamp(result.getStartMillis()) + " - " + result.getMethod().getQualifiedName() + " - SKIPPED.");
        testResultAgg.put("SKIPPED", testResultAgg.get("SKIPPED") + 1);
        tlExtentTest.get().skip("Test skipped.");
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            log.info("Executing test '{}' ", method.getTestMethod().getQualifiedName());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("------------------------------------------------------------------");
        log.info("SUMMARY: {}", testResultAgg);
        log.info("------------------------------------------------------------------");
        log.info("Individual results: {}", testResults);
        extentReports.flush();
    }

    private String transformTimeStamp(long millis) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        return formatter.format(Instant.ofEpochMilli(millis));
    }

    private String calculateDuration(ITestResult result) {
        long durationInMillis = result.getEndMillis() - result.getStartMillis();
        Duration duration = Duration.ofMillis(durationInMillis);
        long minutes = duration.toMinutes();
        long seconds = duration.minusMinutes(minutes).getSeconds();
        return String.format("%02d:%02d", minutes, seconds);
    }

    private static String takeScreenshot(String methodName, WebDriver driver) {
        Path targetDir = Paths.get("target", "extentReports", "failureScreenshots");

        try {
            Files.createDirectories(targetDir);
        } catch (IOException e) {
            log.error("Unable to create failureScreenshots directory.");
        }

        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("-yyMMdd-HHmmss"));
        String fileName = methodName + timeStamp + ".png";
        Path filePath = targetDir.resolve(fileName);

        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            Files.copy(file.toPath(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return "failureScreenshots/" + fileName; // extentreports wants related path inside the target dir
        } catch (IOException e) {
            log.error("Unable to save screenshot to directory '{}'", targetDir);
            return null;
        }
    }
}
