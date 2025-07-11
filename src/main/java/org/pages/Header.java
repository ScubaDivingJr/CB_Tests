package org.pages;
import org.enums.Menus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.Map;
import java.util.Set;

public class Header extends BasePage {

    private final Map<Menus, By> navBarLocators = Map.ofEntries(
            Map.entry(Menus.ACASA, By.cssSelector(".logo")),
            Map.entry(Menus.DESPRE_NOI, By.cssSelector("a[href='/despre-noi.html']")),
            Map.entry(Menus.SERVICII, By.cssSelector("a[href='/servicii.html']")),
            Map.entry(Menus.TRATAMENTE_FACIALE, By.xpath("//li[@class='sp-menu-item sp-has-child']/a[contains(text(), 'Tratamente faciale')]")),
            Map.entry(Menus.MEZOTERAPIE_VIRTUALA, By.xpath("//li[@class='sp-menu-item']/a[contains(text(), 'Mezoterapie virtuala')]")),
            Map.entry(Menus.TERAPIE_CU_OXIGEN, By.xpath("//li[@class='sp-menu-item']/a[contains(text(), 'Terapie cu oxigen')]")),
            Map.entry(Menus.MICRONEEDLING_SAU_MEZOTERAPIE_CU_ACE, By.xpath("//li[@class='sp-menu-item']/a[contains(text(), 'Microneedling')]")),
            Map.entry(Menus.MAKE_UP, By.xpath("//li[@class='sp-menu-item']/a[contains(text(), 'Make-up')]")),
            Map.entry(Menus.STILIZARE_SPRANCENE, By.xpath("//li[@class='sp-menu-item']/a[contains(text(), 'Stilizare sprancene')]")),
            Map.entry(Menus.MICROBLADING, By.xpath("//li[@class='sp-menu-item']/a[contains(text(), 'Microblading')]")),
            Map.entry(Menus.EPILARE_CU_CEARA_DE_UNICA_FOLOSINTA, By.xpath("//li[@class='sp-menu-item']/a[contains(text(), 'Epilare cu ceara')]")),
            Map.entry(Menus.PROGRAMARI_ONLINE, By.cssSelector("a[href='http://cosmeticabrasov.ro/programari-online.html']")),
            Map.entry(Menus.BLOG, By.cssSelector("a[href='/blog.html']")),
            Map.entry(Menus.CONTACT, By.cssSelector("a[href='/contact.html']"))
    );

    private final Map<Menus, By> hamburgerMenuLocators = Map.ofEntries(
            Map.entry(Menus.ACASA, By.cssSelector("[class^='item-437']")),
            Map.entry(Menus.DESPRE_NOI, By.cssSelector("[class^='item-544']")),
            Map.entry(Menus.SERVICII, By.cssSelector("[class^='item-279']")),
            Map.entry(Menus.TRATAMENTE_FACIALE, By.cssSelector("[class^='item-985']")),
            Map.entry(Menus.MAKE_UP, By.cssSelector("[class^='item-986']")),
            Map.entry(Menus.STILIZARE_SPRANCENE, By.cssSelector("[class^='item-987']")),
            Map.entry(Menus.MICROBLADING, By.cssSelector("[class^='item-1010']")),
            Map.entry(Menus.EPILARE_CU_CEARA_DE_UNICA_FOLOSINTA, By.cssSelector("[class^='item-988']")),
            Map.entry(Menus.MEZOTERAPIE_VIRTUALA, By.cssSelector("[class^='item-990']")),
            Map.entry(Menus.TERAPIE_CU_OXIGEN, By.cssSelector("[class^='item-989']")),
            Map.entry(Menus.MICRONEEDLING_SAU_MEZOTERAPIE_CU_ACE, By.cssSelector("[class^='item-991']")),
            Map.entry(Menus.TRATAMENTE_DERMATO_COSMETICE, By.cssSelector("[class^='item-992']")),
            Map.entry(Menus.PROGRAMARI_ONLINE,By.cssSelector("[class^='item-1009']")),
            Map.entry(Menus.BLOG, By.cssSelector("[class^='item-1004']")),
            Map.entry(Menus.CONTACT, By.cssSelector("[class^='item-545']"))
    );

    private final Set<Menus> firstLevelMenuChildItems = Set.of(
            Menus.TRATAMENTE_FACIALE,
            Menus.MAKE_UP,
            Menus.STILIZARE_SPRANCENE,
            Menus.MICROBLADING,
            Menus.EPILARE_CU_CEARA_DE_UNICA_FOLOSINTA
    );

    private final Set<Menus> secondLevelMenuChildren = Set.of(
            Menus.MEZOTERAPIE_VIRTUALA,
            Menus.TERAPIE_CU_OXIGEN,
            Menus.MICRONEEDLING_SAU_MEZOTERAPIE_CU_ACE,
            Menus.TRATAMENTE_DERMATO_COSMETICE
    );

    public void clickHamburgerMenuItem(Menus itemToClick) {

        if (!driver.findElement(By.className("offcanvas-menu")).isDisplayed()) {
            webElementActions.clickWithWait(By.cssSelector("i[class='fa fa-bars']"), 2);
        }
        if (firstLevelMenuChildItems.contains(itemToClick)) {
            expandServicesInHamburgerMenu();
            webElementActions.clickWithWait(hamburgerMenuLocators.get(itemToClick), 2);
        }
        else if (secondLevelMenuChildren.contains(itemToClick)) {
            expandServicesInHamburgerMenu();
            expandFacialTreatmentsHamburgerMenu();
            webElementActions.clickWithWait(hamburgerMenuLocators.get(itemToClick), 2);
        }
        else {
            webElementActions.clickWithWait(hamburgerMenuLocators.get(itemToClick), 2);
        }
    }

    public void clickNavBarMenuItem(Menus itemToClick) {

        if (firstLevelMenuChildItems.contains(itemToClick)) {
            selectServicesExpandable();
            webElementActions.clickWithWait(navBarLocators.get(itemToClick), 2);
        }
        else if (secondLevelMenuChildren.contains(itemToClick)) {
            selectServicesExpandable();
            selectTreatmentsExpandable();
            webElementActions.clickWithWait(navBarLocators.get(itemToClick), 2);
        }
        else {
            webElementActions.clickWithWait(navBarLocators.get(itemToClick), 2);
        }
    }

    private void selectServicesExpandable() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(navBarLocators.get(Menus.SERVICII))).perform();
    }

    private void selectTreatmentsExpandable() {
        selectServicesExpandable();
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(navBarLocators.get(Menus.TRATAMENTE_FACIALE))).perform();
    }

    private void expandServicesInHamburgerMenu() {
        webElementActions.clickWithWait(By.cssSelector("span[class='offcanvas-menu-toggler collapsed']"), 2);
    }

    private void expandFacialTreatmentsHamburgerMenu() {
        expandServicesInHamburgerMenu();

        WebElement facialTreatmentsBtn = driver.findElement(By.cssSelector("li[class^='item-985']"));
        WebElement facialTreatmentsExpander = facialTreatmentsBtn.findElement(By.cssSelector("[class='offcanvas-menu-toggler']"));
        webElementActions.clickWithWait(facialTreatmentsExpander, 2);
    }
}