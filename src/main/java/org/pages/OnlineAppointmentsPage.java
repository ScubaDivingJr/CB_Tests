package org.pages;
import org.enums.OnlineAppointmentScheduleOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OnlineAppointmentsPage extends BasePage {

    private By nameLocator = By.id ("nume");
    private By phoneNumberLocator = By.id("telefon");
    private By emailLocator = By.id("email");
    private By dateLocator = By.id("data_preferata");
    private By scheduleLocator = By.id("interval_orar");
    private By servicesAndDetailsLocator = By.id("serviciu_dorit_si_detalii_suplimentare");
    private By submitBtnLocator = By.id("button8");
    private By failMessageContainerLocator = By.id("system-message");
    private By successMessageContainerLocator = By.cssSelector(".ui message success");

    public OnlineAppointmentsPage enterName(String name) {
        driver.findElement(nameLocator).sendKeys(name);
        return this;
    }

    public OnlineAppointmentsPage enterPhoneNumber(String phone) {
        driver.findElement(phoneNumberLocator).sendKeys(phone);
        return this;
    }

    public OnlineAppointmentsPage enterEmail(String email) {
        driver.findElement(emailLocator).sendKeys(email);
        return this;
    }

    public OnlineAppointmentsPage enterPreferredDate(LocalDate date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(formatter);
        driver.findElement(dateLocator).sendKeys(formattedDate);

        return this;
    }
    //maybe one prefers magic strings of type dd/mm/yyyy
    public OnlineAppointmentsPage enterPreferredDate(String date) {
        driver.findElement(dateLocator).sendKeys(date);
        return this;
    }

    public OnlineAppointmentsPage enterSchedule(OnlineAppointmentScheduleOptions scheduleOptions) {

        WebElement scheduleDropDown = driver.findElement(By.id("interval_orar"));
        Select select = new Select(scheduleDropDown);

        switch (scheduleOptions) {
            case DIMINEATA -> select.selectByVisibleText(OnlineAppointmentScheduleOptions.DIMINEATA.getValue());
            case LA_PRANZ -> select.selectByVisibleText(OnlineAppointmentScheduleOptions.LA_PRANZ.getValue());
            case SEARA -> select.selectByVisibleText(OnlineAppointmentScheduleOptions.SEARA.getValue());
        }

        return this;
    }

    public OnlineAppointmentsPage enterAdditionalDetails(String details) {

        //any string works here
        driver.findElement(servicesAndDetailsLocator).sendKeys(details);
        return this;
    }

    public void clickSubmit() {
        click(submitBtnLocator, false);
    }

    public boolean verifyMessageAfterSubmit() {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            customWait.until((WebDriver webdriver) ->
                    !webdriver.findElements(failMessageContainerLocator).isEmpty() ||
                    !webdriver.findElements(successMessageContainerLocator).isEmpty()
            );
        } catch (Exception e) {
            log.error("Cannot find any message after waiting for 10 seconds.");
        }

        boolean successDisplayed = !driver.findElements(successMessageContainerLocator).isEmpty();
        boolean failDisplayed = !driver.findElements(failMessageContainerLocator).isEmpty();

        if (failDisplayed || !successDisplayed) {
            log.error("Could not send appoinment. Fail message displayed or success message not displayed.");
        }
        return successDisplayed && !failDisplayed;


    }

    public void sendCompleteOnlineAppointmentWithDummyData() {
        enterName("Andrei")
                .enterPhoneNumber("0726123456")
                .enterEmail("andrei.marcu1337@gmail.com")
                .enterPreferredDate("08/09/2025")
                .enterSchedule(OnlineAppointmentScheduleOptions.DIMINEATA)
                .enterAdditionalDetails("asdfg")
                .clickSubmit();
    }
}