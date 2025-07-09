package org.framework.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.framework.driver.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RetryJsFallbackClick implements ClickStrategy {

    private final WebDriverWait webDriverWait;
    private final WebDriver driver;
    private static final Logger log = LogManager.getLogger(RetryJsFallbackClick.class);

    public RetryJsFallbackClick() {
        this.driver = DriverFactory.getInstance().getDriver();
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    @Override
    public void click(By locator) {
        int maxAttempts = 2;
        int attempts = 0;

        while (attempts < maxAttempts) {
            try {
                webDriverWait.until(ExpectedConditions.elementToBeClickable(locator)).click();
                return; //success
            } catch (NoSuchElementException | StaleElementReferenceException | ElementClickInterceptedException e) {
                attempts++;
                if (attempts == maxAttempts) {
                    try  {
                        WebElement element = driver.findElement(locator);
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                        return; //success
                    } catch (Exception ex) {
                        log.error("Unable to click element with jsClick.");
                        log.error(ex);
                        throw ex;
                    }
                }
            }
        }
    }

    @Override
    public void click(WebElement element) {
        int maxAttempts = 2;
        int attempts = 0;

        while (attempts < maxAttempts) {
            try {
                webDriverWait.until(ExpectedConditions.elementToBeClickable(element)).click();
                return; //success
            } catch (NoSuchElementException | StaleElementReferenceException | ElementClickInterceptedException e) {
                attempts++;
                if (attempts == maxAttempts) {
                    try  {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                        return; //success
                    } catch (Exception ex) {
                        log.error("Unable to click element with jsClick.");
                        log.error(ex);
                        throw ex;
                    }
                }
            }
        }
    }
}
