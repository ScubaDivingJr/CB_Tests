package org.framework.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WebElementActionsLogger implements WebElementActions {

    private static final Logger log = LogManager.getLogger(WebElementActionsLogger.class);
    private final WebElementActions webElementActions;

    public WebElementActionsLogger(WebElementActions webElementActions) {
       this.webElementActions = webElementActions;
    }

    @Override
    public void clickWithStrategy(By locator, ClickStrategy clickStrategy) {
        log.info("Clicking element with locator '{}' using '{}' strategy...", locator, clickStrategy);
        webElementActions.clickWithStrategy(locator, clickStrategy);
    }

    @Override
    public WebDriverWait webDriverWait(int duration) {
        log.info("Custom explicit wait applied for maximum {} seconds.", duration);
        return webElementActions.webDriverWait(duration);
    }

    @Override
    public void click(By locator) {
        log.info("Clicking element with locator '{}' - no waiting.", locator);
        webElementActions.click(locator);
    }

    @Override
    public void click(WebElement webElement) {
        log.info("Clicking element '{}'...", webElement);
        webElementActions.click(webElement);
    }

    @Override
    public WebElement waitForVisibility(By locator, int waitDuration) {
        log.info("Wait for visibility of element with locator '{}'...", locator);
        return webElementActions.waitForVisibility(locator, 2);
    }

    @Override
    public WebElement waitForClickability(By locator, int waitDuration) {
        return webElementActions.waitForClickability(locator, waitDuration);
    }

    @Override
    public WebElement waitForPresence(By locator, int waitDuration) {
        return webElementActions.waitForPresence(locator, waitDuration);
    }

    @Override
    public List<WebElement> waitForVisibilityOfAll(By locator, int waitDuration) {
        return webElementActions.waitForVisibilityOfAll(locator, waitDuration);
    }

    @Override
    public List<WebElement> waitForPresenceOfAll(By locator, int waitDuration) {
        return webElementActions.waitForPresenceOfAll(locator, waitDuration);
    }

    @Override
    public void sendKeys(By locator, String keysToSend) {
        webElementActions.sendKeys(locator, keysToSend);
    }

    @Override
    public void sendKeys(WebElement webElement, String keysToSend) {
        webElementActions.sendKeys(webElement, keysToSend);
    }

    @Override
    public void jsScrollIntoView(WebElement webElement) {
        log.info("Scrolling webelement '{}' into view...", webElement);
        webElementActions.jsScrollIntoView(webElement);
    }

    @Override
    public WebElement waitForVisibility(WebElement webElement, int waitDuration) {
        log.info("Waiting for visibility of element '{}'...", webElement);
        return webElementActions.waitForVisibility(webElement, waitDuration);
    }

    @Override
    public void clickWithWait(By locator, int waitDuration) {
        log.info("Clicking element with locator '{}' with a maximum wait of '{}' seconds...", locator, waitDuration);
        webElementActions.clickWithWait(locator,waitDuration);
    }

    @Override
    public void clickWithWait(WebElement element, int waitDuration) {
        log.info("Clicking element '{}' with a maximum wait of '{}' seconds...", element, waitDuration);
        webElementActions.clickWithWait(element, waitDuration);
    }
}
