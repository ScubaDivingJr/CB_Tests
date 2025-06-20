package org.framework.driver;
import org.enums.BrowserTypes;
import org.openqa.selenium.WebDriver;

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

    public void initializeDriver(BrowserTypes browser, boolean headless) {

        if (tlDriver.get() != null) {
            return;
        }

       switch (browser) {
           case CHROME -> tlDriver.set(new ChromeDriverManager().getDriver(headless));
           case CHROME_MOBILE -> tlDriver.set(new ChromeMobileDriverManager().getDriver(headless));
           case FIREFOX ->  tlDriver.set(new FirefoxDriverManager().getDriver(headless));
           case EDGE ->  tlDriver.set(new EdgeDriverManager().getDriver(headless));
           default -> throw new IllegalArgumentException("Valid enum value but initialization not set in DriverFactory?");
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