package org.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.framework.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public final String baseUrl = "https://cosmeticabrasov.ro";
    private static final Logger log = LogManager.getLogger(BasePage.class);

    WebDriver driver = DriverFactory.getInstance("chrome").getDriver();

    public void goToHomepage() {
        driver.get(baseUrl);
    }

    protected WebDriverWait webDriverWait(int duration) {
        return new WebDriverWait(driver, Duration.ofSeconds(duration));
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
            try {
                log.info("Trying to click element with locator: '{}' with wait.", elementLocator);
                webDriverWait(5).until(ExpectedConditions.elementToBeClickable(elementLocator)).click();
            } catch (TimeoutException | NoSuchElementException | ElementClickInterceptedException e) {
                log.error("Unable to click element with locator '{}' after waiting for 5 seconds.", elementLocator);
                log.error(e.getMessage());
            }
        }
        else {
            try {
                log.info("Cliking element with locator: '{}' without wait.", elementLocator);
                driver.findElement(elementLocator).click();
            } catch (TimeoutException | NoSuchElementException | ElementClickInterceptedException e) {
                log.error("Unable to click element with locator: '{}' without waiting.", elementLocator);
                log.error(e.getMessage());
            }
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
                log.info("Clicking element '{}' with wait", element);
                webDriverWait(5).until(ExpectedConditions.elementToBeClickable(element)).click();
            } catch (TimeoutException | NoSuchElementException | ElementClickInterceptedException e) {
                log.error("Unable to click element {} after waiting " + 5 + "seconds.", element.toString());
                log.error(e.getMessage());
            }
        }
        else {
            try {
                log.info("Trying to click element {} without waiting.", element);
                element.click();
            } catch (TimeoutException | NoSuchElementException | ElementClickInterceptedException e) {
                log.error("Unable to click element {} without waiting.", element.toString());
                throw e;
            }
        }
    }
}
