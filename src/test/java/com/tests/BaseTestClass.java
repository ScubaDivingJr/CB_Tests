package com.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.framework.DriverFactory;
import org.framework.Screenshotter;
import org.framework.TestLogger;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.annotations.BeforeClass;

@Listeners({Screenshotter.class, TestLogger.class})

public abstract class BaseTestClass {
    private static final Logger log = LogManager.getLogger(BaseTestClass.class);
    protected static final String browser = "chrome";
    protected static final String base_url = "https://cosmeticabrasov.ro";

    @BeforeSuite
    public void logStart() {
        log.info("Starting test suite...");
    }

    @BeforeClass
    public void beforeClassSetup() {
        //implement in each test class
    }

    @BeforeMethod()
    public void beforeMethodSetup() {
        //implement in each test class
    }

    @AfterSuite
    public void cleanUp() {
        DriverFactory.getInstance(browser).quitBrowser();
    }
}