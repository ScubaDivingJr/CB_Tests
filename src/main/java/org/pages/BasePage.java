package org.pages;

import org.framework.actions.*;
import org.framework.driver.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BasePage {

    public static final String baseUrl = "https://cosmeticabrasov.ro";
    protected WebDriver driver;
    protected WebElementActions webElementActions;
    protected ClickStrategy clickWithWait;
    protected ClickStrategy jsClick;

    private final int standardWaitDuration = 2;

    public BasePage() {
        this.driver = DriverFactory.getInstance().getDriver();
        WebElementActions baseImpl = new WebElementActionsImpl();
        this.webElementActions = new WebElementActionsLogger(baseImpl);
        //this.clickWithWait = new WaitClick(standardWaitDuration);
        this.jsClick = new RetryJsFallbackClick();
    }

    protected WebDriverWait webDriverWait(int duration) {
        return new WebDriverWait(driver, Duration.ofSeconds(duration));
    }
}
