package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import static org.framework.DriverFactory.getChromeDriver;
import static org.framework.DriverFactory.getGetDriverWait;
import static org.framework.TestUtils.explicitWait;

public class Homepage {
    private final WebDriver driver = getChromeDriver();
    private final WebDriverWait wait = getGetDriverWait();

    public void clickProgramariOnline() {
        driver.findElement(By.cssSelector("a[href='http://cosmeticabrasov.ro/programari-online.html']")).click();
    }

    public void clickMaiMulteDetalii() {
        driver.findElement(By.id("btn-1513331494768")).click();
    }

    public void clickProgrameazate() {
        driver.findElement(By.id("btn-1515652264954")).click();
    }

    public void clickSlideshowDetails(int slide) {

        if (slide == 1) {
                explicitWait();
                WebElement s = driver.findElement(By.xpath("//*[@id=\"slide-fullwidth\"]/div[1]/div/div[5]/div/div/div/div/div/a"));
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", s);
        }

        if (slide == 2) {

            explicitWait();
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