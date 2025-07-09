package org.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import java.util.List;

public class Homepage extends BasePage {

    private static final Logger log = LogManager.getLogger(Homepage.class);

    private final By nextSlideArrowLocator = By.cssSelector("a[class='sppbSlideNext']");
    private final By prevSlideArrowLocator = By.cssSelector("a[class='sppbSlidePrev']");
    private final By activeSlideBtnLocator = By.cssSelector(".btn.btn-primary");

    public void clickSlideDetailsBtn() {
        //hours spend here: 8
        // This is a last resort to make this slider work. We don't use this anywhere else.
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.error(e);
        }
        // wait for a slide to become active.
       WebElement activeSlide = webElementActions.waitForVisibility(By.cssSelector(".owl-item.active"), 2);
        //locate details button inside the active slide and click it (with wait)
        webElementActions.clickWithWait(activeSlide.findElement(activeSlideBtnLocator), 2);
    }

    public void switchToSlide(int slideIndex) {

        int currentSlideIndex = getCurrentSlideIndex();

        // we need to hover over the slider to make prev/next buttons appear.
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id("slide-fullwidth"))).perform();

        if (currentSlideIndex == -1) {
            log.error("Unable to determine slide index.");
            throw new IllegalStateException("Unable to determine current slide index.");
        }

        if(currentSlideIndex == slideIndex) { return; }

        int slideChangeSteps = Math.abs(slideIndex - currentSlideIndex);
        By navigationBtn;

        if (slideIndex > currentSlideIndex) {
            navigationBtn = nextSlideArrowLocator;
        } else {
            navigationBtn = prevSlideArrowLocator;
        }

        for (int i = 0; i < slideChangeSteps; i++) {
            webElementActions.clickWithWait(navigationBtn, 1);
        }
    }

    private int getCurrentSlideIndex() {

        // first, let's get div that contains the dots at the bottom of the slide
        WebElement dotContainer = driver.findElement(By.className("owl-dots"));
        // then, inside it, let's get the all the owl dots classnames in order
        List<WebElement> owlDots = dotContainer.findElements(By.tagName("div"));

        for (int i = 0; i < owlDots.size(); i++) {
            String classAttr = owlDots.get(i).getAttribute("class");
            if (classAttr != null && classAttr.contains("active")) {
                return i;
            }
        }
        return -1;
    }
}