package com.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.enums.FooterMenuItems;
import org.framework.CommonVerifications;
import org.framework.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.pages.Footer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FooterTests extends BaseTestClass {

    @Override
    @BeforeClass
    public void beforeClassSetup() {
        Logger log = LogManager.getLogger(FooterTests.class);
        log.info("Executing prerequisites for '{}'...", this.getClass().getSimpleName());
        WebDriver driver = DriverFactory.getInstance(browser).getDriver();
        driver.get(base_url);
    }

    @Test
    void footerDespreNoi() {
        CommonVerifications commonVerifications = new CommonVerifications();
        Footer footer = new Footer();
        footer.clickFooterItem(FooterMenuItems.DESPRE_NOI);
        commonVerifications.verifyTextOnPage("Pielea noastra este o MINUNE");
    }

    @Test
    void footerServicii() {
        CommonVerifications commonVerifications = new CommonVerifications();
        Footer footer = new Footer();
        footer.clickFooterItem(FooterMenuItems.SERVICII);
        commonVerifications.verifyTextOnPage("Tratamente Faciale");
    }

    @Test
    void footerBlog() {
        CommonVerifications commonVerifications = new CommonVerifications();
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
