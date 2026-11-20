package stepdefinitions;

import com.microsoft.playwright.Page;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.testng.Assert;

import pages.LandingPage;
import pages.CreatePRPage;
import utils.PlaywrightManager;

public class LandingPageSteps {

    private Page page;
    private LandingPage landingPage;
    private CreatePRPage createPRPage;

    public LandingPageSteps() {
        page = PlaywrightManager.page;
        landingPage = new LandingPage(page);
        createPRPage = new CreatePRPage(page);
    }

    @Then("I should be redirected to the landing page")
    public void i_should_be_redirected_to_the_landing_page() {
        Assert.assertTrue(landingPage.isLandingPageDisplayed(), "Landing page is not displayed");
        System.out.println("Landing page displayed successfully");
    }

    @When("I click on the P-Collab organization")
    public void i_click_on_the_p_collab_organization() {
        landingPage.clickPCollabOrganization();
        System.out.println("P-Collab organization clicked successfully");
    }

    @Then("I should be redirected to the P-Collab application")
    public void i_should_be_redirected_to_the_p_collab_application() {
        Assert.assertTrue(landingPage.isPCollabOpened(), "P-Collab application was not opened");
        System.out.println("P-Collab application opened successfully");
    }

    @When("I click on RFQ Process")
    public void i_click_on_rfq_process() {
        landingPage.clickRfqProcess();
        System.out.println("RFQ Process clicked successfully");
    }

    @Then("I should see the RFQ Process page")
    public void i_should_see_the_rfq_process_page() {
        Assert.assertTrue(landingPage.isRfqProcessPageDisplayed(), "RFQ Process page is not displayed");
        System.out.println("RFQ Process page displayed successfully");
    }

    @When("I click on Create PR")
    public void i_click_on_create_pr() {
        createPRPage.clickCreatePR();
        System.out.println("Create PR clicked successfully");
    }

    @Then("I should be redirected to the Create PR page")
    public void i_should_be_redirected_to_the_create_pr_page() {
        Assert.assertTrue(createPRPage.isCreatePRPageDisplayed(), "Create PR page is not displayed");
        System.out.println("Create PR page displayed successfully");
    }
}