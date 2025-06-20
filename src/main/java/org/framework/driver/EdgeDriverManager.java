package org.framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriverManager implements IBrowserDriver {
    @Override
    public WebDriver getDriver(boolean headless) {
        WebDriverManager.edgedriver()
                .clearResolutionCache()
                .setup();

        if (headless) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--headless");
            options.addArguments("--window-size=1280,800");
            return new EdgeDriver(options);
        }

        return new EdgeDriver();
    }
}
