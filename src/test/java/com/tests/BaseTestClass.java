package com.tests;


import org.framework.Screenshotter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
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