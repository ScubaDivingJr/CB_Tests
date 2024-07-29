package org.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import static org.framework.DriverFactory.getChromeDriver;
import static org.framework.DriverFactory.getGetDriverWait;

public class Footer {

    WebDriver driver = getChromeDriver();

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

}
