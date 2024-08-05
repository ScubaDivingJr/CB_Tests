package org.framework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.framework.DriverFactory.getChromeDriver;
import org.pages.ServicesPage;
import org.testng.Assert;

public class CommonVerifications {

    ServicesPage serv = new ServicesPage();
    protected WebDriver driver = getChromeDriver();

    public static CommonVerifications getCommonVerifications() { return new CommonVerifications();}
    public CommonVerifications verifyIsDisplayed(By element){
        Assert.assertTrue(driver.findElement(element).isDisplayed());
        return this;
    }
    public CommonVerifications verifyIsNotDisplayed(By element) {
        Assert.assertFalse(driver.findElement(element).isDisplayed());
        return this;
    }
    public CommonVerifications verifyTextOnPage(String text) {
        Assert.assertTrue(driver.getPageSource().contains(text));
        return this;
    }
    public CommonVerifications verifyUrl(String expectedUrl) {
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        return this;
    }

    public CommonVerifications verifyTreatmentDescription(Treatments treatment, String expectedTreatmentDescription){

        String actualTreatmentDescription = serv.getActualTreatmentDescription(treatment);
        Assert.assertTrue(actualTreatmentDescription.contains(expectedTreatmentDescription), "Description not found");

        return this;
    }
}