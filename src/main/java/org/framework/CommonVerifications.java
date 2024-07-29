package org.framework;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.framework.DriverFactory.getChromeDriver;
import static org.framework.DriverFactory.getGetDriverWait;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


public class CommonVerifications {
    protected WebDriver driver = getChromeDriver();

    public static CommonVerifications getCommonVerifications() { return new CommonVerifications();}
    public CommonVerifications verifyIsDisplayed(By element){
        assertTrue(driver.findElement(element).isDisplayed());
        return this;
    }
    public CommonVerifications verifyIsNotDisplayed(By element) {
        assertFalse(driver.findElement(element).isDisplayed());
        return this;
    }
    public CommonVerifications verifyTextOnPage(String text) {
        assertTrue(driver.getPageSource().contains(text));
        return this;
    }
    public CommonVerifications verifyUrl(String expectedUrl) {
        assertEquals(driver.getCurrentUrl(), expectedUrl);
        return this;
    }
}