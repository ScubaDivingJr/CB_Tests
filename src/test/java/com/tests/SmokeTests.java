package com.tests;

import com.mailjet.client.errors.MailjetException;
import org.enums.Menus;
import org.framework.CommonVerifications;
import org.framework.EmailSender;
import org.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

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
        header.clickHamburgerMenuItem(Menus.ACASA);
        home.switchToSlide(1);
        home.switchToSlide(0);
        home.clickCurrentSlideDetailsBtn();
        commonVerifications.verifyTextOnPage("Mezoterapie virtuala");
    }

    @Test
    void createOnlineAppointment() {
        header.clickHamburgerMenuItem(Menus.PROGRAMARI_ONLINE);
        onlineAppointments.sendCompleteOnlineAppointmentWithDummyData();
        Assert.assertTrue(onlineAppointments.verifyMessageAfterSubmit());
    }

/*    @Test
    void testtest() throws IOException, MailjetException {
        EmailSender emailSender = new EmailSender();
        emailSender.sendEmail();
        //emailSender.showEnvVariables();

    }*/
}
