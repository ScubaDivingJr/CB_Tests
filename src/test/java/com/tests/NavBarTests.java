package com.tests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.internal.LogManagerStatus;
import org.framework.CommonVerifications;
import org.enums.Menus;
import org.framework.DriverFactory;
import org.openqa.selenium.WebDriver;
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
        log.info("Executing prerequisites for '{}'...", this.getClass().getSimpleName());
        WebDriver driver = DriverFactory.getInstance(browser).getDriver();
        driver.get(base_url);
    }

    @BeforeMethod
    public void navigateToHomePage() {
        //for these tests, we have to navigate to homepage each time.
        WebDriver driver = DriverFactory.getInstance(browser).getDriver();
        driver.get(base_url);
    }



    @Test
    void homeButtonTest() {

        if (DriverFactory.headless) { throw new SkipException("Skipping test in headless mode. There's no navbar.");}
        Header header = new Header();
        CommonVerifications commonVerifications = new CommonVerifications();
        header.clickNavBarMenuItem(Menus.ACASA);
        commonVerifications.verifyUrl("https://cosmeticabrasov.ro/");
    }
    @Test
    void despreNoiTest() {
        if (DriverFactory.headless) { throw new SkipException("Skipping test in headless mode. There's no navbar.");}
        Header header = new Header();
        CommonVerifications commonVerifications = new CommonVerifications();
        header.clickNavBarMenuItem(Menus.DESPRE_NOI);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/despre-noi.html")
                .verifyTextOnPage("COSMETICIANA TA");
    }
    @Test
    void serviciiParent() {
        if (DriverFactory.headless) { throw new SkipException("Skipping test in headless mode. There's no navbar.");}
        Header header = new Header();
        CommonVerifications commonVerifications = new CommonVerifications();
        header.clickNavBarMenuItem(Menus.SERVICII);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/servicii.html")
                .verifyTextOnPage("Tratamente Faciale");
    }

    @Test
    void serviciiTratementeFaciale() {
        if (DriverFactory.headless) { throw new SkipException("Skipping test in headless mode. There's no navbar.");}
        Header header = new Header();
        CommonVerifications commonVerifications = new CommonVerifications();
        header.clickNavBarMenuItem(Menus.TRATAMENTE_FACIALE);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/servicii/tratamente-faciale.html")
                .verifyTextOnPage("Tratamente Faciale");
    }
    @Test
    void serviciiMezoterapieVirtuala() {
        if (DriverFactory.headless) { throw new SkipException("Skipping test in headless mode. There's no navbar.");}
        Header header = new Header();
        CommonVerifications commonVerifications = new CommonVerifications();
        header.clickNavBarMenuItem(Menus.MEZOTERAPIE_VIRTUALA);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/servicii/tratamente-faciale/mezoterapie-virtuala.html")
                .verifyTextOnPage("Mezoterapie virtuala");
    }
    @Test
    void serviciiTerapieCuOxigen() {
        if (DriverFactory.headless) { throw new SkipException("Skipping test in headless mode. There's no navbar.");}
        Header header = new Header();
        CommonVerifications commonVerifications = new CommonVerifications();
        header.clickNavBarMenuItem(Menus.TERAPIE_CU_OXIGEN);
        commonVerifications
                .verifyUrl("https://cosmeticabrasov.ro/servicii/tratamente-faciale/terapie-cu-oxigen-hiperboric.html")
                .verifyTextOnPage("Terapie cu oxigen hiperbaric");
        }
}
