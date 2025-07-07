package org.framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager implements BrowserDriver{
    @Override
    public WebDriver getDriver(boolean headless) {

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
        return new ChromeDriver(options);
    }
}
