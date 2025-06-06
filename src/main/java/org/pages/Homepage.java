package org.pages;

import com.google.common.util.concurrent.UncheckedTimeoutException;
import org.framework.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class Homepage extends BasePage {

    private final By nextSlideArrowLocator = By.cssSelector("a[class='sppbSlideNext']");
    private final By prevSlideArrowLocator = By.cssSelector("a[class='sppbSlidePrev']");
    private final By activeSlideLocator = By.cssSelector("[class='owl-item active']");
    private final By detailsInSlideBtnLocator = By.cssSelector("a[href='http://cosmeticabrasov.ro/servicii/tratamente-faciale.html']");
    private final By activeSlideBtnLocator = By.cssSelector(".owl-item.active a[href='http://cosmeticabrasov.ro/servicii/tratamente-faciale.html']");

    public void clickCurrentSlideDetailsBtnTest() {
        // Wait until the active slide's details button is clickable, then click it
        WebElement detailsBtn = webDriverWait.until(ExpectedConditions.elementToBeClickable(activeSlideBtnLocator));
        click(detailsBtn, false);
    }

    public int getCurrentSlideIndex() {

        //first, let's get div that contains the dots at the bottom of the slide
        WebElement dotContainer = driver.findElement(By.className("owl-dots"));
        //then, inside it, let's get the all the owl dots classnames in order
        List<WebElement> owlDots = dotContainer.findElements(By.tagName("div"));

        for (WebElement ele : owlDots) {
            if (!owlDots.isEmpty() && ele.getAttribute("class").equals("owl-dot active")) {
                return owlDots.indexOf(ele);
            }
        }
        return -1;
    }

    public void switchToSlide(int slideIndex) {

        int currentSlideIndex = getCurrentSlideIndex();

        if (currentSlideIndex == -1) {
            throw new IllegalStateException("Unable to determine current slide index.");
        }

        if(currentSlideIndex == slideIndex) {
            return;
        }

        int slideChangeSteps = Math.abs(slideIndex - currentSlideIndex);

        By navigationBtn;

        if (slideIndex > currentSlideIndex) {
            navigationBtn = nextSlideArrowLocator;
        }
        else {
            navigationBtn = prevSlideArrowLocator;
        }

        for (int i = 0; i < slideChangeSteps; i++) {
            click(navigationBtn, true);
        }
    }

    public void clickNextSlideButtonTest() {
        click(nextSlideArrowLocator, true);
    }
}