package org.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.framework.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    WebDriver driver = DriverFactory.getInstance("chrome").getDriver();

    public final String baseUrl = "https://cosmeticabrasov.ro";
    private final int globalWaitDuration = 5;
    protected final WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(globalWaitDuration));

    protected static Logger log = LogManager.getLogger(BasePage.class);

    public void goToHomepage() {
        driver.get(baseUrl);
    }

    /**
     * Clicks on a web element with optional explicit wait.
     *
     * @param elementLocator The WebElement to be clicked.
     * @param wait If true, waits for the element to be clickable before clicking.
     *             If false, attempts to click immediately without waiting.
     */

    protected void click(By elementLocator, boolean wait) {

        if (wait) {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(elementLocator)).click();
        }
        else {
            driver.findElement(elementLocator).click();
        }
    }

    /**
     * Waits for the specified element then returns it.
     * @param elementLocator locator of element
     * @return element after waiting if not null
     */
    protected WebElement waitForElement(By elementLocator) {

        try {
            return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        } catch (TimeoutException e) {
            throw  new TimeoutException("Element not found by " + elementLocator + " after waiting " + globalWaitDuration + "seconds");
        }
    }
    /**
     * Clicks on a web element with optional explicit wait.
     *
     * @param element The WebElement to be clicked.
     * @param wait If true, waits 5 seconds for the element to be clickable before clicking.
     *             If false, attempts to click immediately without waiting.
     */
    protected void click(WebElement element, boolean wait) {

        if (wait) {
            try {
                webDriverWait.until(ExpectedConditions.elementToBeClickable(element)).click();
            } catch (Exception e) {
                log.error("Unable to click element " + element.toString() + " after waiting " + globalWaitDuration + "seconds.");
                throw e;
            }
        }

        else {
            try {
                element.click();
            } catch (Exception e) {
                log.error("Unable to click element " + element.toString() + " without waiting.");
                throw e;
            }
        }
    }


}
