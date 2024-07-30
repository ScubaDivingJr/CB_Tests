package com.tests;

import org.framework.CommonVerifications;
import org.framework.TestUtils;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.pages.Footer;

import java.io.IOException;
import java.sql.SQLOutput;

public class FooterTests extends BaseTestClass {

    Footer footer = new Footer();
    CommonVerifications commonVerifications = new CommonVerifications();
    TestUtils credentials = new TestUtils();

    @Test
    void footerDespreNoi() {
        footer.clickDespreNoi();
        commonVerifications.verifyTextOnPage("Pielea noastra este o MINUNE");
    }

    @Test
    void footerServicii() {
        footer.clickServicii();
        commonVerifications.verifyTextOnPage("Tratamente Faciale");
    }

    @Test
    void footerBlog() {
        footer.clickBlog();
        commonVerifications.verifyTextOnPage("BLOG - NOUTATI -INFO");
    }

    @Test
    void footerFacebook() throws IOException {

        footer.clickFacebook();
        footer.verifyFacebookPageAndClose();
    }
}
