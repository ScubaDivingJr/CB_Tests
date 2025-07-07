package org.framework.driver;

import org.openqa.selenium.WebDriver;

public interface BrowserDriver {

    WebDriver getDriver(boolean headless);
}
