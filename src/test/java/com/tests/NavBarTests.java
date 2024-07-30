package com.tests;
import org.framework.CommonVerifications;
import org.framework.Menus;
import org.framework.TestUtils;
import org.framework.Treatments;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.Header;
import org.pages.Homepage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.framework.DriverFactory.getGetDriverWait;
import static org.junit.jupiter.api.Assertions.*;
import static org.framework.CommonVerifications.getCommonVerifications;
import org.pages.*;


public class NavBarTests extends BaseTestClass {

    Header header = new Header();
    CommonVerifications commonVerifications = new CommonVerifications();

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
        TestUtils.explicitWait();
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
