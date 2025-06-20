package org.framework.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReportsManager {

    private static ExtentReports extentReport;

    private ExtentReportsManager() {
        //
    }

    public static ExtentReports getIntance() {
        if (extentReport == null) {

            String targetDir = "target/extentReports";

            createFolder(targetDir);
            createInstance(targetDir);

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(targetDir + "/index.html");
            extentReport = new ExtentReports();
            extentReport.attachReporter(sparkReporter);
        }
        return extentReport;
    }

    private static ExtentReports createInstance(String filePath) {
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(filePath);
        extentSparkReporter.config().setReportName("Cosmetica Brasov Automated Test Report");
        extentSparkReporter.config().setDocumentTitle("Extent Report");
        extentReport = new ExtentReports();
        extentReport.attachReporter(extentSparkReporter);
        return extentReport;
    }

    private static void createFolder(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
}
