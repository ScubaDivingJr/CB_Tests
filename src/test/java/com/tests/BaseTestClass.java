package com.tests;


import org.framework.DriverFactory;
import org.framework.Screenshotter;
import org.framework.TestUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.annotations.BeforeClass;

import java.lang.reflect.Method;

@Listeners({Screenshotter.class})

public class BaseTestClass {

    private static final String browser = "chrome";
    public static final String base_url = "https://cosmeticabrasov.ro";

    WebDriver driver = DriverFactory.getInstance(browser).getDriver();
    protected TestUtils testUtils;

    @BeforeClass
    public void startBrowser() {
        testUtils = new TestUtils();
    }

    @BeforeMethod
    public void setUp(Method method) {
        driver.get(base_url);
    }

    @AfterSuite
    public void cleanUp() {
        DriverFactory.getInstance(browser).quitBrowser();
    }
}