package com.tests;

import org.framework.CommonVerifications;
import org.framework.Menus;
import org.framework.Treatments;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.framework.CommonVerifications.getCommonVerifications;

import org.pages.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class AllTests extends BaseTestClass {

    Header header = new Header();
    Homepage home = new Homepage();
    ServicesPage serv = new ServicesPage();
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

    @Test @Order(2)
    public void DespreNoi(){
        header.clickNavBarItem(Menus.DESPRE_NOI);
        commonVerifications.verifyTextOnPage("COSMETICIANA TA");
    }

    @Test @Order(2)
    void ServiciiTratamenteDermato() {
        header.clickNavBarItem(Menus.SERVICII);
        assertEquals(serv.getAllTreatmentDescriptions(), serv.getExpectedTreatmentDescriptions());
    }

    @Test
    void sliderButtons() {
        header.clickHome();
        home.clickSlideshowDetails(2);
        commonVerifications.verifyTextOnPage("Mezoterapie virtuala");
    }

    @Test @Tag("TreatmentDescriptionTests") @Order (3)
    void checkPeelingEnzimaticDescription() {
        header.clickNavBarItem(Menus.SERVICII);
        serv.clickTreatment(Treatments.TRATAMENTUL_CU_PEELING_ENZIMATIC);

        String actualTreatmentDescription = serv.getActualTreatmentDescription(Treatments.TRATAMENTUL_CU_PEELING_ENZIMATIC);
        String expectedTreatmentDescription = "Contine extracte de fructe exotice ananas si papaia";

        assertTrue(actualTreatmentDescription.contains(expectedTreatmentDescription), "Description not found.");
    }

    @Test @Tag("TreatmentDescriptionTests") @Order(4)

    void checkTratamentCuPhytopeelingDescription()  {
        header.clickNavBarItem(Menus.SERVICII);
        serv.clickTreatment(Treatments.TRATAMENTUL_CU_PHYTOPEELING);

        String actualTreatmentDescription = serv.getActualTreatmentDescription(Treatments.TRATAMENTUL_CU_PHYTOPEELING);
        String expectedTreatmentDescription = "Reprezinta un peeling chimic mediu si in acelasi timp";

        assertTrue(actualTreatmentDescription.contains(expectedTreatmentDescription), "Description not found");
    }

    @Test @Tag("TreatmentDescriptionTests") @Order(5)
    void checkTratamentulAcneeiDescription() {
        header.clickNavBarItem(Menus.SERVICII);
        serv.clickTreatment(Treatments.TRATAMENTUL_ACNEEI);

        String actualTreatmentDescription = serv.getActualTreatmentDescription(Treatments.TRATAMENTUL_ACNEEI);
        String expectedTreatmentDescription = "Acneea reprezinta o afectiune dermatologica la nivelul glandelor sebacee";

        assertTrue(actualTreatmentDescription.contains(expectedTreatmentDescription), "Description incorrect");
    }

    @Test @Tag("TreatmentDescriptionTests") @Order(5)
    void checkTerapieDnaRepairDescription() throws InterruptedException {
        header.clickNavBarItem(Menus.SERVICII);
        serv.clickTreatment(Treatments.TERAPIE_DNA_REPAIR);

        String actualTreatmentDescription = serv.getActualTreatmentDescription(Treatments.TERAPIE_DNA_REPAIR);
        String expectedTreatmentDescription = "Terapie destinata tuturor tipurilor de ten";

        assertTrue(actualTreatmentDescription.contains(expectedTreatmentDescription), "Description did not match.");
    }

    @Test @Tag("treatmentDescriptionTest") @Order(5)
    void checkTerapiaCuVitaminaCDescription() {
        header.clickNavBarItem(Menus.SERVICII);
        serv.clickTreatment(Treatments.TERAPIE_CU_VITAMINA_C);

        String actualTreatmentDescription = serv.getActualTreatmentDescription(Treatments.TERAPIE_CU_VITAMINA_C);
        String expectedTreatmentDescription = "Terapie destinata tenurilor deshidratate";

        assertTrue(actualTreatmentDescription.contains(expectedTreatmentDescription));
    }

    @Test @Tag("TreatmentDescriptionTests") @Order(6)
    void checkTerapieCuCaviarDescription() {
        header.clickNavBarItem(Menus.SERVICII);
        serv.clickTreatment(Treatments.TERAPIE_CU_CAVIAR);

        String actualTreatmentDescription = serv.getActualTreatmentDescription(Treatments.TERAPIE_CU_CAVIAR);
        String expectedTreatmentDescription = "Destinata tenurilor cu riduri vizibile";

        assertTrue(actualTreatmentDescription.contains(expectedTreatmentDescription), "Description did not match");
    }

    @Test @Tag("TreatmentDescriptionTests") @Order(7)
    void checkTratamentCuHidratareDescription() {
        header.clickNavBarItem(Menus.SERVICII);
        serv.clickTreatment(Treatments.TRATAMENT_CU_HIDRATARE);

        String actualTreatmentDescription = serv.getActualTreatmentDescription(Treatments.TRATAMENT_CU_HIDRATARE);
        String expectedTreatmentDescription = "Destinat tenurilor deshidratate, ingredientul principal este acidul hialuronic";

        assertTrue(actualTreatmentDescription.contains(expectedTreatmentDescription));
    }

    @Test @Tag("TreatmentDescriptionTests") @Order(8)
    void checkTratamentDeLiftingDescription() {
        header.clickNavBarItem(Menus.SERVICII);
        serv.clickTreatment(Treatments.TRATAMENT_DE_LIFTING_CU_PROTEINE);

        String actualTreatmentDescription = serv.getActualTreatmentDescription(Treatments.TRATAMENT_DE_LIFTING_CU_PROTEINE);
        String expectedTreatmentDescription = "Combinatie a doua proteine superioare: proteina din zer";

        assertTrue(actualTreatmentDescription.contains(expectedTreatmentDescription));
    }

    @Test @Tag("TreatmentDescriptionTests") @Order(9)

    void checkTratamentPentruOchiDescription() {
        header.clickNavBarItem(Menus.SERVICII);
        serv.clickTreatment(Treatments.TRATAMENT_PENTRU_OCHI);

        String actualTreatmentDescription = serv.getActualTreatmentDescription(Treatments.TRATAMENT_PENTRU_OCHI);
        String expectedTreatmentDescription = "Terapie completa si complexa care impiedica formarea ridurilor";

        assertTrue(actualTreatmentDescription.contains(expectedTreatmentDescription));

    }

    @Test @Tag("TreatmentDescriptionTests") @Order(10)

    void checkTratamentulCuPulbereDeDiamanteDescription() {
        header.clickNavBarItem(Menus.SERVICII);
        serv.clickTreatment(Treatments.TRATAMENT_PULBERE_DIAMANTE);

        String actualTreatmentDescription = serv.getActualTreatmentDescription(Treatments.TRATAMENT_PULBERE_DIAMANTE);
        String expectedTreatmentDescription = "Tratamentul cu pulbere de diamante";

        assertTrue(actualTreatmentDescription.contains(expectedTreatmentDescription));
    }


    @Test @Order(11)
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