package org.framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager implements IBrowserDriver {
    @Override
    public WebDriver getDriver(boolean headless) {
        WebDriverManager.firefoxdriver()
                .clearResolutionCache()
                .setup();

        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("--headless");
            options.addArguments("--width=1280");
            options.addArguments("--height=800");
            return new FirefoxDriver(options);
        } else {
            WebDriver driver = new FirefoxDriver(options);
            driver.manage().window().maximize();
            return driver;
        }
    }
}
