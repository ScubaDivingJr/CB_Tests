package org.framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;

public class ChromeMobileDriverManager implements BrowserDriver {

    @Override
    public WebDriver getDriver(boolean headless) {

        WebDriverManager.chromedriver().clearResolutionCache().setup();

        ChromeOptions options = new ChromeOptions();

        if (ConfigManager.isMobile()) {
            options.setExperimentalOption("mobileEmulation", Map.of("deviceName", "iPhone X"));
        }

        if (ConfigManager.isHeadless()) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=375,812"); // iPhone X resolution
        }

        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-search-engine-choice-screen");

        return new ChromeDriver();
    }
}
