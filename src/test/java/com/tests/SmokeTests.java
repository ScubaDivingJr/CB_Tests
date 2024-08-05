package com.tests;

import org.framework.CommonVerifications;
import org.framework.Menus;
import org.openqa.selenium.WebDriver;
import org.pages.*;
import org.testng.annotations.Test;

import static org.framework.CommonVerifications.getCommonVerifications;
import static org.framework.DriverFactory.getChromeDriver;



public class SmokeTests extends BaseTestClass {

    Header header = new Header();
    Homepage home = new Homepage();
    CommonVerifications commonVerifications = getCommonVerifications();
    OnlineAppointmentsPage onlineAppointments = OnlineAppointmentsPage.getOnlineAppointmentsPage();
    WebDriver driver = getChromeDriver();

    @Test
    void HomePageloaded() {
        commonVerifications
                .verifyIsDisplayed(Homepage.HomeButtonAndActive())
                .verifyIsDisplayed(Homepage.Logo())
                .verifyIsDisplayed(Homepage.Slide())
                .verifyTextOnPage("Consultanta cosmetica personalizata");
    }

    @Test
    void sliderButtons() {
        header.clickHome();
        home.clickSlideshowDetails(2);
        commonVerifications.verifyTextOnPage("Mezoterapie virtuala");
    }

    @Test
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