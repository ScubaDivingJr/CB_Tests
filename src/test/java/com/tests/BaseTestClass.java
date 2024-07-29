package com.tests;

import org.checkerframework.checker.units.qual.C;
import org.framework.DriverFactory;
import org.framework.Screenshotter;
import org.framework.TestUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


import static org.framework.DriverFactory.getChromeDriver;
import static org.framework.DriverFactory.getGetDriverWait;

@ExtendWith(Screenshotter.class)
public class BaseTestClass {
    static WebDriver driver;
    static WebDriverWait wait;
    public static final String BASE_URL = "https://cosmeticabrasov.ro";
    @BeforeSuite
    public void startBrowser() {
        driver = getChromeDriver();
        wait = getGetDriverWait();
        driver.get(BASE_URL);
    }
    @BeforeAll
    public static void goToHome() {
        driver = getChromeDriver();
        driver.get(BASE_URL);
    }
    @AfterSuite
    public static void cleanUp(){
     driver.quit();
    }
}