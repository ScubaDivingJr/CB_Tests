package org.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.enums.Treatments;
import org.framework.utils.ImageChecker;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServicesPage extends BasePage {

    private static final Logger log = LogManager.getLogger(ServicesPage.class);

    private final By treatmentListLocator = By.cssSelector("ul[class='sppb-nav sppb-nav-tabs']");
    private final By activeTreatmentDescriptionWrapperLocator = By.cssSelector("div[class='sppb-tab-pane sppb-fade active in']");
    private final By imageHolderLocator = By.cssSelector(".sppb-image-holder");
    private final By treatmentOuterContainerLocator = By.cssSelector("div[class='sppb-tab-content sppb-nav-tabs-content']");
    private final By individualDescriptionContainerLocator = By.cssSelector("[class^='sppb-tab-pane sppb-fade']");

    public void clickTreatment(@NotNull Treatments treatment) {
        List<WebElement> li = getTreatmentContainerlist();
        webElementActions.clickWithWait(li.get(treatment.getValue()), 2);
    }

    public List<String> getActualTreatmentDescriptions() {

        List<String> actualTreatmentDescriptions = new ArrayList<>();
        List<WebElement> descriptions = getTreatmentDescriptionsContainer();

        // We locate this so we can click on the titles to load each description
        List<WebElement> treatmentTitlesClickable = getTreatmentContainerlist();

        if (descriptions.size() == Treatments.values().length) {
            log.info("Size of descriptions String list is:{}.", descriptions.size() + " This is what we expect given our Treatments enum values.");
        } else {
            log.warn("The size of description String list was '{}'. Either more were added some or we have a problem.", Treatments.values().length);
        }

        if (!descriptions.isEmpty()) {
            for (int i = 0; i < descriptions.size(); i++) {

                // We have to click so they can render.
                webElementActions.clickWithWait(treatmentTitlesClickable.get(i), 2);

                //each description lives 3 divs under descriptions List. We have to wait for them to load when we click each one. We care about the first paragraph only.
                WebElement shortDescription = webElementActions.waitForVisibility(descriptions.get(i).findElement(By.xpath("./div/div/div/p[1]")), 3);

                // Add it to the list of actual descriptions.
                actualTreatmentDescriptions.add(shortDescription.getText());
            }
        }
        return actualTreatmentDescriptions;
    }

    public String getActualTreatmentDescription(@NotNull Treatments treatments) {

        List<WebElement> li = getTreatmentContainerlist();
        webElementActions.clickWithWait(li.get(treatments.getValue()), 2);

        List<WebElement> treatmentDescriptionWrapper = webElementActions.waitForVisibilityOfAll(activeTreatmentDescriptionWrapperLocator, 3);

        WebElement treatmentDescription = webElementActions.waitForVisibility(treatmentDescriptionWrapper.getFirst(), 3);
        return treatmentDescription.getText();
    }

    public List<String> getActualTreatmentTitles() {

        List<String> actualTreatments = new ArrayList<>();
        List<WebElement> li = getTreatmentContainerlist();

        for (WebElement webElement : li) {
            actualTreatments.add(webElement.getText());
        }
        return actualTreatments;
    }

    public boolean checkTreatmentImage(@NotNull Treatments treatment) {

        List<WebElement> li = getTreatmentContainerlist();
        webElementActions.clickWithWait(li.get(treatment.getValue()), 2);

        List<WebElement> treatmentDescriptionWrapper = webElementActions.waitForPresenceOfAll(activeTreatmentDescriptionWrapperLocator, 2);

        WebElement treatmentDescription = webElementActions.waitForVisibility(treatmentDescriptionWrapper.getFirst(), 2);
        String imageSrc = treatmentDescription.findElement(By.tagName("img")).getAttribute("src");

        ImageChecker imageChecker = new ImageChecker();
        return imageChecker.isImageLoaded(imageSrc);
    }

    public boolean checkTreatmentImages() {

        List<WebElement> descriptions = getTreatmentDescriptionsContainer();

        // We locate this so we can click on the titles to load each description
        List<WebElement> treatmentTitlesClickable = getTreatmentContainerlist();

        ImageChecker imageChecker = new ImageChecker();
        if (!descriptions.isEmpty()) {
            for (int i = 0; i < descriptions.size(); i++) {

                // we have to click so they can render.
                webElementActions.clickWithWait(treatmentTitlesClickable.get(i), 2);
                WebElement img = descriptions.get(i).findElement(By.tagName("img"));

                String imageSrc = img != null ? img.getAttribute("src") : "";

                if (imageSrc == null || imageSrc.isEmpty() || !imageChecker.isImageLoaded(imageSrc)) {
                    log.warn("Image for treatment index {} is not loaded properly.", i);
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean checkFacialTreatmentImages() {

        List<String> webElementIds = List.of("section-id-1517131626142", "section-id-1517131626144", "section-id-1517131626156");
        ImageChecker imageChecker = new ImageChecker();

        for (String webElementId : webElementIds) {

            WebElement section = driver.findElement(By.id(webElementIds.get(webElementIds.indexOf(webElementId))));
            WebElement imageHolderElement = section.findElement(imageHolderLocator);

            //getting the url in brackets inside the style attribute
            String imageSrc = imageHolderElement.getAttribute("style");
            Pattern pattern = Pattern.compile("url\\(['\"]?([^'\")]+)['\"]?\\)");
            Matcher matcher = pattern.matcher(imageSrc);

            if (matcher.find() && !imageChecker.isImageLoaded(baseUrl + matcher.group(1))) {
                return false;
            }
        }
        return true;
    }

    private List<WebElement> getTreatmentDescriptionsContainer() {
        WebElement outerContainer = driver.findElement(treatmentOuterContainerLocator);
        // Get all description containers under it.
        return outerContainer.findElements(individualDescriptionContainerLocator);
    }

    private List<WebElement> getTreatmentContainerlist() {
        WebElement ul = driver.findElement(treatmentListLocator);
        return ul.findElements(By.tagName("li"));
    }
}