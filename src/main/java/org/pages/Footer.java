package org.pages;
import org.framework.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.io.IOException;

import static org.framework.DriverFactory.getChromeDriver;
import static org.framework.DriverFactory.getGetDriverWait;

public class Footer {

    WebDriver driver = getChromeDriver();
    WebDriverWait wait = getGetDriverWait();

    TestUtils testUtils = new TestUtils();
    private void scrollToFooter(){
        WebElement f = driver.findElement(By.id("sp-bottom"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", f);
    }

    public static Footer getFooter(){
        return new Footer();
    }


    public void clickDespreNoi() {
        scrollToFooter();
        driver.findElement(By.cssSelector("li[class='item-939']")).click();
    }

    public void clickServicii() {
        scrollToFooter();
        driver.findElement(By.cssSelector("li[class='item-940']")).click();
    }

    public void clickBlog() {
        scrollToFooter();
        driver.findElement(By.cssSelector("li[class='item-941']")).click();
    }

    public void clickProgramariOnline() {
        scrollToFooter();
        driver.findElement(By.cssSelector("li[class='item-1008']")).click();
    }

    public void clickContact() {
        scrollToFooter();
        driver.findElement(By.cssSelector("li[class='item-942']")).click();
    }
    public void clickFacebook() {

        scrollToFooter();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"sp-bottom3\"]/div/div/div/div[2]/span/iframe")));
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"sp-bottom3\"]/div/div/div/div[2]/span/iframe")));
        driver.findElement(By.className("_1drq")).click();
    }

    public void verifyFacebookPageAndClose() throws IOException {

        //Facebook page opens in new window, so we switch to last window opened
        Object[] windowHandles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String)windowHandles[windowHandles.length-1]);
        driver.manage().window().maximize();

        //Login if we're not logged in
        if (driver.getPageSource().contains("You must log in to continue.")) {

            //Decline cookie pop-up window
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"facebook\"]/body/div[3]/div[2]/div/div/div/div/div[3]/div[2]/div/div[1]/div[2]/div/div[1]/div/span/span")));
            driver.findElement(By.xpath("//*[@id=\"facebook\"]/body/div[3]/div[2]/div/div/div/div/div[3]/div[2]/div/div[1]/div[2]/div/div[1]/div/span/span")).click();

            //enter username and password
            driver.findElement(By.id("email")).sendKeys(testUtils.getCredentials(TestUtils.Credentials.USERNAME));
            driver.findElement(By.id("pass")).sendKeys(testUtils.getCredentials(TestUtils.Credentials.PASSWORD));
            //pending facebook account zzz
            //driver.findElement(By.id("loginbutton")).click();

            //close Facebook window
            driver.close();

            //switch back to original window
            driver.switchTo().window((String)windowHandles[windowHandles.length-2]);
        }
    }
}
