//package pages;
//
//import com.microsoft.playwright.Locator;
//import com.microsoft.playwright.Page;
//import com.microsoft.playwright.options.WaitForSelectorState;
//import java.nio.file.Paths;
//
//public class CreatePRPage {
//    private final Page page;
//
//    public CreatePRPage(Page page) {
//        this.page = page;
//    }
//
//    public void clickCreatePR() {
//        page.locator("div.landing-widgets-title[title='Create PR']").click();
//        page.waitForTimeout(1000);
//    }
//
//    public boolean isCreatePRPageDisplayed() {
//        try {
//            page.waitForURL("**/pr/createpr", new Page.WaitForURLOptions().setTimeout(10000));
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public void selectPRType(String prType) {
//        page.locator("#prDetails-prType").click();
//        page.waitForTimeout(500);
//        page.locator("//mat-option//span[@class='mat-option-text' and normalize-space(text())='" + prType + "']").click();
//        page.waitForTimeout(500);
//    }
//
//    public void enterPROrderTitle(String title) {
//        page.locator("#prDetails-prOrderTitle").fill(title);
//    }
//
//    public void enterRequisitionDescription(String description) {
//        page.locator("#productDesc-Div .angular-editor-textarea").click();
//        page.locator("#productDesc-Div .angular-editor-textarea").fill(description);
//    }
//
//    public void enterMatCode(int rowIndex, String matCode) {
//        Locator row = page.locator("table tbody tr").nth(rowIndex);
//        row.locator("input[id='materialCode']").fill(matCode);
//        page.waitForTimeout(500);
//    }
//
//    public void enterMaterialDescription(int rowIndex, String description) {
//        page.locator("input[id='td-materialDesc-2-" + rowIndex + "']").fill(description);
//    }
//
//    public void enterQuantity(int rowIndex, String quantity) {
//        page.locator("input[id='td-materialQty-3-" + rowIndex + "']").fill(quantity);
//    }
//
//    public void selectUom(int rowIndex, String uomText) {
//        Locator row = page.locator("table tbody tr").nth(rowIndex);
//        row.locator("mat-select").nth(0).click();
//        page.locator("//mat-option//span[@class='mat-option-text' and normalize-space(text())='" + uomText + "']").click();
//        page.waitForTimeout(1000);
//    }
//
//    public void selectDate(int rowIndex, String date) {
//        Locator dateInput = page.locator("input[id='deliveryDate']").nth(rowIndex);
//        dateInput.click();
//        page.waitForTimeout(500);
//
//        String[] parts = date.split("-");
//        String day = String.valueOf(Integer.parseInt(parts[0]));
//
//        page.locator("td.mat-calendar-body-cell .mat-calendar-body-cell-content")
//            .filter(new Locator.FilterOptions().setHasText(day))
//            .first()
//            .click();
//        page.waitForTimeout(500);
//    }
//
//    public void selectPurchaseOrg(int rowIndex, String orgText) {
//        Locator row = page.locator("table tbody tr").nth(rowIndex);
//        row.locator("mat-select").nth(1).click();
//        page.locator("//mat-option//span[@class='mat-option-text' and normalize-space(text())='" + orgText + "']").click();
//        page.waitForTimeout(1000);
//    }
//
//    public void enterRemarks(int rowIndex, String remarks) {
//        page.locator("input[id='td-materialSpecif-12-" + rowIndex + "']").fill(remarks);
//    }
//
//    public void uploadAttachment(int rowIndex, String filePath) {
//        page.locator("#item-upload-0-13").setInputFiles(Paths.get(filePath));
//        page.waitForTimeout(1000);
//    }
//
//    public void addNewRow() {
//        page.locator("(//i[@class='fa fa-cog settings-icon'])[1]").click();
//        page.waitForTimeout(1000);
//        page.locator("div.mat-menu-content").waitFor();
//        page.locator("button.mat-menu-item:has-text('Add New Row')").click();
//        page.waitForTimeout(4000);
//    }
//
//    public int getRowCount() {
//        return page.locator("table tbody tr").count();
//    }
//
//    public void deleteLastRow() {
//        page.keyboard().press("Escape");
//        page.waitForTimeout(500);
//
//        page.locator("body").click(new Locator.ClickOptions().setPosition(10, 10));
//        page.waitForTimeout(500);
//
//        try {
//            page.locator("div.cdk-overlay-backdrop").waitFor(
//                new Locator.WaitForOptions().setState(WaitForSelectorState.DETACHED).setTimeout(5000));
//        } catch (Exception e) { }
//
//        try {
//            page.locator("div.toast-success").waitFor(
//                new Locator.WaitForOptions().setState(WaitForSelectorState.DETACHED).setTimeout(5000));
//        } catch (Exception e) { }
//
//        Locator lastRow = page.locator("table tbody tr").last();
//        Locator cogIcon = lastRow.locator("i.fa-cog.settings-icon");
//        cogIcon.click(new Locator.ClickOptions().setForce(true));
//        page.waitForTimeout(1000);
//        page.locator("div.mat-menu-content").waitFor();
//        page.locator("button.mat-menu-item:has-text('Delete Item')").click();
//        page.waitForTimeout(4000);
//    }
//
////    public void clickSaveAsDraft() {
////        page.locator("button:has-text('Save As Draft')").click();
////        page.waitForTimeout(2000);
////    }
////
////    public void clickSubmit() {
////        page.locator("button:has-text('Submit')").click();
////        page.waitForTimeout(2000);
////   }
//}



package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreatePRPage {

    private final Page page;

    public CreatePRPage(Page page) {
        this.page = page;
    }

    public void clickCreatePR() {
        page.locator("div.landing-widgets-title[title='Create PR']").click();
        page.waitForTimeout(1000);
    }

    public boolean isCreatePRPageDisplayed() {
        try {
            page.waitForURL("**/pr/createpr",
                    new Page.WaitForURLOptions().setTimeout(10000));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void selectPRType(String prType) {
        page.locator("#prDetails-prType").click();
        page.waitForTimeout(500);

        page.locator(
            "//mat-option//span[@class='mat-option-text' and normalize-space(text())='"
            + prType + "']"
        ).click();

        page.waitForTimeout(500);
    }

    public void enterPROrderTitle(String title) {
        page.locator("#prDetails-prOrderTitle").fill(title);
    }

    public void enterRequisitionDescription(String description) {
        page.locator("#productDesc-Div .angular-editor-textarea").click();

        page.locator("#productDesc-Div .angular-editor-textarea")
            .fill(description);
    }

    public void enterMatCode(int rowIndex, String matCode) {

        Locator row = page.locator("table tbody tr").nth(rowIndex);

        row.locator("input[id='materialCode']").fill(matCode);

        page.waitForTimeout(500);
    }

    public void enterMaterialDescription(int rowIndex, String description) {

        page.locator(
            "input[id='td-materialDesc-2-" + rowIndex + "']"
        ).fill(description);
    }

    public void enterQuantity(int rowIndex, String quantity) {

        page.locator(
            "input[id='td-materialQty-3-" + rowIndex + "']"
        ).fill(quantity);
    }

    public void selectUom(int rowIndex, String uomText) {

        Locator row = page.locator("table tbody tr").nth(rowIndex);

        row.locator("mat-select").nth(0).click();

        page.locator(
            "//mat-option//span[@class='mat-option-text' and normalize-space(text())='"
            + uomText + "']"
        ).click();

        page.waitForTimeout(1000);
    }

    public void selectDate(int rowIndex, String date) {

        Locator dateInput = page.locator("input[id='deliveryDate']")
                .nth(rowIndex);

        dateInput.click();

        page.waitForTimeout(500);

        String[] parts = date.split("-");

        String day = String.valueOf(Integer.parseInt(parts[0]));

        page.locator("td.mat-calendar-body-cell .mat-calendar-body-cell-content").filter(
            new Locator.FilterOptions().setHasText(day))
        .first()
        .click();

        page.waitForTimeout(500);
    }

    public void selectPurchaseOrg(int rowIndex, String orgText) {

        Locator row = page.locator("table tbody tr").nth(rowIndex);

        row.locator("mat-select").nth(1).click();

        page.locator("//mat-option//span[@class='mat-option-text' and normalize-space(text())='"+ orgText + "']").click();

        page.waitForTimeout(1000);
    }

    public void enterRemarks(int rowIndex, String remarks) {
        Locator row = page.locator("table tbody tr").nth(rowIndex);
        row.locator("td.item-materialSpecif input").fill(remarks);
        page.waitForTimeout(500);
    }

    // =========================================================
    // UPLOAD ATTACHMENT
    // =========================================================

    public void uploadAttachment(int rowIndex, String filePath) {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new RuntimeException("Attachment file not found: " + path.toAbsolutePath());
        }
        // Select the file
        page.locator("#item-upload-0-13").setInputFiles(path);
        page.waitForTimeout(1000);
        // Click Upload button in the Attachments popup
        page.locator("button[title='Upload']").click();
        page.waitForTimeout(2000);
    }

    // =========================================================
    // ADD NEW ROW
    // =========================================================

    public void addNewRow() {

        page.locator("(//i[@class='fa fa-cog settings-icon'])[1]").click();
        page.waitForTimeout(1000);
        page.locator("div.mat-menu-content").waitFor();
        page.locator("button.mat-menu-item:has-text('Add New Row')").click();
        page.waitForTimeout(4000);
    }

    // =========================================================
    // GET ROW COUNT
    // =========================================================

    public int getRowCount() {
        return page.locator("table tbody tr").count();
    }
}

//    public void clickSaveAsDraft() {
//        page.locator("button:has-text('Save As Draft')").click();
//        page.waitForTimeout(2000);
//    }
//
//    public void clickSubmit() {
//        page.locator("button:has-text('Submit')").click();
//        page.waitForTimeout(2000);
//    }