package com.tests;

import org.framework.CommonVerifications;
import org.framework.Menus;
import org.framework.Treatments;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.pages.*;

public class ServicesContentTests extends BaseTestClass {

     Header header = new Header();
     ServicesPage serv = new ServicesPage();
     CommonVerifications commonVerifications = new CommonVerifications();
    @Test @Order(2)
    void ServiciiTratamenteDermato() {
        header.clickNavBarItem(Menus.SERVICII);
        assertEquals(serv.getAllTreatmentDescriptions(), serv.getExpectedTreatmentDescriptions());
    }

    @Test @Tag("TreatmentDescriptionTests")
    void checkPeelingEnzimaticDescription() {
        header.clickNavBarItem(Menus.SERVICII);
        serv.clickTreatment(Treatments.TRATAMENTUL_CU_PEELING_ENZIMATIC);

        commonVerifications.verifyTreatmentDescription(Treatments.TRATAMENTUL_CU_PEELING_ENZIMATIC,"Contine extracte de fructe exotice ananas si papaia");
    }

    @Test @Tag("TreatmentDescriptionTests")

    void checkTratamentCuPhytopeelingDescription()  {
        header.clickNavBarItem(Menus.SERVICII);
        serv.clickTreatment(Treatments.TRATAMENTUL_CU_PHYTOPEELING);

        commonVerifications.verifyTreatmentDescription(Treatments.TRATAMENTUL_CU_PHYTOPEELING, "Reprezinta un peeling chimic mediu si in acelasi timp");
    }

    @Test @Tag("TreatmentDescriptionTests")
    void checkTratamentulAcneeiDescription() {
        header.clickNavBarItem(Menus.SERVICII);
        serv.clickTreatment(Treatments.TRATAMENTUL_ACNEEI);

        commonVerifications.verifyTreatmentDescription(Treatments.TRATAMENTUL_ACNEEI, "Acneea reprezinta o afectiune dermatologica la nivelul glandelor sebacee");
    }

    @Test @Tag("TreatmentDescriptionTests")
    void checkTerapieDnaRepairDescription() {
        header.clickNavBarItem(Menus.SERVICII);
        serv.clickTreatment(Treatments.TERAPIE_DNA_REPAIR);

        commonVerifications.verifyTreatmentDescription(Treatments.TERAPIE_DNA_REPAIR, "Terapie destinata tuturor tipurilor de ten");
    }

    @Test @Tag("treatmentDescriptionTest")
    void checkTerapiaCuVitaminaCDescription() {
        header.clickNavBarItem(Menus.SERVICII);
        serv.clickTreatment(Treatments.TERAPIE_CU_VITAMINA_C);

        commonVerifications.verifyTreatmentDescription(Treatments.TERAPIE_CU_VITAMINA_C, "Terapie destinata tenurilor deshidratate");
    }

    @Test @Tag("TreatmentDescriptionTests")
    void checkTerapieCuCaviarDescription() {
        header.clickNavBarItem(Menus.SERVICII);
        serv.clickTreatment(Treatments.TERAPIE_CU_CAVIAR);

        commonVerifications.verifyTreatmentDescription(Treatments.TERAPIE_CU_CAVIAR, "Destinata tenurilor cu riduri vizibile");
    }

    @Test @Tag("TreatmentDescriptionTests")
    void checkTratamentCuHidratareDescription() {
        header.clickNavBarItem(Menus.SERVICII);
        serv.clickTreatment(Treatments.TRATAMENT_CU_HIDRATARE);

        commonVerifications.verifyTreatmentDescription(Treatments.TRATAMENT_CU_HIDRATARE, "Destinat tenurilor deshidratate, ingredientul principal este acidul hialuronic");
    }

    @Test @Tag("TreatmentDescriptionTests")
    void checkTratamentDeLiftingDescription() {
        header.clickNavBarItem(Menus.SERVICII);
        serv.clickTreatment(Treatments.TRATAMENT_DE_LIFTING_CU_PROTEINE);

        commonVerifications.verifyTreatmentDescription(Treatments.TRATAMENT_DE_LIFTING_CU_PROTEINE, "Combinatie a doua proteine superioare: proteina din zer");
    }

    @Test @Tag("TreatmentDescriptionTests") @Order(9)

    void checkTratamentPentruOchiDescription() {
        header.clickNavBarItem(Menus.SERVICII);
        serv.clickTreatment(Treatments.TRATAMENT_PENTRU_OCHI);

        commonVerifications.verifyTreatmentDescription(Treatments.TRATAMENT_PENTRU_OCHI, "Terapie completa si complexa care impiedica formarea ridurilor");

    }

    @Test @Tag("TreatmentDescriptionTests") @Order(10)

    void checkTratamentulCuPulbereDeDiamanteDescription() {
        header.clickNavBarItem(Menus.SERVICII);
        serv.clickTreatment(Treatments.TRATAMENT_PULBERE_DIAMANTE);

        commonVerifications.verifyTreatmentDescription(Treatments.TRATAMENT_PULBERE_DIAMANTE, "Tratamentul cu pulbere de diamante");
    }
}
