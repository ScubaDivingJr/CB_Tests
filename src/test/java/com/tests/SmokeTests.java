package com.tests;

import org.framework.CommonVerifications;
import org.framework.Menus;
import org.junit.jupiter.api.*;

import static org.framework.CommonVerifications.getCommonVerifications;

import org.pages.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class SmokeTests extends BaseTestClass {

    Header header = new Header();
    Homepage home = new Homepage();
    Footer footer = Footer.getFooter();
    CommonVerifications commonVerifications = getCommonVerifications();
    OnlineAppointmentsPage onlineAppointments = OnlineAppointmentsPage.getOnlineAppointmentsPage();

    @Test @Order(1)
    void HomePageloaded() {
        driver.get(BASE_URL);
        commonVerifications.verifyIsDisplayed(Homepage.HomeButtonAndActive());
        commonVerifications.verifyIsDisplayed(Homepage.Logo());
        commonVerifications.verifyIsDisplayed(Homepage.Slide());
        commonVerifications.verifyTextOnPage("Consultanta cosmetica personalizata");
    }

    @Test
    void sliderButtons() {
        header.clickHome();
        home.clickSlideshowDetails(2);
        commonVerifications.verifyTextOnPage("Mezoterapie virtuala");
    }

    @Test @Order(14)
    void createOnlineAppointment() {
        header.clickNavBarItem(Menus.PROGRAMARI_ONLINE);
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