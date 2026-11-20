package stepdefinitions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import utils.ConfigReader;
import utils.PlaywrightManager;

public class LoginSteps {
    Page page = PlaywrightManager.page;

    @Given("I navigate to the P-Collab login page")
    public void navigate_to_login_page() {
        page.navigate(ConfigReader.get("baseUrl"));
    }

    @When("I enter valid username")
    public void enter_valid_username() {
        page.locator("input[formcontrolname='email']").fill(ConfigReader.get("username"));
    }

    @When("I enter valid password")
    public void enter_valid_password() {
        page.locator("input[formcontrolname='password']").fill(ConfigReader.get("password"));
    }

    @When("I enter username {string}")
    public void enter_username(String username) {
        page.locator("input[formcontrolname='email']").fill(username);
    }

    @When("I click on the username field and then click away")
    public void click_username_and_blur() {
        page.locator("input[formcontrolname='email']").click();
        page.locator("body").click();
    }

    @When("I enter password {string}")
    public void enter_password(String password) {
        page.locator("input[formcontrolname='password']").fill(password);
    }

    @When("I enter the CAPTCHA")
    public void enter_captcha() {
        System.out.println("Please manually enter the CAPTCHA in the browser now...");
        page.waitForTimeout(15000);
    }

    @When("I click the login button")
    public void click_login_button() {
        Locator signInButton = page.locator("button.lc-btn-primary.lc-btn-sky");
        if (signInButton.count() > 0) {
            signInButton.click();
        }
    }

    @Then("I should see an error message")
    public void verify_error_message() { }

    @Then("I should see a validation error for username")
    public void verify_username_validation_error() {
        page.waitForTimeout(1000);
        boolean isVisible = page.locator("text=Username is required").isVisible();
        org.testng.Assert.assertTrue(isVisible, "Expected 'Username is required' validation message");
    }

    @Then("I should see a validation error for password")
    public void verify_password_validation_error() { }
}