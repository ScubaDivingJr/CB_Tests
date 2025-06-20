package org.framework.driver;

import org.enums.BrowserTypes;

public class ConfigManager {

    public static BrowserTypes getBrowser(){
        String browser = System.getProperty("browser", "chrome").toUpperCase();
        return BrowserTypes.valueOf(browser);
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(System.getProperty("headless", "true"));
    }

    public static boolean isMobile() {
        return Boolean.parseBoolean(System.getProperty("mobile", "false"));
    }
}
