package com.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.enums.Menus;
import org.pages.ContactPage;
import org.pages.Header;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ContactTests extends BaseTestClass {

    private static final Logger log = LogManager.getLogger(ContactTests.class);

    @Override
    protected void individualClassSetup() {

        log.info("Executing prerequisites for test class '{}'", this.getClass().getSimpleName());

        driver.get(base_url);
        Header header = new Header();
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
        contactPage.fillContactFormWithDummyData();
        //contactPage.sendFormAndVerifySent(); //let's not spam this for now.
    }

    @Test
    void verifyGoogleMapsIFramePresence() {
        ContactPage contactPage = new ContactPage();
        Assert.assertTrue(contactPage.verifyGoogleMapsIFrameIsPresent());
    }
}
