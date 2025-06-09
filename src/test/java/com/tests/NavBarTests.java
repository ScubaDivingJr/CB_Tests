package com.tests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.internal.LogManagerStatus;
import org.framework.CommonVerifications;
import org.enums.Menus;
import org.framework.DriverFactory;
import org.pages.Header;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class NavBarTests extends BaseTestClass {

    private static final Logger log = LogManager.getLogger(ContactTests.class);

    @Override
    @BeforeClass
    public void beforeClassSetup() {
        if (DriverFactory.headless) {
            log.info("Skipping navBar tests in headless mode. It becomes the Hamburger menu at that resolution.");
            throw new SkipException("Skipped.");
        } else {
            log.info("Executing prerequisites for '{}'...", this.getClass().getSimpleName());
            driver.get(base_url);
        }
    }

    @Override
    @BeforeMethod
    public void beforeMethodSetup() {
        //for these tests, we have to navigate to homepage each time.
        driver.get(base_url);
    }

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
