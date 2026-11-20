package stepdefinitions;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ScreenshotType;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.PlaywrightManager;

import java.nio.file.Paths;

public class Hooks {

    @Before
    public void setup() {
        PlaywrightManager.playwright = Playwright.create();
        PlaywrightManager.browser = PlaywrightManager.playwright.chromium()
            .launch(new BrowserType.LaunchOptions().setHeadless(false));
        PlaywrightManager.page = PlaywrightManager.browser.newPage();
    }

    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Ensure screenshots folder exists
            java.io.File dir = new java.io.File("target/screenshots");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            byte[] screenshot = PlaywrightManager.page.screenshot(
                new Page.ScreenshotOptions()
                    .setType(ScreenshotType.PNG)
                    .setPath(Paths.get("target/screenshots/" + scenario.getName().replaceAll("[^a-zA-Z0-9]", "_") + ".png"))
            );
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        PlaywrightManager.browser.close();
        PlaywrightManager.playwright.close();
    }
}