package org.pages;
import org.framework.Menus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.framework.DriverFactory.getChromeDriver;

public class Header {

     WebDriver driver = getChromeDriver();

    public void clickHome() {
        driver.findElement(By.cssSelector(".logo")).click();
    }

    private void selectServicesExpandable() {
        WebElement serviciiDropDown = driver.findElement(By.xpath("//li[@class='sp-menu-item sp-has-child']/a[contains(text(), 'Servicii')]"));
        Actions action = new Actions(driver);
        action.moveToElement(serviciiDropDown).perform();
    }

    private void selectTreatmentsExpandable() {
        selectServicesExpandable();
        WebElement trat = driver.findElement(By.xpath("//li[@class='sp-menu-item sp-has-child']/a[contains(text(), 'Tratamente faciale')]"));
        Actions act = new Actions(driver);
        act.moveToElement(trat).perform();
    }

    public void clickNavBarItem(Menus value) {
        switch(value) {
            case Menus.ACASA:
                clickHome();
                break;
            case Menus.DESPRE_NOI:
                driver.findElement(By.cssSelector("a[href='/despre-noi.html']")).click();
                break;
            case Menus.SERVICII:
                driver.findElement(By.cssSelector("a[href='/servicii.html']")).click();
                break;
            case Menus.TRATAMENTE_FACIALE:
                selectServicesExpandable();
                driver.findElement(By.xpath("//li[@class='sp-menu-item sp-has-child']/a[contains(text(), 'Tratamente faciale')]")).click();
                break;
            case Menus.MEZOTERAPIE_VIRTUALA:
                selectServicesExpandable();
                selectTreatmentsExpandable();
                driver.findElement(By.xpath("//li[@class='sp-menu-item']/a[contains(text(), 'Mezoterapie virtuala')]")).click();
                break;
            case Menus.TERAPIE_CU_OXIGEN:
                selectServicesExpandable();
                selectTreatmentsExpandable();
                driver.findElement(By.xpath("//li[@class='sp-menu-item']/a[contains(text(), 'Terapie cu oxigen')]")).click();
                break;
            case Menus.MICRONEEDLING_SAU_MEZOTERAPIE_CU_ACE:
                selectServicesExpandable();
                selectTreatmentsExpandable();
                driver.findElement(By.xpath("//li[@class='sp-menu-item']/a[contains(text(), 'Microneedling')]")).click();
                break;
            case Menus.MAKE_UP:
                selectServicesExpandable();
                driver.findElement(By.xpath("//li[@class='sp-menu-item']/a[contains(text(), 'Make-up')]")).click();
                break;
            case Menus.STILIZARE_SPRANCENE:
                selectServicesExpandable();
                driver.findElement(By.xpath("//li[@class='sp-menu-item']/a[contains(text(), 'Stilizare sprancene')]")).click();
                break;
            case Menus.MICROBLADING:
                selectServicesExpandable();
                driver.findElement(By.xpath("//li[@class='sp-menu-item']/a[contains(text(), 'Microblading')]")).click();
                break;
            case Menus.EPILARE_CU_CEARA_DE_UNICA_FOLOSINTA:
                selectServicesExpandable();
                driver.findElement(By.xpath("//li[@class='sp-menu-item']/a[contains(text(), 'Epilare cu ceara')]")).click();
                break;
            case Menus.PROGRAMARI_ONLINE:
                driver.findElement(By.cssSelector("a[href='http://cosmeticabrasov.ro/programari-online.html']")).click();
                break;
            case Menus.BLOG:
                driver.findElement(By.cssSelector("a[href='/blog.html'")).click();
                break;
            case Menus.CONTACT:
                driver.findElement(By.cssSelector("a[href='/contact.html'")).click();
                break;
        }
    }
}