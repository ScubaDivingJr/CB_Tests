package org.framework.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface ClickStrategy {
    void click(By locator);
    void click(WebElement element);
}
