package com.tests;

import org.enums.Menus;
import org.framework.CommonVerifications;
import org.pages.*;
import org.testng.annotations.Test;

import static org.framework.CommonVerifications.getCommonVerifications;

public class SmokeTests extends BaseTestClass {

    Header header = new Header();
    Homepage home = new Homepage();
    CommonVerifications commonVerifications = getCommonVerifications();
    OnlineAppointmentsPage onlineAppointments = OnlineAppointmentsPage.getOnlineAppointmentsPage();

    @Test (groups = "SmokeTests")
    void HomePageloaded() {
        commonVerifications
                .verifyTextOnPage("Consultanta cosmetica personalizata");
    }

    @Test (groups = "SmokeTests")
    void sliderButtons() {
        header.clickHamburgerMenuItem(Menus.ACASA);
        home.clickCurrentSlideDetailsBtnTest();
        commonVerifications.verifyTextOnPage("Mezoterapie virtuala");
    }

    @Test
    void createOnlineAppointment() {
        header.clickNavBarMenuItem(Menus.PROGRAMARI_ONLINE);
        onlineAppointments.enterName("Andrei Marcu")
                .enterPhoneNumber("0726897976")
                .enterEmail("andrei.marcu1337@gmail.com")
                .enterPreferredDate("22/04/2023")
                .enterTimeOfDay("Dimineata")
                .additionalDetails("This is test")
                .clickSubmit();
        commonVerifications.verifyIsNotDisplayed(OnlineAppointmentsPage.systemMessage())
                .verifyIsDisplayed(OnlineAppointmentsPage.successMessage());
    }
}
