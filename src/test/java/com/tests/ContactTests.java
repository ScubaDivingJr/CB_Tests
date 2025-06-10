package com.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.enums.Menus;
import org.framework.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.pages.ContactPage;
import org.pages.Header;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactTests extends BaseTestClass {

    private static final Logger log = LogManager.getLogger(ContactTests.class);


    Header header = new Header();

    @Override
    @BeforeClass
    public void beforeClassSetup() {
            log.info("Executing prerequisites for test class '{}'", this.getClass().getSimpleName());
            WebDriver driver = DriverFactory.getInstance(browser).getDriver();
            driver.get(base_url);
            header.clickHamburgerMenuItem(Menus.CONTACT);
    }

    @Test
    void verifyAddressText() {
        ContactPage contactPage = new ContactPage();
        Assert.assertTrue(contactPage.verifyAddressContent());
    }

    @Test
    void verifyPhoneText() {
        ContactPage contactPage = new ContactPage();
        Assert.assertTrue(contactPage.verifyPhoneContent());
    }

    @Test
    void verifyEmailText() {
        ContactPage contactPage = new ContactPage();
        Assert.assertTrue(contactPage.verifyEmailContent());
    }

    @Test
    void verifyContactFormSubmission() {
        ContactPage contactPage = new ContactPage();
        Assert.assertTrue(contactPage.verifyContactFormWithDummyData());
    }

    @Test
    void verifyGoogleMapsIFramePresence() {
        ContactPage contactPage = new ContactPage();
        Assert.assertTrue(contactPage.verifyGoogleMapsIFrameIsPresent());
    }
}
