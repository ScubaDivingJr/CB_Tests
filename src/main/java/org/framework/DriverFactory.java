package org.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static DriverFactory instance;
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    private DriverFactory() {
        //
    }

    public static DriverFactory getInstance(String browser) {

        if (instance == null) {
            synchronized (WebDriver.class) {
                if (instance == null) {
                    instance = new DriverFactory();
                }
            }
        }

        if (tlDriver.get() == null) {
            instance.initializeDriver(browser);
        }
        return instance;
    }

    private void initializeDriver(String browser) {
       switch (browser) {
           case "chrome":
               //System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
               ChromeOptions options = new ChromeOptions();
               options.addArguments("start-maximized");
               options.addArguments("--remote-allow-origins=*");
               options.addArguments("--disable-search-engine-choice-screen");
               tlDriver.set(new ChromeDriver(options));
               break;
           case "firefox":
               tlDriver.set(new FirefoxDriver());
               break;
           case "edge":
               tlDriver.set(new EdgeDriver());
               break;
           default:
               throw new IllegalArgumentException("Browser " + browser + "not supported.");
       }
    }

    public void quitBrowser() {
        WebDriver driver = tlDriver.get();
        if (driver != null) {
            driver.quit();
            tlDriver.remove();
        }
    }

    public WebDriver getDriver() {
        return tlDriver.get();
    }
}