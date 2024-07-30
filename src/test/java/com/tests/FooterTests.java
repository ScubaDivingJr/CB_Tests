package com.tests;

import org.framework.CommonVerifications;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.pages.Footer;

public class FooterTests extends BaseTestClass {

    Footer footer = new Footer();
    CommonVerifications commonVerifications = new CommonVerifications();

    @Test
    @Order(11)
    void footerDespreNoi() {
        footer.clickDespreNoi();
        commonVerifications.verifyTextOnPage("Pielea noastra este o MINUNE");
    }

    @Test @Order(12)
    void footerServicii() {
        footer.clickServicii();
        commonVerifications.verifyTextOnPage("Tratamente Faciale");
    }

    @Test @Order(13)
    void footerBlog() {
        footer.clickBlog();
        commonVerifications.verifyTextOnPage("BLOG - NOUTATI -INFO");
    }
}
