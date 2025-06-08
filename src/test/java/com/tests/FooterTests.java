package com.tests;

import org.enums.FooterMenuItems;
import org.framework.CommonVerifications;
import org.pages.Footer;
import org.testng.annotations.Test;
import java.io.IOException;

public class FooterTests extends BaseTestClass {

    Footer footer = new Footer();
    CommonVerifications commonVerifications = new CommonVerifications();
    //TestUtils credentials = new TestUtils();

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
    void footerFacebook() throws IOException {
        footer.clickFooterItem(FooterMenuItems.FACEBOOK_BTN);
        footer.loginToFacebook();
        footer.closeFacebookWindowsAndSwitchToOriginal();
    }
}
