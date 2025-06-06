package org.pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.framework.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.JavascriptExecutor;
import java.io.IOException;
import java.util.Set;
import org.enums.FooterMenuItems;

public class Footer extends BasePage {

    private static Logger log = LogManager.getLogger(Footer.class);

    private String originalWindowHandle;
    @FindBy(css = "li[class='item-939']")
    private WebElement despreNoi;
    @FindBy(css = "li[class='item-940']")
    private WebElement servicii;
    @FindBy(css = "li[class='item-941']")
    private WebElement blog;
    @FindBy(css = "li[class='item-1008']")
    private WebElement programariOnline;
    @FindBy(css = "li[class='item-942']")
    private WebElement contact;
    @FindBy(css = "a[class='_1drp _5lv6']")
    private WebElement facebookBtn;
    @FindBy(id = "email")
    private WebElement email;
    @FindBy(css = "input[type='password']")
    private WebElement password;

    public Footer() {
        PageFactory.initElements(driver, this);

    }

    public void clickFooterItem(FooterMenuItems footerItem) {

        scrollToFooter();

        log.info("Cliking Footer element {}...", footerItem);

        switch (footerItem) {
            case DESPRE_NOI-> click(despreNoi, false);
            case SERVICII -> click(servicii, false);
            case BLOG -> click(blog, false);
            case PROGRAMARI_ONLINE -> click(programariOnline, false);
            case CONTACT -> click(contact, false);
            case FACEBOOK_BTN -> {

                //IMPORTANT: You MUST call closeFacebookWindowsAndSwitchToOriginal() after interacting with Facebook to close the window and return context.
                //getting original window handle to switch back to it later.
                this.originalWindowHandle = driver.getWindowHandle();

                log.info("Waiting for Facebook Widget...");
                WebElement fbIframe = waitForElement(By.cssSelector("iframe[data-testid='fb:like_box Facebook Social Plugin']"));

                log.info("Switching to its iframe...");
                driver.switchTo().frame(fbIframe);
                log.info("Clicking Facebook widget. This should open a new browser window...");
                click(By.className("_1drq"), true);

                log.info("Getting window information and switching to Facebook window...");
                Object[] windowHandles = driver.getWindowHandles().toArray();
                driver.switchTo().window((String) windowHandles[windowHandles.length - 1]);

                log.info("Window found. Maximizing...");
                driver.manage().window().maximize();
                log.info("Continue interacting with Facebook Window by calling loginToFacebook() or close the window by calling closeFacebookWindowsAndSwitchToOriginal() (mandatory to continue tests).");
            }

        }
    }

    /**
     * logins to Facebook with stored TestUtils credentials.
     * Important: Call closeFacebookWindowsAndSwitchToOriginal() after this to return to context.
     */
    public void loginToFacebook() throws IOException {

        TestUtils testUtils = new TestUtils();

        //Login if we're not logged in
        if (driver.getPageSource().contains("You must log in to continue.")) {

            //Decline cookie pop-up window
            click(By.xpath("//*[@id=\"facebook\"]/body/div[3]/div[2]/div/div/div/div/div[3]/div[2]/div/div[1]/div[2]/div/div[1]/div/span/span"), true);

            //enter username and password
            email.sendKeys(testUtils.getCredentials(TestUtils.Credentials.USERNAME));
            password.sendKeys(testUtils.getCredentials(TestUtils.Credentials.PASSWORD));
            //pending facebook account zzz
            //driver.findElement(By.id("loginbutton")).click();
        }
        log.warn("You must call closeFacebookWindowsAndSwitchToOriginal after this.");
    }

    public void closeFacebookWindowsAndSwitchToOriginal() {

        log.info("Closing Facebook window and switching to original window...");
        Set<String> allWindowHandles = driver.getWindowHandles();

        for (String handle : allWindowHandles) {

            driver.switchTo().window(handle);

            String pageTitle = driver.getTitle();
            String url = driver.getCurrentUrl();

            if (pageTitle.contains("Facebook") && url.contains("facebook.com")) {
                driver.close();
            }
        }
        driver.switchTo().window(originalWindowHandle);
    }
    private void scrollToFooter() {
        WebElement f = driver.findElement(By.id("sp-bottom"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", f);
    }
}
