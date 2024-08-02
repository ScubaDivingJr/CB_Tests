package org.pages;
import net.bytebuddy.dynamic.scaffold.TypeWriter;
import org.framework.Menus;
import org.framework.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.framework.DriverFactory.getChromeDriver;
import static org.framework.DriverFactory.getGetDriverWait;

public class Header {

     WebDriver driver = getChromeDriver();
     WebDriverWait wait = getGetDriverWait();

     TestUtils testUtils = new TestUtils();

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

    public void clickHamburgerMenuItem (Menus value) {

        switch(value) {
            case Menus.ACASA:
                getHamburgerMenuTopLevelChildren().getFirst().click();
                break;
            case Menus.DESPRE_NOI:
                getHamburgerMenuTopLevelChildren().get(1).click();
                break;
            case Menus.SERVICII:
                getHamburgerMenuTopLevelChildren().get(2).click();
                break;
            case Menus.TRATAMENTE_FACIALE:
                expandServicesInHamburgerMenu();
                getHamMenuServicesChildren().getFirst().click();
                break;
            case Menus.MAKE_UP:
                expandServicesInHamburgerMenu();
                getHamMenuServicesChildren().get(5).click();
                break;
            case Menus.STILIZARE_SPRANCENE:
                expandServicesInHamburgerMenu();
                getHamMenuServicesChildren().get(6).click();
                break;
            case Menus.MICROBLADING:
                expandServicesInHamburgerMenu();
                getHamMenuServicesChildren().get(7).click();
                break;
            case Menus.EPILARE_CU_CEARA_DE_UNICA_FOLOSINTA:
                expandServicesInHamburgerMenu();
                getHamMenuServicesChildren().get(8).click();
                break;
            case Menus.MEZOTERAPIE_VIRTUALA:
                expandServicesInHamburgerMenu();
                expandFacialTreatmentsHamburgerMenu();
                getFacialTreatmentsChildren().getFirst().click();
                break;
            case Menus.TERAPIE_CU_OXIGEN:
                expandServicesInHamburgerMenu();
                expandFacialTreatmentsHamburgerMenu();
                getFacialTreatmentsChildren().get(1).click();
                break;
            case Menus.MICRONEEDLING_SAU_MEZOTERAPIE_CU_ACE:
                expandServicesInHamburgerMenu();
                expandFacialTreatmentsHamburgerMenu();
                getFacialTreatmentsChildren().get(2).click();
                break;
            case Menus.TRATAMENTE_DERMATO_COSMETICE:
                expandServicesInHamburgerMenu();
                expandFacialTreatmentsHamburgerMenu();
                getFacialTreatmentsChildren().get(3).click();
                break;
            case Menus.PROGRAMARI_ONLINE:
                getHamburgerMenuTopLevelChildren().get(12).click();
                break;
            case Menus.BLOG:
                getHamburgerMenuTopLevelChildren().get(13).click();
                break;
            case Menus.CONTACT:
                getHamburgerMenuTopLevelChildren().get(14).click();
                break;
        }
    }
    private void expandServicesInHamburgerMenu() {
        getHamburgerMenuTopLevelChildren().get(2).findElement(By.cssSelector("span[class='offcanvas-menu-toggler collapsed'")).click();
        wait.until(ExpectedConditions.elementToBeClickable(getHamMenuServicesChildren().getFirst()));
    }

    private List<WebElement> getHamMenuServicesChildren() {
        //get Hamburger menu top level children
        WebElement s = getHamburgerMenuTopLevelChildren().get(2);
        //find the 'services' expandable menu within them
        WebElement s2 = s.findElement(By.id("collapse-menu-279"));
        //get its children
        List<WebElement> servChildren = s2.findElements(By.tagName("li"));
        //wait until last child in list is clickable
        wait.until(ExpectedConditions.elementToBeClickable(servChildren.getLast()));

        return servChildren;
    }

    private List<WebElement> getFacialTreatmentsChildren(){
        //get first hamburger menu child (facial treatments)
        WebElement s1 = getHamMenuServicesChildren().getFirst();
        //get child unordered list with id
        WebElement f1 = s1.findElement(By.id("collapse-menu-985"));
        //return child list
        List<WebElement> facialTreatmetsChildren = f1.findElements(By.tagName("li"));
        //wait until last child is clickable
        wait.until(ExpectedConditions.elementToBeClickable(facialTreatmetsChildren.getLast()));

        return facialTreatmetsChildren;
    }


    private void expandFacialTreatmentsHamburgerMenu() {
        expandServicesInHamburgerMenu();
        //wait until the last services menu child is clickable
        wait.until(ExpectedConditions.elementToBeClickable(getHamMenuServicesChildren().getLast()));
        //get first child item from services menu (facial treaments), then click expand button
        WebElement servicesFirstChild = getHamMenuServicesChildren().getFirst();
        servicesFirstChild.findElement(By.tagName("span")).click();
        //wait until last child in facial treatments menu is clickable
        wait.until(ExpectedConditions.elementToBeClickable(getFacialTreatmentsChildren().getLast()));
    }


    private List<WebElement> getHamburgerMenuTopLevelChildren() {
        if (!driver.findElement(By.cssSelector(".offcanvas-menu")).isDisplayed()) {
            driver.findElement(By.id("offcanvas-toggler")).click();
            //wait for hamburger menu to appear
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='offcanvas-menu']")));
        }

        //specifically locate hamburger element because footer contains another 'nav-menu'.
        WebElement hamMenu = driver.findElement(By.cssSelector("div[class='offcanvas-menu']"));

        //get all elements present in nav-menu list
        WebElement ham = hamMenu.findElement(By.cssSelector("ul[class='nav menu']"));
        List <WebElement> hamList = ham.findElements(By.tagName("li"));

        //wait until the first list element is clickable (should mean all others are clickable).
        wait.until(ExpectedConditions.elementToBeClickable(hamList.getFirst()));
        return hamList;
    }
}