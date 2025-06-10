package com.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.framework.CommonVerifications;
import org.enums.Menus;
import org.framework.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.pages.Header;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HamburgerMenuTests extends BaseTestClass{

    @Override
    @BeforeClass()
    public void beforeClassSetup() {
        Logger log = LogManager.getLogger(HamburgerMenuTests.class);
        //navigate to the homepage once when starting these tests
        log.info("Executing prerequisites for '{}'...", this.getClass().getSimpleName());
        WebDriver driver = DriverFactory.getInstance(browser).getDriver();
        driver.get(base_url);
    }

    @Test
    void hamburgerMenuHomeTest() {
        CommonVerifications commonVerifications = new CommonVerifications();
        Header header = new Header();
        header.clickHamburgerMenuItem(Menus.ACASA);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/")
                .verifyTextOnPage("Consultanta cosmetica personalizata");
    }
    @Test
    void hamburgerMenuDespreNoiTest() {
        CommonVerifications commonVerifications = new CommonVerifications();
        Header header = new Header();
        header.clickHamburgerMenuItem(Menus.DESPRE_NOI);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/despre-noi.html")
                .verifyTextOnPage("Pielea noastra este o MINUNE");
    }

    @Test
    void hamburgerMenuServicesTest() {
        CommonVerifications commonVerifications = new CommonVerifications();
        Header header = new Header();
        header.clickHamburgerMenuItem(Menus.SERVICII);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/servicii.html")
                .verifyTextOnPage("Tratamente Faciale");
    }
    @Test
    void hamburgerMenuFacialTreatmentsTest() {
        CommonVerifications commonVerifications = new CommonVerifications();
        Header header = new Header();
        header.clickHamburgerMenuItem(Menus.TRATAMENTE_FACIALE);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/servicii/tratamente-faciale.html")
                .verifyTextOnPage("Tratamente Faciale");
    }
    @Test
    void hamburgerMenuMezotherapyTest() {
        CommonVerifications commonVerifications = new CommonVerifications();
        Header header = new Header();
        header.clickHamburgerMenuItem(Menus.MEZOTERAPIE_VIRTUALA);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/servicii/tratamente-faciale/mezoterapie-virtuala.html")
                .verifyTextOnPage("Mezoterapie virtuala");
    }
    @Test
    void hamburgerMenuOxygenTherapyTest() {
        CommonVerifications commonVerifications = new CommonVerifications();
        Header header = new Header();
        header.clickHamburgerMenuItem(Menus.TERAPIE_CU_OXIGEN);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/servicii/tratamente-faciale/terapie-cu-oxigen-hiperboric.html")
                .verifyTextOnPage("Terapie cu oxigen hiperbaric");
    }
    @Test
    void hamburgerMenuMicroneedlingTest() {
        CommonVerifications commonVerifications = new CommonVerifications();
        Header header = new Header();
        header.clickHamburgerMenuItem(Menus.MICRONEEDLING_SAU_MEZOTERAPIE_CU_ACE);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/servicii/tratamente-faciale/microneedling-sau-mezoterapie-cu-ace.html")
                .verifyTextOnPage("Microneedling sau mezoterapie cu ace");

    }
    @Test
    void hamburgerMenuDermatoTreatments() {
        CommonVerifications commonVerifications = new CommonVerifications();
        Header header = new Header();
        header.clickHamburgerMenuItem(Menus.TRATAMENTE_DERMATO_COSMETICE);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/servicii/tratamente-faciale/tratamente-dermato-cosmetice.html")
                .verifyTextOnPage("Tratamente dermato-cosmetice");
    }
    @Test
    void hamburgerMenuMakeupTest() {
        CommonVerifications commonVerifications = new CommonVerifications();
        Header header = new Header();
        header.clickHamburgerMenuItem(Menus.MAKE_UP);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/servicii/make-up.html")
                .verifyTextOnPage("Make-up");
    }
    @Test
    void hamburgerEyebrowStylingTest() {
        CommonVerifications commonVerifications = new CommonVerifications();
        Header header = new Header();
        header.clickHamburgerMenuItem(Menus.STILIZARE_SPRANCENE);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/servicii/stilizare-sprancene.html")
                .verifyTextOnPage("Stilizare sprancene");
    }
    @Test
    void hamburgerMicrobladingTest() {
        CommonVerifications commonVerifications = new CommonVerifications();
        Header header = new Header();
        header.clickHamburgerMenuItem(Menus.MICROBLADING);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/servicii/microblading.html")
                .verifyTextOnPage("Microblading");
    }
    @Test
    void hamburgerWaxingTest() {
        CommonVerifications commonVerifications = new CommonVerifications();
        Header header = new Header();
        header.clickHamburgerMenuItem(Menus.EPILARE_CU_CEARA_DE_UNICA_FOLOSINTA);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/servicii/epilare-cu-ceara-de-unica-folosinta.html")
                .verifyTextOnPage("Epilare cu ceara de unica folosinta");
    }
    @Test
    void hamburgerOnlineAppoimentsTest() {
        CommonVerifications commonVerifications = new CommonVerifications();
        Header header = new Header();
        header.clickHamburgerMenuItem(Menus.PROGRAMARI_ONLINE);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/programari-online.html")
                .verifyTextOnPage("Completeaza formularul de mai jos pentru a te programa online.");
    }
    @Test
    void hamburgerBlogTest() {
        CommonVerifications commonVerifications = new CommonVerifications();
        Header header = new Header();
        header.clickHamburgerMenuItem(Menus.BLOG);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/blog.html")
                .verifyTextOnPage("Blog - Noutati -Info");
    }
    @Test
    void hamburgerContactTest() {
        CommonVerifications commonVerifications = new CommonVerifications();
        Header header = new Header();
        header.clickHamburgerMenuItem(Menus.CONTACT);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/contact.html")
                .verifyTextOnPage("Formular de contact");
    }
}
