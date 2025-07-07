package org.framework.actions;

import org.framework.driver.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class WebElementActionsImpl implements WebElementActions {

    protected WebDriver driver;
    private final ClickStrategy defaultClickStrategy;
    private static final int standardWaitDuration = 2;

    public WebElementActionsImpl() {
        this.driver = DriverFactory.getInstance().getDriver();
        this.defaultClickStrategy = new DefaultClick(); //default
    }

    @Override
    public WebDriverWait webDriverWait(int duration) {
        return new WebDriverWait(driver, Duration.ofSeconds(duration));
    }

    @Override
    public void clickWithStrategy(By locator, ClickStrategy clickStrategy) {
        clickStrategy.click(locator);
    }

    @Override
    public void click(By locator) {
        defaultClickStrategy.click(locator);
    }

    @Override
    public void click(WebElement webElement) {
        defaultClickStrategy.click(webElement);
    }


    @Override
    public WebElement waitForVisibility(By locator, int waitDuration) {
        return webDriverWait(standardWaitDuration).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Override
    public WebElement waitForClickability(By locator, int waitDuration) {
        return webDriverWait(standardWaitDuration).until(ExpectedConditions.elementToBeClickable(locator));
    }

    @Override
    public WebElement waitForPresence(By locator, int waitDuration) {
        return webDriverWait(standardWaitDuration).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    @Override
    public List<WebElement> waitForVisibilityOfAll(By locator, int waitDuration) {
        return webDriverWait(standardWaitDuration).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    @Override
    public List<WebElement> waitForPresenceOfAll(By locator, int waitDuration) {
        return webDriverWait(standardWaitDuration).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    @Override
    public void sendKeys(By locator, String keysToSend) {
        driver.findElement(locator).sendKeys(keysToSend);
    }

    @Override
    public void sendKeys(WebElement webElement, String keysToSend) {
        webElement.sendKeys(keysToSend);
    }

    @Override
    public void jsScrollIntoView(WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    @Override
    public WebElement waitForVisibility(WebElement webElement, int waitDuration) {
        return webDriverWait(waitDuration).until(ExpectedConditions.visibilityOf(webElement));
    }

    @Override
    public void clickWithWait(By locator, int waitDuration) {
        ClickStrategy waitClickStrategy = new WaitClick(waitDuration);
        waitClickStrategy.click(locator);
    }

    @Override
    public void clickWithWait(WebElement element, int waitDuration) {
        ClickStrategy waitClickStrategy = new WaitClick(waitDuration);
        waitClickStrategy.click(element);
    }
}
