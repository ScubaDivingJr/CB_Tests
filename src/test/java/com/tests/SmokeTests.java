package com.tests;

import org.enums.Menus;
import org.framework.CommonVerifications;
import org.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.framework.CommonVerifications.getCommonVerifications;

public class SmokeTests extends BaseTestClass {

    Header header = new Header();
    Homepage home = new Homepage();
    CommonVerifications commonVerifications = getCommonVerifications();
    OnlineAppointmentsPage onlineAppointments = new OnlineAppointmentsPage();

    @Test
    void HomePageloaded() {
        commonVerifications
                .verifyTextOnPage("Consultanta cosmetica personalizata");
    }

    @Test
    void sliderButtons() {
        header.clickNavBarMenuItem(Menus.ACASA);
        home.switchToSlide(1);
        home.clickCurrentSlideDetailsBtn();
        commonVerifications.verifyTextOnPage("Mezoterapie virtuala");
    }

    @Test
    void createOnlineAppointment() {
        header.clickNavBarMenuItem(Menus.PROGRAMARI_ONLINE);
        onlineAppointments.sendCompleteOnlineAppointmentWithDummyData();
        Assert.assertTrue(onlineAppointments.verifyMessageAfterSubmit());
    }
}
