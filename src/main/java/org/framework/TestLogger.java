package org.framework;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;
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
       log.info(result.getMethod().getQualifiedName() + " - PASSED.");
       testResults.add(transformTimeStamp(result.getStartMillis()) + " - " + result.getMethod().getQualifiedName() + " - PASSED.");
       testResultAgg.put("PASSED", testResultAgg.get("PASSED") + 1);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info(result.getMethod().getQualifiedName() + " - FAILED.");
        testResults.add(transformTimeStamp(result.getStartMillis()) + " - " + result.getMethod().getQualifiedName() + " - FAILED.");
        testResultAgg.put("FAILED", testResultAgg.get("FAILED") + 1);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info(result.getMethod().getQualifiedName() + " - SKIPPED.");
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
        log.info(testResults);
        log.info(testResultAgg);
        writeLogFile(testResults, testResultAgg);
    }

    private String transformTimeStamp(long millis) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        return formatter.format(Instant.ofEpochMilli(millis));
    }

    private void writeLogFile(List<String> testResults, Map<String, Integer> testResultAgg) {

        //combine results
        List<String> lines = new ArrayList<>(testResults);
        testResultAgg.forEach((k, v) -> lines.add(k + ": " + v));

        //write
        try {
            java.nio.file.Files.write(
                    java.nio.file.Paths.get("target\\test-results.txt"),
                    lines,
                    java.nio.file.StandardOpenOption.CREATE,
                    java.nio.file.StandardOpenOption.TRUNCATE_EXISTING
            );
        } catch (java.io.IOException e) {
            log.error("Failed to write test results file", e);
        }
    }
}
