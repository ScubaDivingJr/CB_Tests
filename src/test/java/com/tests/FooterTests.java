package com.tests;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.enums.FooterMenuItems;
import org.framework.CommonVerifications;
import org.framework.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.pages.Footer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.lang.reflect.Method;

public class FooterTests extends BaseTestClass {

    private static final Logger log = LogManager.getLogger(FooterTests.class);

    @Override
    @BeforeClass
    public void beforeClassSetup() {
        log.info("Executing prerequisites for '{}'...", this.getClass().getSimpleName());
        WebDriver driver = DriverFactory.getInstance(browser).getDriver();
        driver.get(base_url);
    }


    CommonVerifications commonVerifications = new CommonVerifications();

    @Test
    void footerDespreNoi() {
        Footer footer = new Footer();
        footer.clickFooterItem(FooterMenuItems.DESPRE_NOI);
        commonVerifications.verifyTextOnPage("Pielea noastra este o MINUNE");
    }

    @Test
    void footerServicii() {
        Footer footer = new Footer();
        footer.clickFooterItem(FooterMenuItems.SERVICII);
        commonVerifications.verifyTextOnPage("Tratamente Faciale");
    }

    @Test
    void footerBlog() {
        Footer footer = new Footer();
        footer.clickFooterItem(FooterMenuItems.BLOG);
        commonVerifications.verifyTextOnPage("BLOG - NOUTATI -INFO");
    }

    @Test
    void footerFacebook() {
        Footer footer = new Footer();
        footer.clickFooterItem(FooterMenuItems.FACEBOOK_BTN);
        //footer.loginToFacebook();    -- getting a test Facebook account is painful. Shelving this.
        footer.closeFacebookWindowsAndSwitchToOriginal();
    }
}
