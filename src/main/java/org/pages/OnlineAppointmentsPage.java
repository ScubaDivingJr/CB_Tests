package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.framework.DriverFactory.getChromeDriver;

public class OnlineAppointmentsPage {

    WebDriver driver = getChromeDriver();

    private OnlineAppointmentsPage() {
        //
    }

    public static OnlineAppointmentsPage getOnlineAppointmentsPage() {
        return new OnlineAppointmentsPage();
    }

    public OnlineAppointmentsPage enterName(String name) {
        driver.findElement(By.id("nume")).sendKeys(name);
        return this;
    }

    public OnlineAppointmentsPage enterPhoneNumber(String phone) {
        driver.findElement(By.id("telefon")).sendKeys(phone);
        return this;
    }

    public OnlineAppointmentsPage enterEmail(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
        return this;
    }

    public OnlineAppointmentsPage enterPreferredDate(String date) {
        driver.findElement(By.id("data_preferata")).sendKeys(date);
        return this;
    }

    public OnlineAppointmentsPage enterTimeOfDay(String intervalOrar) {
        WebElement orar = driver.findElement(By.id("interval_orar"));
        Select s = new Select(orar);

        if (intervalOrar.equals("dimineata")) {
            s.selectByVisibleText("Dimineata");
        }
        if (intervalOrar.equals("la pranz")) {
            s.selectByVisibleText("La pranz");
        }
        if (intervalOrar.equals("seara")) {
            s.selectByVisibleText("Seara");
        }
        return this;
    }

    public OnlineAppointmentsPage additionalDetails(String details) {
        driver.findElement(By.id("serviciu_dorit_si_detalii_suplimentare")).sendKeys(details);
        return this;
    }

    public void clickSubmit() {
        driver.findElement(By.id("button8")).click();
    }

    public static By systemMessage(){
        return By.id("system-message");
    }

    public static By successMessage() {
        return By.cssSelector("[class='ui message success']");
    }
}