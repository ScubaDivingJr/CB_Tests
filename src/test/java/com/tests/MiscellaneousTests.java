package com.tests;

import org.framework.CommonVerifications;
import org.framework.Menus;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.*;

public class MiscellaneousTests extends BaseTestClass {

    Header header = new Header();
    Homepage home = new Homepage();
    CommonVerifications commonVerifications = new CommonVerifications();
    @Test
    void sliderButtons() {
        header.clickHome();
        home.clickSlideshowDetails(2);
        commonVerifications.verifyTextOnPage("Mezoterapie virtuala");
    }
}
