package org.framework.actions;

import org.framework.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WaitClick implements ClickStrategy {

    protected final WebDriver driver;
    protected final WebDriverWait webDriverWait;

    public WaitClick(int waitDuration) {
        this.driver = DriverFactory.getInstance().getDriver();;
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(waitDuration));
    }

    @Override
    public void click(By locator) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    @Override
    public void click(WebElement element) { webDriverWait.until(ExpectedConditions.elementToBeClickable(element)).click(); }
}
