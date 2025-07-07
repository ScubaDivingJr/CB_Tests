package org.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.framework.actions.*;
import org.framework.driver.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected WebDriver driver;

    public static final String baseUrl = "https://cosmeticabrasov.ro";
    private static final Logger log = LogManager.getLogger(BasePage.class);
    protected WebElementActions webElementActions;
    protected ClickStrategy clickWithWait;
    protected ClickStrategy jsClick;

    public BasePage() {
        this.driver = DriverFactory.getInstance().getDriver();
        WebElementActions baseImpl = new WebElementActionsImpl();
        this.webElementActions = new WebElementActionsLogger(baseImpl);
        this.clickWithWait = new WaitClick(2);
        this.jsClick = new RetryWithJsFallbackClick();
    }

    public void goToHomepage() {
        driver.get(baseUrl);
    }

    protected WebDriverWait webDriverWait(int duration) {
        return new WebDriverWait(driver, Duration.ofSeconds(duration));
    }

    protected WebElement waitForVisibility(By locator, int customDuration) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(customDuration));

        try {
            log.info("Waiting for visibility of element with locator '{}'...", locator);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            log.error("'{}': element with locator '{}' not visible after waiting for '{}' seconds.",e.getClass().getSimpleName(), locator, customDuration);
            log.error(e);
            throw e;
        }
    }

    protected WebElement waitForVisibility(WebElement element, int customDuration) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(customDuration));

        try {
            log.info("Waiting for visibility of element...");
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            log.error("'{}': element not visible after waiting for '{}' seconds.", e.getClass().getSimpleName(), customDuration);
            log.error(e);
            throw e;
        }
    }

    protected WebElement waitForClickability(By locator, int customDuration) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(customDuration));

        try {
            log.info("Waiting for element with locator '{}' to be clickable...", locator);
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            log.error("'{}': element with locator '{}' clickable after waiting for '{}' seconds.",e.getClass().getSimpleName(), locator, customDuration);
            log.error(e);
            throw e;
        }
    }

    protected WebElement waitForClickability(WebElement element, int customDuration) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(customDuration));

        try {
            log.info("Waiting for element to be clickable...");
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            log.error("'{}': element not clickable after waiting for '{}' seconds.",e.getClass().getSimpleName(), customDuration);
            log.error(e);
            throw e;
        }
    }

    protected WebElement waitForPresence(By locator, int customDuration) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(customDuration));

        try {
            log.info("Waiting for presence of element with locator '{}'...", locator);
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            log.error("'{}': element with locator '{}' not visible after '{}' seconds.", e.getClass().getSimpleName(), locator, customDuration);
            log.error(e);
            throw e;
        }
    }

    protected List<WebElement> waitForPresenceOfAll(By locator, int customDuration) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(customDuration));

        try {
            log.info("Waiting for presence of elements with locator '{}'...", locator);
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            log.error("'{}': elements with locator '{}' not present after '{}' seconds.", e.getClass().getSimpleName(), locator, customDuration);
            log.error(e);
            throw e;
        }
    }

    protected List<WebElement> waitForVisibilityOfAll(By locator, int customDuration) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(customDuration));
        try {
            log.info("Waiting for visibility of elements with locator '{}'...", locator);
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            log.error("'{}': elements with locator '{}' not visible after '{}' seconds.", e.getClass().getSimpleName(), locator, customDuration);
            log.error(e);
            throw e;
        }
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
                log.error(e);
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
                log.error(e);
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
                log.error(e);
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
                log.error(e);
            }
        }
    }

    protected void sendKeys(By locator, String keysToSend) {
        driver.findElement(locator).sendKeys(keysToSend);
    }
}
