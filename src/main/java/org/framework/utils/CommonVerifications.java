package org.framework;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.framework.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.pages.BasePage;
import org.pages.ServicesPage;
import org.testng.Assert;

public class CommonVerifications extends BasePage {

    private static final Logger log = LogManager.getLogger(CommonVerifications.class);

    WebDriver driver = DriverFactory.getInstance().getDriver();

    public static CommonVerifications getCommonVerifications() { return new CommonVerifications();}
    public CommonVerifications verifyIsDisplayed(By element){
        Assert.assertTrue(driver.findElement(element).isDisplayed());
        return this;
    }
    public CommonVerifications verifyIsNotDisplayed(By element) {
        Assert.assertFalse(driver.findElement(element).isDisplayed());
        return this;
    }
    public CommonVerifications verifyTextOnPage(String text) {
        try {
            Assert.assertTrue(driver.getPageSource().contains(text), "Unable to find text " + text + " on page " + driver.getCurrentUrl() + ".");
            log.info("Verify text '{}' on page successful.", text);
        } catch(AssertionError e) {
            log.error("Cloud not find text '{}' on page.", text);
            log.error(e.getMessage());
            throw e;
        }
        return this;
    }
    public CommonVerifications verifyUrl(String expectedUrl) {

        if (driver != null) {
            try {
                Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "URL " + expectedUrl + " did not match " + driver.getCurrentUrl() + ".");
                log.info("URL '{}' matched current page URL.", expectedUrl);
            } catch(AssertionError e) {
                log.error("Expected URL ('{}') did not match current URL ('{}').", expectedUrl, driver.getCurrentUrl());
                log.error(e.getMessage());
                throw e;
            }
        } else {
            log.error("Driver was null in verifyUrl(). We shouldn't be here.");
            Assert.fail();
        }
        return this;
    }
}