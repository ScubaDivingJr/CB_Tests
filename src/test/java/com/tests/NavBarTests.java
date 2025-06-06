package com.tests;
import org.framework.CommonVerifications;
import org.enums.Menus;
import org.pages.Header;
import org.testng.annotations.Test;


public class NavBarTests extends BaseTestClass {

    Header header = new Header();
    CommonVerifications commonVerifications = new CommonVerifications();

    @Test
    void homeButtonTest() {
        header.clickNavBarMenuItem(Menus.ACASA);
        commonVerifications.verifyUrl("https://cosmeticabrasov.ro/");
    }
    @Test
    void despreNoiTest() {
        header.clickNavBarMenuItem(Menus.DESPRE_NOI);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/despre-noi.html")
                .verifyTextOnPage("COSMETICIANA TA");
    }
    @Test
    void serviciiParent() {
        header.clickNavBarMenuItem(Menus.SERVICII);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/servicii.html")
                .verifyTextOnPage("Tratamente Faciale");
    }

    @Test
    void serviciiTratementeFaciale() {
        header.clickNavBarMenuItem(Menus.TRATAMENTE_FACIALE);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/servicii/tratamente-faciale.html")
                .verifyTextOnPage("Tratamente Faciale");
    }
    @Test
    void serviciiMezoterapieVirtuala() {
        header.clickNavBarMenuItem(Menus.MEZOTERAPIE_VIRTUALA);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/servicii/tratamente-faciale/mezoterapie-virtuala.html")
                .verifyTextOnPage("Mezoterapie virtuala");
    }
    @Test
    void serviciiTerapieCuOxigen() {
        header.clickNavBarMenuItem(Menus.TERAPIE_CU_OXIGEN);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/servicii/tratamente-faciale/terapie-cu-oxigen-hiperboric.html")
                .verifyTextOnPage("Terapie cu oxigen hiperbaric");
        }
}
