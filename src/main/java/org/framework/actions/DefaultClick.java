package org.framework.actions;

import org.framework.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DefaultClick implements ClickStrategy {

    private final WebDriver driver;

    public DefaultClick() {
        this.driver = DriverFactory.getInstance().getDriver();
    }

    @Override
    public void click(By locator) {
        driver.findElement(locator).click();
    }

    @Override
    public void click(WebElement element) {
        element.click();
    }
}
