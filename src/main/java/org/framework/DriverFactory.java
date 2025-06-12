package org.framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
public class DriverFactory {

    private static DriverFactory instance;
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static boolean headless = false;

    private DriverFactory() {
        //
    }

    public static DriverFactory getInstance() {

        if (instance == null) {
            synchronized (DriverFactory.class) {
                if (instance == null) {
                    instance = new DriverFactory();
                }
            }
        }
        return instance;
    }

    public void initializeDriver(String browser) {

        if (tlDriver.get() != null) {
            return;
        }

       switch (browser) {
           case "chrome":
               WebDriverManager.chromedriver()
                       .clearResolutionCache()
                       .setup();// setup compatible chromedriver.
               ChromeOptions options = new ChromeOptions();
               if (headless) {
                   options.addArguments("--headless=new");
                   options.addArguments("--no-sandbox");
                   options.addArguments("--disable-gpu");
                   options.addArguments("--window-size=1280,800");
               }
               options.addArguments("start-maximized");
               options.addArguments("--remote-allow-origins=*");
               options.addArguments("--disable-search-engine-choice-screen");
               tlDriver.set(new ChromeDriver(options));
               break;
           case "firefox":
               WebDriverManager.firefoxdriver();
               tlDriver.set(new FirefoxDriver());
               break;
           case "edge":
               WebDriverManager.edgedriver();
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