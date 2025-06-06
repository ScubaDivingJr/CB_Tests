package org.pages;

import org.enums.Treatments;
import org.framework.ImageChecker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServicesPage extends BasePage {

    private final By treatmentListLocator = By.cssSelector("ul[class='sppb-nav sppb-nav-tabs']");
    private final By activeTreatmentDescriptionWrapperLocator = By.cssSelector("div[class='sppb-tab-pane sppb-fade active in'");

    public void clickTreatment(Treatments treatment) {
        WebElement ul = driver.findElement(treatmentListLocator);
        List<WebElement> list1 = ul.findElements(By.tagName("li"));
        click(list1.get(treatment.getValue()), true);
    }

    public List<String> getActualTreatmentDescriptions() {

        List<String> actualTreatmentDescriptions = new ArrayList<>();
        List<WebElement> descriptions = getTreatmentDescriptionsContainer();

        //we locate this so we can click on the titles to load each description
        WebElement ul = driver.findElement(treatmentListLocator);
        List<WebElement> treatmentTitlesClickable = ul.findElements(By.tagName("li"));

        if (descriptions.size() == Treatments.values().length) {
            log.info("Size of descriptions String list is: {}", descriptions.size() + "\nThis is what we expect given our Treatments enum value.");
        } else {
            log.warn("The size of description String list isn't 10. Either more were added or we have a problem.");
        }

        if (!descriptions.isEmpty()) {
            for (int i = 0; i < descriptions.size(); i++) {

                //we have to click so they can render.
                click(treatmentTitlesClickable.get(i), true);

                //each description lives 3 divs under descriptions List. We have to wait for them to load when we click each one. We care about the first paragraph only.
                WebElement shortDescription = webDriverWait.until(ExpectedConditions.visibilityOf(descriptions.get(i).findElement(By.xpath("./div/div/div/p[1]"))));

                //add it to the list of actual descriptions.
                actualTreatmentDescriptions.add(shortDescription.getText());
            }
        }
        return actualTreatmentDescriptions;
    }

    public String getActualTreatmentDescription(Treatments treatments) {
        WebElement ul = driver.findElement(treatmentListLocator);
        List<WebElement> list1 = ul.findElements(By.tagName("li"));
        click(list1.get(treatments.getValue()), true);

        List<WebElement> treatmentDescriptionWrapper = webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(activeTreatmentDescriptionWrapperLocator));

        WebElement treatmentDescription = webDriverWait.until(ExpectedConditions.visibilityOf(treatmentDescriptionWrapper.getFirst()));
        return treatmentDescription.getText();
    }

    public List<String> getActualTreatmentTitles() {

        List<String> actualTreatments = new ArrayList<>();

        WebElement ul = driver.findElement(treatmentListLocator);
        List<WebElement> list1 = ul.findElements(By.tagName("li"));

        for (WebElement webElement : list1) {
            actualTreatments.add(webElement.getText());
        }
        return actualTreatments;
    }

    public boolean checkTreatmentImage(Treatments treatment) {

        WebElement ul = driver.findElement(treatmentListLocator);
        List<WebElement> list1 = ul.findElements(By.tagName("li"));
        click(list1.get(treatment.getValue()), true);

        List<WebElement> treatmentDescriptionWrapper = webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(activeTreatmentDescriptionWrapperLocator));

        WebElement treatmentDescription = webDriverWait.until(ExpectedConditions.visibilityOf(treatmentDescriptionWrapper.getFirst()));

        String imageSrc =  treatmentDescription.findElement(By.tagName("img")).getAttribute("src");

        ImageChecker imageChecker = new ImageChecker();
        log.info("Checking image with src: {}", imageSrc);
        if (imageChecker.isImageLoaded(imageSrc)) {
            log.info("Image {}status = 200 OK.", imageSrc);
            return true;
        } else {
            log.error("Image could not be loaded. There might be an issue.");
            return false;
        }
    }
    public void checkTreatmentImages() {

        List<WebElement> descriptions = getTreatmentDescriptionsContainer();

        //we locate this so we can click on the titles to load each description
        WebElement ul = driver.findElement(treatmentListLocator);
        List<WebElement> treatmentTitlesClickable = ul.findElements(By.tagName("li"));

        if (!descriptions.isEmpty()) {
            for (int i = 0; i < descriptions.size(); i++) {

                //we have to click so they can render.
                click(treatmentTitlesClickable.get(i), true);

                String imageSrc = descriptions.get(i).findElement(By.tagName("img")).getAttribute("src");
                log.info(imageSrc);
            }
        }
    }

    private List<WebElement> getTreatmentDescriptionsContainer() {
        WebElement descDisc = driver.findElement(By.cssSelector("div[class='sppb-tab-content sppb-nav-tabs-content'"));
        //get all description containers under it.
        return descDisc.findElements(By.cssSelector("[class^='sppb-tab-pane sppb-fade'"));
    }
}