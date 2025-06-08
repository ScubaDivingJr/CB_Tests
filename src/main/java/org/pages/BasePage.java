package org.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.framework.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public static final String baseUrl = "https://cosmeticabrasov.ro";
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

            log.info("Trying to click element with locator '{}'...", elementLocator);
            try {
                WebElement element = webDriverWait(5).until(ExpectedConditions.elementToBeClickable(elementLocator));
                String elementText = element.getText();
                element.click();
                if (elementText.isBlank()) {
                    log.info("Successfully clicked element with locator '{}' (with wait - element has no visible text).", elementLocator);
                } else {
                    log.info("Successfully clicked '{}' (with wait).", elementText);
                }
            } catch (TimeoutException | NoSuchElementException | ElementClickInterceptedException | StaleElementReferenceException  e) {
                log.error("Unable to click element with locator '{}' after waiting for 5 seconds.", elementLocator);
                log.error(e.getMessage());
            }
        }
        else {
            log.info("Trying to click element with locator '{}' without waiting.", elementLocator);
            try {
                WebElement element = driver.findElement(elementLocator);
                String elementText = element.getText();
                element.click();
                log.info("Successfully clicked '{}' (without wait).", elementText);
            } catch (TimeoutException | NoSuchElementException | ElementClickInterceptedException | StaleElementReferenceException  e) {
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
            log.info("Trying to click element '{}' (with wait)...", element);
            try {
                webDriverWait(5).until(ExpectedConditions.elementToBeClickable(element));
                String elementText = element.getText();
                element.click();
                log.info("Successfully clicked '{}' (with wait).", elementText);
            } catch (TimeoutException | ElementClickInterceptedException | StaleElementReferenceException  e) {
                log.error("Unable to click element '{}' after waiting {} seconds.", element, 5);
                log.error(e.getMessage());
            }
        }
        else {
            log.info("Trying to click element '{}' (without wait)...", element);
            try {
                String elementText = element.getText();
                element.click();
                log.info("Successfully clicked '{}'.", elementText);
            } catch (TimeoutException | ElementClickInterceptedException | StaleElementReferenceException  e) {
                log.error("Unable to click element {} without waiting.", element);
                log.error(e.getMessage());
            }
        }
    }
}
