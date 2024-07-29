package org.pages;

import dev.failsafe.internal.util.FutureLinkedList;
import org.framework.Treatments;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.framework.DriverFactory.getChromeDriver;
import static org.framework.DriverFactory.getGetDriverWait;
import static org.framework.TestUtils.explicitWait;

public class ServicesPage {
    private final WebDriver driver = getChromeDriver();
    private final WebDriverWait wait = getGetDriverWait();

    public void clickTreatment(Treatments value) {
        WebElement ul = driver.findElement(By.cssSelector("ul[class='sppb-nav sppb-nav-tabs']"));
        List<WebElement> list1 = ul.findElements(By.tagName("li"));
        list1.get(value.ordinal()).click();
    }

    public String getActualTreatmentDescription(Treatments value) {
        WebElement ul = driver.findElement(By.cssSelector("ul[class='sppb-nav sppb-nav-tabs']"));
        List<WebElement> list1 = ul.findElements(By.tagName("li"));
        WebElement treatment = list1.get(value.ordinal());
        treatment.click();

        explicitWait();

        List<WebElement> treatmentDescriptionWrapper = driver.findElements(By.cssSelector("div[class='sppb-tab-pane sppb-fade active in']"));
        WebElement treatmentDescription = treatmentDescriptionWrapper.getFirst();
        explicitWait();
        return treatmentDescription.getText();

        //WebElement treatmentDescriptionWrapper = driver.findElement(By.cssSelector(("div[class='sppb-tab-pane sppb-fade active in']")));
        //return treatmentDescriptionWrapper.getText();

    }

    public List<String> getAllTreatmentDescriptions() {

        List<String> actualTreatments = new ArrayList<>();

        WebElement ul = driver.findElement(By.cssSelector("ul[class='sppb-nav sppb-nav-tabs']"));
        List<WebElement> list1 = ul.findElements(By.tagName("li"));

        for (WebElement webElement : list1) {
            actualTreatments.add(webElement.getText());
        }

        return actualTreatments;
    }

    public List<String> getExpectedTreatmentDescriptions() {
        return new ArrayList<>(Arrays.asList("TRATAMENTUL CU PEELING ENZIMATIC",
                "TRATAMENTUL CU PHYTOPEELING",
                "TRATAMENTUL ACNEEI",
                "TERAPIE DNA-REPAIR",
                "TERAPIE CU VITAMINA C",
                "TERAPIE CU CAVIAR",
                "TRATAMENT CU HIDRATARE",
                "TRATAMENT DE LIFTING CU PROTEINE",
                "TRATAMENT PENTRU OCHI",
                "TRATAMENTUL CU PULBERE DE DIAMANTE"));
    }

}
