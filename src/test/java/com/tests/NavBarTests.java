package com.tests;
import org.framework.CommonVerifications;
import org.framework.Menus;
import org.framework.TestUtils;
import org.junit.jupiter.api.*;
import org.pages.Header;


public class NavBarTests extends BaseTestClass {

    Header header = new Header();
    CommonVerifications commonVerifications = new CommonVerifications();
    TestUtils testUtils = new TestUtils();

    @Test
    void homeButtonTest() {
        header.clickHome();
        commonVerifications.verifyUrl("https://cosmeticabrasov.ro/");
    }
    @Test
    void despreNoiTest() {
        header.clickNavBarItem(Menus.DESPRE_NOI);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/despre-noi.html")
                .verifyTextOnPage("COSMETICIANA TA");
    }
    @Test
    void serviciiParent() {
        header.clickHome();
        header.clickNavBarItem(Menus.SERVICII);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/servicii.html")
                .verifyTextOnPage("Tratamente Faciale");
    }

    @Test
    void serviciiTratementeFaciale() {
        header.clickHome();
        header.clickNavBarItem(Menus.TRATAMENTE_FACIALE);
        testUtils.explicitWait();
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/servicii/tratamente-faciale.html")
                .verifyTextOnPage("Tratamente Faciale");
    }
    @Test
    void serviciiMezoterapieVirtuala() {
        header.clickHome();
        header.clickNavBarItem(Menus.MEZOTERAPIE_VIRTUALA);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/servicii/tratamente-faciale/mezoterapie-virtuala.html")
                .verifyTextOnPage("Mezoterapie virtuala");
    }
    @Test
    void serviciiTerapieCuOxigen() {
        header.clickHome();
        header.clickNavBarItem(Menus.TERAPIE_CU_OXIGEN);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/servicii/tratamente-faciale/terapie-cu-oxigen-hiperboric.html")
                .verifyTextOnPage("Terapie cu oxigen hiperbaric");
        }
}
