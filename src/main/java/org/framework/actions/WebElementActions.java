package org.framework.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public interface WebElementActions {

    WebDriverWait webDriverWait(int duration);
    void click(By locator);
    void click(WebElement webElement);
    void clickWithWait(By locator, int waitDuration);
    void clickWithWait(WebElement element, int waitDuration);
    WebElement waitForVisibility(By locator, int waitDuration);
    WebElement waitForVisibility(WebElement webElement, int waitDuration);
    WebElement waitForClickability(By locator, int waitDuration);
    WebElement waitForPresence(By locator, int waitDuration);
    List<WebElement> waitForVisibilityOfAll(By locator, int waitDuration);
    List<WebElement> waitForPresenceOfAll(By locator, int waitDuration);
    void sendKeys(By locator, String keysToSend);
    void sendKeys(WebElement webElement, String keysToSend);
    void clickWithStrategy(By locator, ClickStrategy clickStrategy);
    void clickWithStrategy(WebElement element, ClickStrategy clickStrategy);
    void jsScrollIntoView(WebElement webElement);
}
