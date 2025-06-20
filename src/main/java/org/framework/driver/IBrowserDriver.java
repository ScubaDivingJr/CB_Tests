package org.framework.driver;

import org.openqa.selenium.WebDriver;

public interface IBrowserDriver {

    WebDriver getDriver(boolean headless);
}
