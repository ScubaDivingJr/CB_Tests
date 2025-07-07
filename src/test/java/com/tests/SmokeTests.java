package com.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.enums.Menus;
import org.framework.CommonVerifications;
import org.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTests extends BaseTestClass {

    private static final Logger log = LogManager.getLogger(SmokeTests.class);
    private CommonVerifications commonVerifications;
    private Header header;
    private OnlineAppointmentsPage onlineAppointmentsPage;
    private Homepage homepage;

    @Override
    public void individualClassSetup() {
        log.info("Executing prerequisites for '{}'...", this.getClass().getSimpleName());
        this.commonVerifications = new CommonVerifications();
        this.header = new Header();
        this.onlineAppointmentsPage = new OnlineAppointmentsPage();
        this.homepage = new Homepage();
        driver.get(base_url);
    }

    @Override
    public void beforeMethodSetup(){
        //navigate to homepage before for all of these.
        log.info("going to homepage");
        driver.get(base_url);
    }

    @Test
    void HomePageloaded() {
        commonVerifications.verifyTextOnPage("Consultanta cosmetica personalizata");
    }

    @Test
    void sliderButtons() {
        header.clickHamburgerMenuItem(Menus.ACASA);
        homepage.switchToSlide(1);
        homepage.switchToSlide(0);
        homepage.clickCurrentSlideDetailsBtn();
        commonVerifications.verifyTextOnPage("Mezoterapie virtuala");
    }

    @Test
    void createOnlineAppointment() {
        header.clickHamburgerMenuItem(Menus.PROGRAMARI_ONLINE);
        //onlineAppointments.sendCompleteOnlineAppointmentWithDummyData(); --don't send it. it doesn't work. Stalls for 10 seconds.
        //Assert.assertTrue(onlineAppointments.verifyMessageAfterSubmit());

        //we know this doesn't work. let's not waste 10 seconds waiting on it to fail.
        Assert.fail();
    }
}
