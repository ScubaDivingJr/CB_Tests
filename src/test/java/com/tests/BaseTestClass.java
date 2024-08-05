package com.tests;

import org.framework.Screenshotter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.annotations.BeforeClass;

import static org.framework.DriverFactory.getChromeDriver;

@Listeners({Screenshotter.class})

public class BaseTestClass {
    WebDriver driver = getChromeDriver();

    public static final String BASE_URL = "https://cosmeticabrasov.ro";
    @BeforeClass
    public void startBrowser() {
       driver.get(BASE_URL);
    }
    @BeforeMethod
    public void setUp() {
        driver.get(BASE_URL);
    }
    @AfterSuite
    public void cleanUp(){
     driver.quit();
    }
}