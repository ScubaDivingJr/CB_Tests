package com.tests;

import org.framework.CommonVerifications;
import org.junit.jupiter.api.Test;
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
