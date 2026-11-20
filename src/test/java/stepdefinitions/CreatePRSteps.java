package stepdefinitions;

import org.testng.Assert;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CreatePRPage;
import utils.ConfigReader;
import utils.PlaywrightManager;

public class CreatePRSteps {
    private Page page;
    private CreatePRPage createPRPage;

    public CreatePRSteps() {
        page = PlaywrightManager.page;
        createPRPage = new CreatePRPage(page);
    }

    @When("I enter PR Order Title {string}")
    public void enter_pr_order_title(String title) {
        createPRPage.enterPROrderTitle(title);
    }

    @When("I enter Requisition Description {string}")
    public void i_enter_requisition_description(String description) {
        createPRPage.enterRequisitionDescription(description);
    }

    @When("I enter Mat Code {string} for row 1")
    public void enter_mat_code(String matCode) {
        createPRPage.enterMatCode(0, matCode);
    }

    @When("I enter Material Description {string} for row 1")
    public void enter_material_description(String description) {
        createPRPage.enterMaterialDescription(0, description);
    }

    @When("I enter Quantity {string} for row 1")
    public void enter_quantity(String quantity) {
        createPRPage.enterQuantity(0, quantity);
    }

    @When("I select UOM {string} for row 1")
    public void select_uom(String uomText) {
        createPRPage.selectUom(0, uomText);
    }

    @When("I slect date {string} for row {int}")
    public void i_slect_date_for_row(String date, Integer rowIndex) {
        createPRPage.selectDate(rowIndex - 1, date);
    }

    @When("I select Purchase Organization {string} for row 1")
    public void select_purchase_org(String orgText) {
        createPRPage.selectPurchaseOrg(0, orgText);
    }

    @When("I enter Remarks {string} for row 1")
    public void enter_remarks(String remarks) {
        createPRPage.enterRemarks(0, remarks);
    }
    
    @When("I upload an attachment for row 1")
    public void upload_attachment() {
        createPRPage.uploadAttachment(0, ConfigReader.get("uploadFilePath"));
    }

    @When("I add a new row")
    public void add_new_row() {
        createPRPage.addNewRow();
    }

    @Then("a new row should be added")
    public void verify_row_added() {
        Assert.assertTrue(createPRPage.getRowCount() > 1, "Row was not added");
    }
}