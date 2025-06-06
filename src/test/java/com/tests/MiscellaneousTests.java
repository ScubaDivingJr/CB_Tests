package com.tests;

import org.enums.Menus;
import org.framework.CommonVerifications;
import org.pages.*;
import org.testng.annotations.Test;

import javax.sound.midi.ShortMessage;

public class MiscellaneousTests extends BaseTestClass {

    Homepage homepage = new Homepage();
    CommonVerifications commonVerifications = new CommonVerifications();
    @Test
    void sliderButtons() {
        homepage.switchToSlide(1);
        homepage.clickCurrentSlideDetailsBtnTest();
        commonVerifications.verifyTextOnPage("Mezoterapie virtuala");
    }
}
