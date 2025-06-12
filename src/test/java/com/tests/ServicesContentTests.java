package com.tests;
import org.Data.TreatmentsDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.enums.Menus;
import org.enums.Treatments;
import org.framework.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.pages.*;
import org.testng.Assert;

import java.util.List;

public class ServicesContentTests extends BaseTestClass {

    private static final Logger log = LogManager.getLogger(ServicesContentTests.class);

    @Override
    public void individualClassSetup() {
        log.info("Executing prerequisites for '{}'...", this.getClass().getSimpleName());

        driver.get(base_url);
    }

    @Test
    void verifyTreatmentTitles() {
        ServicesPage servicesPage = new ServicesPage();
        Header header = new Header();
        //checking that all treatments titles are there against a list that we have.
        header.clickHamburgerMenuItem(Menus.SERVICII);
        List<String> actualTreatmentTitles = servicesPage.getActualTreatmentTitles();
        List<String> expectedTreatmentTitles = TreatmentsDataProvider.getExpectedTreatmentTitles();

        Assert.assertEquals(actualTreatmentTitles.size(), expectedTreatmentTitles.size());

        for (int i = 0; i < expectedTreatmentTitles.size(); i++) {
            Assert.assertEquals(actualTreatmentTitles.get(i), expectedTreatmentTitles.get(i));
        }
    }

    @Test
    void verifyTreatmentDescriptions() {
        ServicesPage servicesPage = new ServicesPage();
        Header header = new Header();
        header.clickHamburgerMenuItem(Menus.SERVICII);
        List<String> actualTreatmentDescriptions = servicesPage.getActualTreatmentDescriptions();
        List<String> expectedTreatmentDescriptions = TreatmentsDataProvider.expectedTreatmentDescriptionExcerpts().values().stream().toList();

        Assert.assertEquals(actualTreatmentDescriptions.size(), expectedTreatmentDescriptions.size());

        for (int i = 0; i < expectedTreatmentDescriptions.size(); i++) {
            Assert.assertTrue(actualTreatmentDescriptions.get(i).contains(expectedTreatmentDescriptions.get(i)),
                    "Expected description:\n" + expectedTreatmentDescriptions.get(i) + "\ndid not contain:\n" + actualTreatmentDescriptions.get(i));
        }
    }

    @Test
    void verifyTreatmentDescriptionImages() {
        ServicesPage servicesPage = new ServicesPage();
        Header header = new Header();
        header.clickHamburgerMenuItem(Menus.SERVICII);
        Assert.assertTrue(servicesPage.checkTreatmentImages(), "Something went wrong checking the images.");
    }

    @Test
    void verifyFacialTreatmentsImages() {
        ServicesPage servicesPage = new ServicesPage();
        Header header = new Header();
        header.clickHamburgerMenuItem(Menus.SERVICII);
        Assert.assertTrue(servicesPage.checkFacialTreatmentImages(), "Something went wrong checking the images.");
    }
}
