package com.tests;


import com.mailjet.client.errors.MailjetException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.framework.DriverFactory;
import org.framework.EmailSender;
import org.framework.Screenshotter;
import org.framework.TestUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.lang.reflect.Method;

@Listeners({Screenshotter.class})

public class BaseTestClass {
    private static final Logger log = LogManager.getLogger(BaseTestClass.class);
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
        try {
            EmailSender emailSender = new EmailSender();
            emailSender.sendEmail();
        } catch (Exception e) {
            log.error("Unable to send report email.");
            System.err.println("Failed to send report Email " + e.getMessage());
        } finally {
            DriverFactory.getInstance(browser).quitBrowser();
        }
    }
}