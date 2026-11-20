package stepdefinitions;

import com.microsoft.playwright.Page;

public class LoginPage {

    private Page page;

    // Constructor
    public LoginPage(Page page) {
        this.page = page;
    }

    public void clickSignInWithButton() {
        page.locator("//button/span[text()=' Sign in with password ']").click();
    }

    public void setUsername(String username) {
        page.getByPlaceholder("Username").fill(username);
    }

    public void setPassword(String password) {
        page.getByPlaceholder("Password").fill(password);
    }

    public void setCaptcha(String captcha) {
        page.locator("//input[@data-placeholder='Captcha']").fill(captcha);
    }

    public void clickOnSubmit() {
        page.locator("//*[@type='submit']").click();
    }
}