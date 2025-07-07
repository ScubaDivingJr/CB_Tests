package com.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.enums.FooterMenuItems;
import org.framework.utils.CommonVerifications;
import org.pages.Footer;
import org.testng.annotations.Test;

public class FooterTests extends BaseTestClass {

    private static final Logger log = LogManager.getLogger(FooterTests.class);
    private CommonVerifications commonVerifications;
    private Footer footer;

    @Override
    public void individualClassSetup() {
        log.info("Executing prerequisites for '{}'...", this.getClass().getSimpleName());
        this.footer = new Footer();
        this.commonVerifications = new CommonVerifications();
        driver.get(base_url);
    }

    @Test
    void footerDespreNoi() {
        footer.clickFooterItem(FooterMenuItems.DESPRE_NOI);
        commonVerifications.verifyTextOnPage("Pielea noastra este o MINUNE");
    }

    @Test
    void footerServicii() {
        footer.clickFooterItem(FooterMenuItems.SERVICII);
        commonVerifications.verifyTextOnPage("Tratamente Faciale");
    }

    @Test
    void footerBlog() {
        footer.clickFooterItem(FooterMenuItems.BLOG);
        commonVerifications.verifyTextOnPage("BLOG - NOUTATI -INFO");
    }

    @Test
    void footerFacebook() {
        footer.clickFooterItem(FooterMenuItems.FACEBOOK_BTN);
        //footer.loginToFacebook();    -- Facebook just blocked me from creating a test account. Whatever. Shelving this.
        footer.closeFacebookWindowsAndSwitchToOriginal();
    }
}
