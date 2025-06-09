package com.tests;

import com.mailjet.client.errors.MailjetException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.enums.Menus;
import org.framework.CommonVerifications;
import org.framework.EmailSender;
import org.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.framework.CommonVerifications.getCommonVerifications;

public class SmokeTests extends BaseTestClass {

    public static final Logger log = LogManager.getLogger(SmokeTests.class);

    Header header = new Header();
    Homepage home = new Homepage();
    CommonVerifications commonVerifications = getCommonVerifications();
    OnlineAppointmentsPage onlineAppointments = new OnlineAppointmentsPage();

    @Override
    @BeforeClass
    public void beforeClassSetup() {
        log.info("Executing prerequisites for '{}'...", this.getClass().getSimpleName());
        driver.get(base_url);
    }

    @Override
    public void beforeMethodSetup(){
        driver.get(base_url);
    }

    @Test
    void HomePageloaded() throws InterruptedException {
        Thread.sleep(1000);
        commonVerifications
                .verifyTextOnPage("Consultanta cosmetica personalizata");
    }

    @Test
    void sliderButtons() {
        header.clickHamburgerMenuItem(Menus.ACASA);
        home.switchToSlide(1);
        home.switchToSlide(0);
        home.clickCurrentSlideDetailsBtn();
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
