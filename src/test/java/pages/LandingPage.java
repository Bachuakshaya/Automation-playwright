package pages;

import com.microsoft.playwright.Page;
import java.nio.file.Paths;

public class LandingPage {
    private final Page page;

    public LandingPage(Page page) {
        this.page = page;
    }

    public boolean isLandingPageDisplayed() {
        try {
            page.waitForURL("**/landingpage", new Page.WaitForURLOptions().setTimeout(10000));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickPCollabOrganization() {
        page.locator("div.landing-widgets-title:has-text('P-Collab')").click();
        page.waitForTimeout(1000);
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("target/screenshots/after_pcollab_click.png")));
    }

    public boolean isPCollabOpened() {
        try {
            page.waitForURL("**/applaunchpad", new Page.WaitForURLOptions().setTimeout(10000));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickRfqProcess() {
        // Scroll down since RFQ Process card is below the fold
        page.locator("div.landing-widgets-title[title='RFQ Process']").scrollIntoViewIfNeeded();
        page.locator("div.landing-widgets-title[title='RFQ Process']").click();
        page.waitForTimeout(1000);
    }

    public boolean isRfqProcessPageDisplayed() {
        try {
            page.waitForURL("**/rfqprocess/launchpad", new Page.WaitForURLOptions().setTimeout(10000));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}