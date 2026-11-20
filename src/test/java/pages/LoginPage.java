package pages;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LoginPage {

    // ---------- Login data (move to env variables / config if the repo is shared) ----------
    private static final String USERNAME = "akshaya.bachu@incresol.com";
    private static final String PASSWORD = "Incresol@123";

    // ---------- Invite vendor data ----------
    private static final String TEMPLATE_NAME = "OCR Vendor Registration Form";
    private static final String VENDOR_EMAIL = "akshu@incresol.com";
    private static final String VENDOR_NAME = "Seoul";
    private static final String COUNTRY_SEARCH = "India";
    private static final String COUNTRY_OPTION = "India - +91";
    private static final String MOBILE_NUMBER = "9876543216";

    private static final String VENDOR_TYPE_OPTION = "01 - Schema Supplier 01";
    private static final String COMPANY_CODE_SEARCH = "kwi";
    private static final String COMPANY_CODE_OPTION = "KWID - kwid motor company";
    private static final String PURCHASE_ORG_SEARCH = "kwi";
    private static final String PURCHASE_ORG_OPTION = "KWID - kwid pur org";
    private static final String SEARCH_TERM = "akshu";
    private static final String ATTACHMENT_TO_ENABLE = "PAN";
    private static final String WORKFLOW_NAME = "SID WF";

    private static void selectDropdown(Page page, String label, String search,String optionText, boolean exact) {
        Locator dropdown = page.locator("//mat-form-field[.//mat-label[contains(normalize-space(),'" + label + "')]]//mat-select");
        dropdown.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        dropdown.scrollIntoViewIfNeeded();
        dropdown.click();
        
        page.locator(".cdk-overlay-container mat-option").first()
                .waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        if (search != null) {
          
            Locator filter = page.locator(".cdk-overlay-container").getByPlaceholder("Filter");
            if (filter.count() > 0) {
                filter.fill(search);
                page.waitForTimeout(500);
            }
        }
        page.getByRole(AriaRole.OPTION,new Page.GetByRoleOptions().setName(optionText).setExact(exact)).click();
        page.waitForTimeout(500); // let dependent dropdowns load
        System.out.println(label + " selected: " + optionText);
    }

    private static void selectFirstOption(Page page, String label) {
        Locator dropdown = page.locator("//mat-form-field[.//mat-label[contains(normalize-space(),'" + label + "')]]//mat-select");
        dropdown.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        dropdown.scrollIntoViewIfNeeded();
        dropdown.click();

        Locator options = page.locator(".cdk-overlay-container mat-option");
        options.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        Locator realOptions = options.filter(new Locator.FilterOptions().setHasNotText(Pattern.compile("^\\s*Select\\s*$")));
        String text = realOptions.first().innerText().trim();
        realOptions.first().click();
        page.waitForTimeout(500);
        System.out.println(label + " selected (first option): " + text);
    }
    private static void enableAttachmentToggle(Page page, String attachmentName) {
        Object found = page.evaluate(
                "(name) => {" + "  document.querySelectorAll('[data-auto-target]').forEach(e => e.removeAttribute('data-auto-target'));"
              + "  const input = [...document.querySelectorAll('input')]"
              + "      .find(i => (i.value || '').trim() === name);"
              + "  if (!input) return false;"
              + "  let node = input;"
              + "  while (node) {"
              + "    const toggle = node.querySelector('mat-slide-toggle');"
              + "    if (toggle) { toggle.setAttribute('data-auto-target', 'toggle'); return true; }"
              + "    node = node.parentElement;"
              + "  }"
              + "  return false;"
              + "}", attachmentName);

        Locator toggle;
        if (Boolean.TRUE.equals(found)) {
            toggle = page.locator("[data-auto-target='toggle']");
        } else {
            System.out.println("Could not locate row for '" + attachmentName + "', using 2nd toggle as fallback.");
            toggle = page.locator("mat-slide-toggle").nth(1);
        }

        toggle.scrollIntoViewIfNeeded();
        Locator switchInput = toggle.locator("input[role='switch']");
        if (!switchInput.isChecked()) {
            toggle.click();
            page.waitForTimeout(500);
        }
        System.out.println(attachmentName + " toggle enabled: " + switchInput.isChecked());
    }

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setArgs(List.of("--start-maximized")));

        Page page = browser.newPage(new Browser.NewPageOptions().setViewportSize(null));
        Scanner sc = new Scanner(System.in);

        try {
            // ---------------------------------------------------
            // 1. Open Login Page
            // ---------------------------------------------------
            page.navigate("https://qas.p-collab.com/#/login");
            page.waitForLoadState();

            // ---------------------------------------------------
            // 2. Enter Username
            // ---------------------------------------------------
            page.getByPlaceholder("Username").fill(USERNAME);

            // ---------------------------------------------------
            // 3. Click "Sign in with password"
            // ---------------------------------------------------
            page.locator("//button[.//span[contains(normalize-space(),'Sign in with password')]]").click();

            // ---------------------------------------------------
            // 4. Enter Password
            // ---------------------------------------------------
            page.getByPlaceholder("Password").fill(PASSWORD);

            // ---------------------------------------------------
            // 5. Enter CAPTCHA manually
            // ---------------------------------------------------
            System.out.println("Enter CAPTCHA:");
            String captcha = sc.nextLine();
            page.locator("//input[@data-placeholder='Captcha']").fill(captcha);

            // ---------------------------------------------------
            // 6. Click Login / Submit
            // ---------------------------------------------------
            page.locator("//button[@type='submit']").click();

            // ---------------------------------------------------
            // 7. Wait for Landing Page
            // ---------------------------------------------------
            page.waitForURL("**/landingpage");
            System.out.println("Login successful.");
            System.out.println("Current URL: " + page.url());

            // ---------------------------------------------------
            // 8. Click P-Collab
            // ---------------------------------------------------
            Locator pCollab = page.locator("//a[@class='text-anchor'][.//div[contains(normalize-space(),'P-Collab')]]");
            pCollab.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            pCollab.click();

            page.waitForURL("**/applaunchpad");
            System.out.println("After P-Collab click: " + page.url());

            // ---------------------------------------------------
            // 9. Click Vendor Onboarding Process
            // ---------------------------------------------------
            Locator vendorOnboarding = page.locator("//div[@title='Vendor Onboarding Process']");
            vendorOnboarding.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            vendorOnboarding.click();

            // ---------------------------------------------------
            // 10. Click Invite a New Vendor
            // ---------------------------------------------------
            Locator inviteVendor = page.locator("//div[@title='Invite a New Vendor']");
            inviteVendor.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            inviteVendor.click();

            page.waitForURL("**/vendoronboarding/invitenewvendor");
            System.out.println("Invite New Vendor page loaded.");
            System.out.println("Current URL: " + page.url());

            // ---------------------------------------------------
            // 11. Template
            // ---------------------------------------------------
            selectDropdown(page, "Template", null, TEMPLATE_NAME, false);

            // ---------------------------------------------------
            // 12. Email
            // ---------------------------------------------------
            Locator email = page.getByPlaceholder("Email", new Page.GetByPlaceholderOptions().setExact(true));
            email.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            email.fill(VENDOR_EMAIL);
            System.out.println("Email entered: " + VENDOR_EMAIL);

            // ---------------------------------------------------
            // 13. Vendor Name
            // ---------------------------------------------------
            // Leave the email field so Angular runs its blur/validation first
            email.press("Tab");
            page.waitForTimeout(1500);

            // Use the stable name attribute (the id mat-input-512 is dynamic)
            Locator vendorName = page.locator("input[name='vendorName']");
            vendorName.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            vendorName.scrollIntoViewIfNeeded();
            vendorName.click();
            vendorName.fill(VENDOR_NAME);

            // If Angular cleared or ignored the value, type it key by key instead
            if (!VENDOR_NAME.equals(vendorName.inputValue())) {
                vendorName.clear();
                vendorName.pressSequentially(VENDOR_NAME, new Locator.PressSequentiallyOptions().setDelay(50));
            }
            vendorName.press("Tab");
            System.out.println("Vendor name entered: " + vendorName.inputValue());

            // ---------------------------------------------------
            // 14. Country Code
            // ---------------------------------------------------
            selectDropdown(page, "Country Code", COUNTRY_SEARCH, COUNTRY_OPTION, true);

            // ---------------------------------------------------
            // 15. Mobile Number
            // ---------------------------------------------------
            Locator mobile = page.getByPlaceholder("Mobile Number", new Page.GetByPlaceholderOptions().setExact(true));
            mobile.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            mobile.fill(MOBILE_NUMBER);
            System.out.println("Mobile number entered: " + MOBILE_NUMBER);

            // ---------------------------------------------------
            // 16. Vendor Type
            // ---------------------------------------------------
            selectDropdown(page, "Vendor Type", null, VENDOR_TYPE_OPTION, true);

            // (Vendor Group and Reconciliation Account are pre-filled by the application)

            // ---------------------------------------------------
            // 17. Company Code
            // ---------------------------------------------------
            selectDropdown(page, "Company Code", COMPANY_CODE_SEARCH, COMPANY_CODE_OPTION, true);

            // ---------------------------------------------------
            // 18. Planning Group (first available option)
            // ---------------------------------------------------
            selectFirstOption(page, "Planning Group");

            // ---------------------------------------------------
            // 19. Purchase Organization
            // ---------------------------------------------------
            selectDropdown(page, "Purchase Organization", PURCHASE_ORG_SEARCH, PURCHASE_ORG_OPTION, true);

            // ---------------------------------------------------
            // 20. Search Term
            // ---------------------------------------------------
            Locator searchTerm = page.locator("input[name='searchTerm']");
            searchTerm.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            searchTerm.scrollIntoViewIfNeeded();
            searchTerm.fill(SEARCH_TERM);
            searchTerm.press("Tab");
            System.out.println("Search term entered: " + searchTerm.inputValue());

            // ---------------------------------------------------
            // 21. Enable PAN toggle
            // ---------------------------------------------------
            enableAttachmentToggle(page, ATTACHMENT_TO_ENABLE);

            // ---------------------------------------------------
            // 22. Click Invite
            // ---------------------------------------------------
            Locator invite = page.locator("button:has(span.submit-text:text-is('Invite'))");
            invite.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            invite.scrollIntoViewIfNeeded();
            System.out.println("Invite button enabled: " + invite.isEnabled());

            // click() waits for the button to be enabled and stable before clicking
            invite.click();
            System.out.println("Invite button clicked.");

            // ---------------------------------------------------
            // 23. "Select Workflow" popup (appears after clicking Invite)
            // ---------------------------------------------------
            Locator workflow = page.locator("select#WorkflowId");
            workflow.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

            try {
                workflow.selectOption(new SelectOption().setLabel(WORKFLOW_NAME));
            } catch (PlaywrightException e) {
                // (index 0 is the "Select Workflow" placeholder)
                System.out.println("Workflow '" + WORKFLOW_NAME + "' not matched, selecting first option.");
                workflow.selectOption(new SelectOption().setIndex(1));
            }
            System.out.println("Workflow selected: " + workflow.locator("option:checked").innerText().trim());

            // ---------------------------------------------------
            // 24. Click Proceed
            // ---------------------------------------------------
            Locator proceed = page.getByRole(AriaRole.BUTTON,
                    new Page.GetByRoleOptions().setName("Proceed"));
            proceed.click(); // waits until the button is enabled
            System.out.println("Proceed clicked.");

            page.waitForTimeout(3000);
            System.out.println("Current URL: " + page.url());

        } finally {
            browser.close();
            playwright.close();
        }
    }
}