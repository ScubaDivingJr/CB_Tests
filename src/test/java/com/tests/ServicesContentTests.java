package com.tests;
import org.Data.TreatmentsDataProvider;
import org.framework.CommonVerifications;
import org.enums.Menus;
import org.enums.Treatments;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.pages.*;
import org.testng.Assert;

import java.util.List;

public class ServicesContentTests extends BaseTestClass {

    ServicesPage servicesPage = new ServicesPage();
    Header header = new Header();

    @Test
    void verifyTreatmentTitles() {
        //checking that all treatments titles are there against a list that we have.
        header.clickNavBarMenuItem(Menus.SERVICII);
        List<String> actualTreatmentTitles = servicesPage.getActualTreatmentTitles();
        List<String> expectedTreatmentTitles = TreatmentsDataProvider.getExpectedTreatmentTitles();

        Assert.assertEquals(actualTreatmentTitles.size(), expectedTreatmentTitles.size());

        for (int i = 0; i < expectedTreatmentTitles.size(); i++) {
            Assert.assertEquals(actualTreatmentTitles.get(i), expectedTreatmentTitles.get(i));
        }
    }

    @Test
    void verifyTreatmentDescriptions() {
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
        header.clickNavBarMenuItem(Menus.SERVICII);
        Assert.assertTrue(servicesPage.checkTreatmentImage(Treatments.TRATAMENTUL_CU_PEELING_ENZIMATIC), "Something went wrong chekcing the image.");
    }

    @Test
    void verifyAllTreatmentDescriptionImages() {
        header.clickNavBarMenuItem(Menus.SERVICII);
        servicesPage.checkTreatmentImages();
    }
}
