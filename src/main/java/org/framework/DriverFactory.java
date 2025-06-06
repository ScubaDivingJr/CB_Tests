package org.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.time.Duration;

public class DriverFactory {
/*    private static WebDriver driver;
    private static WebDriverWait wait;

    private DriverFactory() {
        //
    }
    public static WebDriver getChromeDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-search-engine-choice-screen");
            driver = new ChromeDriver(options);
        }
        return driver;
    }

    public static WebDriverWait getGetDriverWait() {
        if (wait == null) {
            wait =  new WebDriverWait(getChromeDriver(), Duration.ofSeconds(5));
        }
        return wait;
    }*/
    private DriverFactory() {
        //
    }

    private static DriverFactory instance;
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

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
           case "edge":
               tlDriver.set(new EdgeDriver());
           default:
               throw new IllegalArgumentException("Browser " + browser + "not supported.");
       }
    }

    public WebDriver getDriver() {
        return tlDriver.get();
    }
}