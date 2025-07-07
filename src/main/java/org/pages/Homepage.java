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
    private final By activeSlideBtnLocator = By.cssSelector("a[href='http://cosmeticabrasov.ro/servicii/tratamente-faciale.html']");
    private final By animationInLocator = By.cssSelector("[class='owl-item animated owl-animated-in fadeIn active']");
    private final By animationOutLocator = By.cssSelector("[class='owl-item animated owl-animated-out fadeout']");
    private final By normalActiveSlideLocator = By.cssSelector("[class='owl-item active']");
    private final By clonedActiveSlideLocator = By.cssSelector("[class='owl-item cloned active']"); //yes, we need this.

    public void clickCurrentSlideDetailsBtn() {
        // hours spent here: 6

        // we have to wait for the animations to finish. custom wait for this.
        webDriverWait(2).until(driver -> (
                driver.findElements(animationInLocator).isEmpty() &&
                driver.findElements(animationOutLocator).isEmpty()
                        ));

        // when the animation finishes, we need to wait for one of two slides to be active: the "cloned" on or the "normal" one.
        WebElement activeSlide = webDriverWait(2).until(driver -> {
            List<WebElement> normalSlide = driver.findElements(normalActiveSlideLocator);
            if (!normalSlide.isEmpty()) {
                return normalSlide.getFirst();
            }

            List<WebElement> clonedSlide = driver.findElements(clonedActiveSlideLocator);
            if (!clonedSlide.isEmpty()) {
                return clonedSlide.getFirst();
            } else {
                return null;
            }
        });

        // if we got a slide, click the button inside it.
        if (activeSlide != null) {
            try {
                // avoid long waits. slide changes fast (~2 seconds).

                webElementActions.waitForClickability(activeSlideBtnLocator, 2).click();

                //I've spent enough time here. It seems that this falls back to the javascript method about half the time. I'll come back to it later. Maybe.
            } catch (org.openqa.selenium.ElementNotInteractableException | TimeoutException e) {
                WebElement activeSlideBtn = activeSlide.findElement(activeSlideBtnLocator);
                try {
                    log.warn("Could not interact with slide button. Trying JavascriptExecutor click fallback.");
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", activeSlideBtn);
                    log.info("Sucessfully clicked slider button with JavascriptExecutor.");
                } catch (WebDriverException exception) {
                    log.error("Javascript click failed too. RIP.");
                }
            }
        } else {
            log.error("activeSlide was null.");
        }
    }

    public int getCurrentSlideIndex() {

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

    public void switchToSlide(int slideIndex) {

        int currentSlideIndex = getCurrentSlideIndex();

        // we need to hover over the slider to make prev/next buttons appear.
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id("slide-fullwidth"))).perform();

        if (currentSlideIndex == -1) {
            log.error("Unable to determine slide index.");
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
            webElementActions.clickWithWait(navigationBtn, 1);
        }
    }
}