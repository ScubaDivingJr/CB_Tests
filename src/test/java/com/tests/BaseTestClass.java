package com.tests;


import org.framework.Screenshotter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import static org.framework.DriverFactory.getChromeDriver;
import static org.framework.DriverFactory.getGetDriverWait;

@ExtendWith(Screenshotter.class)
public class BaseTestClass {
    static WebDriver driver;
    static WebDriverWait wait;
    public static final String BASE_URL = "https://cosmeticabrasov.ro";
    @BeforeSuite
    public void startBrowser() {
        driver = getChromeDriver();
        wait = getGetDriverWait();

        //handle search engine pop-up
        //if (driver.getPageSource().contains("Choose your search engine")) {
        //}
        driver.get(BASE_URL);
        WebElement googleRadioButton = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("1"))));
        googleRadioButton.click();
        driver.findElement(By.id("actionButton")).click();
    }
    @BeforeAll
    public static void goToHome() {
        driver = getChromeDriver();
        driver.get(BASE_URL);
    }
    @AfterSuite
    public static void cleanUp(){
     driver.quit();
    }
}