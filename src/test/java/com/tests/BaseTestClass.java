package com.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.framework.DriverFactory;
import org.framework.Screenshotter;
import org.framework.TestLogger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Listeners({Screenshotter.class, TestLogger.class})

public abstract class BaseTestClass {

    protected WebDriver driver;

    protected static final String browser = "chrome";
    protected static final String base_url = "https://cosmeticabrasov.ro";

    private static final Logger log = LogManager.getLogger(BaseTestClass.class);


    @BeforeSuite
    protected void logStart() {
        try {
            Files.createDirectories(Paths.get("target", "logs"));
        } catch (IOException e) {
            log.error("Unable to create log file. Attempting test run regardless.");
        }
        log.info("Starting test suite...");
    }

    @BeforeClass
    public final void beforeClassSetup() {
        // ensures driver instance per class (!), if we decide on parallelizing tests per method, we need to do move this in @BeforeMethod.
        DriverFactory.getInstance().initializeDriver(browser);
        driver = DriverFactory.getInstance().getDriver();

        individualClassSetup();
    }

    // implement this in all child test classes (navigate to page, whatever).
    protected abstract void individualClassSetup();

    @BeforeMethod()
    protected void beforeMethodSetup() {
        // implement in each test class
    }

    @AfterClass (alwaysRun = true)
    protected final void cleanUpAfterEachClass() {
        // this is set to run after each class because we're grouping our threads by class (not methods), so we're ok wrt multi-threading.
        DriverFactory.getInstance().quitBrowser();
    }

    @AfterSuite
    protected void cleanUp() {
        // not sure if we need this actually but safer I guess.
        DriverFactory.getInstance().quitBrowser();
    }
}