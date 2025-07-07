package org.pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.Set;
import org.enums.FooterMenuItems;

public class Footer extends BasePage {

    private static final Logger log = LogManager.getLogger(Footer.class);

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

        switch (footerItem) {
            case DESPRE_NOI-> webElementActions.click(despreNoi);
            case SERVICII -> webElementActions.click(servicii);
            case BLOG -> webElementActions.click(blog);
            case PROGRAMARI_ONLINE -> webElementActions.click(programariOnline);
            case CONTACT -> webElementActions.click(contact);
            case FACEBOOK_BTN -> handleFacebook();
        }
    }

    /**
     * logs in to Facebook with stored TestUtils credentials. Just for local testing only.
     * Important: Call closeFacebookWindowsAndSwitchToOriginal() after this to return to context.
     */
    public void loginToFacebook() {

        //Login if we're not logged in
        if (driver.getPageSource().contains("You must log in to continue.")) {

            //Decline cookie pop-up window
            try {
                webElementActions.clickWithWait(By.xpath("//*[@id=\"facebook\"]/body/div[3]/div[2]/div/div/div/div/div[3]/div[2]/div/div[1]/div[2]/div/div[1]/div/span/span"), 2);
                webElementActions.sendKeys(email,"test");
                webElementActions.sendKeys(password, "test");
            } catch (Exception e) {
                log.error("Could not click cookie pop-up in Facebook / entering user&pass. Whatever, moving on.");
            }
            //webElementActions.click(By.id("loginbutton"));
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
        WebElement f = webElementActions.waitForPresence(By.id("sp-bottom"), 2);
        webElementActions.jsScrollIntoView(f);
    }

    private void handleFacebook() {
        //IMPORTANT: You MUST call closeFacebookWindowsAndSwitchToOriginal() after interacting with Facebook to close the window and return context.
        //getting original window handle to switch back to it later.
        this.originalWindowHandle = driver.getWindowHandle();

        log.info("Waiting for Facebook Widget...");
        WebElement fbIframe = webElementActions.waitForVisibility(By.tagName("iframe"), 3);
        //WebElement fbIframe = waitForVisibility(By.tagName("iframe"), 3);

        log.info("Switching to its iframe...");
        driver.switchTo().frame(fbIframe);
        webElementActions.click(By.className("_1drq"));
        //click(By.className("_1drq"), true);

        log.info("Getting window information and switching to Facebook window...");
        Object[] windowHandles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[windowHandles.length - 1]);

        log.info("Window found. Maximizing...");
        driver.manage().window().maximize();
        log.info("Continue interacting with Facebook Window by calling loginToFacebook() or close the window by calling closeFacebookWindowsAndSwitchToOriginal() (mandatory to continue tests).");
    }
}
