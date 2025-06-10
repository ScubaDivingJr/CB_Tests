package com.tests;

import com.mailjet.client.errors.MailjetException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.enums.Menus;
import org.framework.CommonVerifications;
import org.framework.DriverFactory;
import org.framework.EmailSender;
import org.openqa.selenium.WebDriver;
import org.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static org.framework.CommonVerifications.getCommonVerifications;

public class SmokeTests extends BaseTestClass {


    @Override
    @BeforeClass
    public void beforeClassSetup() {
        Logger log = LogManager.getLogger(SmokeTests.class);
        log.info("Executing prerequisites for '{}'...", this.getClass().getSimpleName());
        WebDriver driver = DriverFactory.getInstance(browser).getDriver();
        driver.get(base_url);
    }

    @Override
    public void beforeMethodSetup(){
        //navigate to homepage before for all of these.
        WebDriver driver = DriverFactory.getInstance(browser).getDriver();
        driver.get(base_url);
    }

    @Test
    void HomePageloaded() {
        CommonVerifications commonVerifications = getCommonVerifications();
        commonVerifications
                .verifyTextOnPage("Consultanta cosmetica personalizata");
    }

    @Test
    void sliderButtons() {
        Homepage home = new Homepage();
        Header header = new Header();
        CommonVerifications commonVerifications = getCommonVerifications();
        header.clickHamburgerMenuItem(Menus.ACASA);
        home.switchToSlide(1);
        home.switchToSlide(0);
        home.clickCurrentSlideDetailsBtn();
        commonVerifications.verifyTextOnPage("Mezoterapie virtuala");
    }

    @Test
    void createOnlineAppointment() {
        Header header = new Header();
        OnlineAppointmentsPage onlineAppointments = new OnlineAppointmentsPage();
        header.clickHamburgerMenuItem(Menus.PROGRAMARI_ONLINE);
        //onlineAppointments.sendCompleteOnlineAppointmentWithDummyData(); --don't send it. it doesn't work. Stalls for 10 seconds.
        //Assert.assertTrue(onlineAppointments.verifyMessageAfterSubmit());

        //we know this doesn't work. let's not waste 10 seconds waiting on it to fail.
        Assert.fail();
    }
}
