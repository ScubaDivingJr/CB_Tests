package com.tests;

import org.enums.Menus;
import org.framework.DriverFactory;
import org.framework.Screenshotter;
import org.framework.TestUtils;
import org.openqa.selenium.WebDriver;
import org.pages.Header;
import org.pages.ServicesPage;
import org.testng.annotations.*;
import org.testng.annotations.BeforeClass;

import java.lang.reflect.Method;

@Listeners({Screenshotter.class})

public class BaseTestClass {
    WebDriver driver = DriverFactory.getInstance("chrome").getDriver();
    protected TestUtils testUtils;

    public static final String BASE_URL = "https://cosmeticabrasov.ro";
    @BeforeClass
    public void startBrowser() {
       driver.get(BASE_URL);
    }

    private static boolean treatmentTestsFirstRun = true;
    @BeforeMethod
    public void setUp(Method method) {
        testUtils = new TestUtils();
        if ("treatmentTests".equals(method.getName())) {
            if (treatmentTestsFirstRun) {
                Header header = new Header();
                header.clickNavBarMenuItem(Menus.SERVICII);
                treatmentTestsFirstRun = false;
            }
            return;
        }
        driver.get(BASE_URL);
    }
    @AfterSuite
    public void cleanUp(){
     //driver.quit();
    }
}