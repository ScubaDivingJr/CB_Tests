package com.tests;

import com.mailjet.client.errors.MailjetException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.framework.DriverFactory;
import org.framework.EmailSender;
import org.framework.Screenshotter;
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

    @BeforeSuite
    public void logStart() {
        log.info("Starting test run...");
    }

    @BeforeClass
    public void startBrowser() {

    }

    @BeforeMethod
    public void setUp(Method method) {
        log.info("Executing test '{}'", method.getName());
        driver.get(base_url);
    }

    @AfterSuite
    public void cleanUp() {

        EmailSender emailSender = new EmailSender();
        try {
            emailSender.sendEmail();
        } catch (IOException | MailjetException e) {
            throw new RuntimeException(e);
        }


        DriverFactory.getInstance(browser).quitBrowser();
    //}
    }
}