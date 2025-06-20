package org.framework.utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestLogger implements ITestListener, IInvokedMethodListener  {

    private static final Logger log = LogManager.getLogger(TestLogger.class);

    List<String> testResults = new ArrayList<>();
    Map<String, Integer> testResultAgg = new HashMap<>();

    @Override
    public void onStart(ITestContext context) {
        testResultAgg.put("PASSED", 0);
        testResultAgg.put("FAILED", 0);
        testResultAgg.put("SKIPPED", 0);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
       log.info("{} - PASSED.", result.getMethod().getQualifiedName());
       testResults.add(transformTimeStamp(result.getStartMillis()) + " - " + result.getMethod().getQualifiedName() + " - PASSED.");
       testResultAgg.put("PASSED", testResultAgg.get("PASSED") + 1);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("{} - FAILED.", result.getMethod().getQualifiedName());
        testResults.add(transformTimeStamp(result.getStartMillis()) + " - " + result.getMethod().getQualifiedName() + " - FAILED.");
        testResultAgg.put("FAILED", testResultAgg.get("FAILED") + 1);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("{} - SKIPPED.", result.getMethod().getQualifiedName());
        testResults.add(transformTimeStamp(result.getStartMillis()) + " - " + result.getMethod().getQualifiedName() + " - SKIPPED.");
        testResultAgg.put("SKIPPED", testResultAgg.get("SKIPPED") + 1);
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
}
