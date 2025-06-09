package org.framework;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;

import java.util.ArrayList;
import java.util.List;


public class TestLogger implements ITestListener, IInvokedMethodListener  {

    private static final Logger log = LogManager.getLogger(TestLogger.class);

    public static List<String> testResults = new ArrayList<>();

    @Override
    public void onTestSuccess(ITestResult result) {
       log.info(result.getMethod().getQualifiedName() + " - PASSED.");
       testResults.add(result.getMethod().getQualifiedName() + " - PASSED.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info(result.getMethod().getQualifiedName() + " - FAILED.");
        testResults.add(result.getMethod().getQualifiedName() + " - FAILED.");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info(result.getMethod().getQualifiedName() + " - SKIPPED.");
        testResults.add(result.getMethod().getQualifiedName() + " - SKIPPED.");
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
    }
}
