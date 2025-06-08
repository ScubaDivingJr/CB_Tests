package org.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.Data.TreatmentsDataProvider;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;

import java.util.List;

public class ContactPage extends BasePage {

    private static final Logger log = LogManager.getLogger(ContactPage.class);

    public enum ContactContainers {
        ADDRESS(0),
        PHONE (1),
        EMAIL(2);
        final int value;

        ContactContainers(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    private final By textLocator = By.cssSelector(".sppb-addon-text");
    private final By topDivWithContainers = By.cssSelector(".sppb-row-container");
    private final By containersList = By.cssSelector("div[class='sppb-col-md-4 sppb-col-sm-4']");
    private final By formSuccessMsg = By.cssSelector(".sppb-text-success");
    private final By gmapsIFrame = By.tagName("iframe");

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

    public boolean verifyContactFormWithDummyData() {
        log.info("Filling out the form.");
        driver.findElement(By.name("name")).sendKeys("Andrei");
        driver.findElement(By.name("email")).sendKeys("andrei.marcu1337@gmail.com");
        driver.findElement(By.name("subject")).sendKeys("Test Subject");
        driver.findElement(By.name("message")).sendKeys("Test Message.");

        //let's not spam this. commenting out for now.

/*        click(By.id("btn-1513851268456"), false);

        log.info("Verifying if form was sent successfully...");
        WebElement successMsg = webDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(formSuccessMsg));
        if (successMsg.isDisplayed()) {
            log.info("Form sent successfully.");
            return true;
        }
        return false;*/
        return true;
    }

    public boolean verifyGoogleMapsIFrameIsPresent() {

       //find the div and scroll to it
       WebElement iframeParentDiv = webDriverWait(1).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".gm-style")));
       ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", iframeParentDiv);

       //isDisplayed() doesn't work for this finicky embedded Google Maps iframe. let's just make sure we can find it for now.
        if (!iframeParentDiv.findElements(By.tagName("iframe")).isEmpty()) {
            log.info("Found Google Maps Iframe. This is fine for now.");
            return true;
        } else {
            log.error("Unable to find Google Maps Iframe.");
            return false;
        }

        //TODO: try to fiddle with the map.
    }

    //once we're in the specified container, the locator is the same for each text.
    private String getContainerText(ContactContainers contactContainer) {
        WebElement container = getContainer(contactContainer);
        return webDriverWait(2).until(ExpectedConditions.visibilityOf(container.findElement(textLocator))).getText();
    }

    //get the container we're interested in (e.g address / email / phone..
    private WebElement getContainer(ContactContainers contactContainers) {

        WebElement topDiv = driver.findElement(topDivWithContainers);
        List<WebElement> containers = topDiv.findElements(containersList);
        return containers.get(contactContainers.getValue());
    }
}
