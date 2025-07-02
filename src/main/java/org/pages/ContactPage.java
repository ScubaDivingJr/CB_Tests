package org.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.enums.ContactContainers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.Data.TreatmentsDataProvider;
import java.util.List;

public class ContactPage extends BasePage {

    private static final Logger log = LogManager.getLogger(ContactPage.class);

    private final By byTextLocator = By.cssSelector(".sppb-addon-text");
    private final By byTopDivWithContainers = By.cssSelector(".sppb-row-container");
    private final By byContainersList = By.cssSelector("div[class='sppb-col-md-4 sppb-col-sm-4']");
    private final By byFormSuccessMsg = By.cssSelector(".sppb-text-success");
    private final By byGmapsIFrame = By.tagName("iframe");
    private final By bySubmitBtnLocator = By.id("btn-1513851268456");
    private final By nameFieldLocator = By.name("name");
    private final By emailFieldLocator = By.name("email");
    private final By subjectFieldLocator = By.name("subject");
    private final By messageFieldLocator = By.name("message");

    public boolean verifyAddressContent() {
        String expectedAddressContent = TreatmentsDataProvider.expectedContactPageContent().get(ContactContainers.ADDRESS);
        String actualAddressContent = getContainerText(ContactContainers.ADDRESS);
        return actualAddressContent.equals(expectedAddressContent);
    }

    public boolean verifyPhoneContent() {
        String expectedPhoneContent = TreatmentsDataProvider.expectedContactPageContent().get(ContactContainers.PHONE);
        String actualPhoneContent =  getContainerText(ContactContainers.PHONE);
        return actualPhoneContent.equals(expectedPhoneContent);
    }

    public boolean verifyEmailContent() {
        String expectedEmailContent = TreatmentsDataProvider.expectedContactPageContent().get(ContactContainers.EMAIL);
        String actualEmailContent =  getContainerText(ContactContainers.EMAIL);
        return actualEmailContent.equals(expectedEmailContent);
    }

    // temporary
    public void fillContactFormWithDummyData() {
        log.info("Filling out the form.");

        sendKeys(nameFieldLocator, "Andrei");
        sendKeys(emailFieldLocator, "andrei.marcu1337@gmail.com");
        sendKeys(subjectFieldLocator, "TestSubject");
        sendKeys(messageFieldLocator, "Test Message");
    }

    public boolean sendFormAndVerifySent() {
        click(bySubmitBtnLocator, false);
        log.info("Verifying if form was sent successfully...");
        return waitForVisibility(byFormSuccessMsg, 10).isDisplayed();
    }

    public boolean verifyGoogleMapsIFrameIsPresent() {

       // find the div and scroll to it
       WebElement iframeParentDiv = waitForVisibility(By.cssSelector(".gm-style"), 5);
       ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", iframeParentDiv);

       // isDisplayed() doesn't work for this finicky embedded Google Maps iframe. let's just make sure we can find it for now.
        if (!iframeParentDiv.findElements(byGmapsIFrame).isEmpty()) {
            log.info("Found Google Maps Iframe. This is fine for now.");
            return true;
        } else {
            log.error("Unable to find Google Maps Iframe.");
            return false;
        }

        //TODO: try to fiddle with the map.
    }

    // once we're in the specified container, the locator is the same for each text.
    private String getContainerText(ContactContainers contactContainer) {
        WebElement container = getContainer(contactContainer);
        return waitForVisibility(container.findElement(byTextLocator), 3).getText();
    }

    // get the container we're interested in (e.g address / email / phone)...
    private WebElement getContainer(ContactContainers contactContainers) {

        WebElement topDiv = driver.findElement(byTopDivWithContainers);
        List<WebElement> containers = topDiv.findElements(byContainersList);
        return containers.get(contactContainers.getValue());
    }
}
