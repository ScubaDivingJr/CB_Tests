package org.framework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.framework.DriverFactory.getChromeDriver;
import static org.junit.jupiter.api.Assertions.*;
import org.pages.ServicesPage;

public class CommonVerifications {

    ServicesPage serv = new ServicesPage();
    protected WebDriver driver = getChromeDriver();

    public static CommonVerifications getCommonVerifications() { return new CommonVerifications();}
    public CommonVerifications verifyIsDisplayed(By element){
        assertTrue(driver.findElement(element).isDisplayed());
        return this;
    }
    public CommonVerifications verifyIsNotDisplayed(By element) {
        assertFalse(driver.findElement(element).isDisplayed());
        return this;
    }
    public CommonVerifications verifyTextOnPage(String text) {
        assertTrue(driver.getPageSource().contains(text));
        return this;
    }
    public CommonVerifications verifyUrl(String expectedUrl) {
        assertEquals(driver.getCurrentUrl(), expectedUrl);
        return this;
    }

    public CommonVerifications verifyTreatmentDescription(Treatments treatment, String expectedTreatmentDescription){

        String actualTreatmentDescription = serv.getActualTreatmentDescription(treatment);
        assertTrue(actualTreatmentDescription.contains(expectedTreatmentDescription), "Description not found");

        return this;
    }
}