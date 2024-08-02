package org.pages;

import org.framework.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import static org.framework.DriverFactory.getChromeDriver;
import static org.framework.DriverFactory.getGetDriverWait;

public class Homepage {
    private final WebDriver driver = getChromeDriver();
    private final WebDriverWait wait = getGetDriverWait();

    TestUtils testUtils = new TestUtils();

    public void clickSlideshowDetails(int slide) {

        if (slide == 1) {
                testUtils.explicitWait();
                WebElement s = driver.findElement(By.xpath("//*[@id=\"slide-fullwidth\"]/div[1]/div/div[5]/div/div/div/div/div/a"));
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", s);
        }

        if (slide == 2) {

            testUtils.explicitWait();
            driver.findElement(By.cssSelector(".sppbSlideNext")).click();
            WebElement s2 = driver.findElement(By.xpath("//*[@id=\"slide-fullwidth\"]/div[1]/div/div[5]/div/div/div/div/div/a"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", s2);
        }
    }

    public static By Slide(){
        return By.id("slide-fullwidth");
    }
    public static By Logo() {
        return By.id("sp-logo");
    }
    public static By HomeButtonAndActive() {
        return By.cssSelector("li[class='sp-menu-item current-item active']");
    }
}