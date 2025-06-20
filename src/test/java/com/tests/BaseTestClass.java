package com.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.enums.BrowserTypes;
import org.framework.driver.ConfigManager;
import org.framework.driver.DriverFactory;
import org.framework.utils.Screenshotter;
import org.framework.utils.TestLogger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Listeners({Screenshotter.class, TestLogger.class})

public abstract class BaseTestClass {

    protected static final BrowserTypes browser = ConfigManager.getBrowser();
    protected boolean headless = ConfigManager.isHeadless();
    protected boolean mobile = ConfigManager.isMobile();
    protected static final String base_url = "https://cosmeticabrasov.ro";
    protected WebDriver driver;

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
        DriverFactory.getInstance().initializeDriver(browser, headless);
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
        // we probably don't need this but safer I guess.
        DriverFactory.getInstance().quitBrowser();
    }
}