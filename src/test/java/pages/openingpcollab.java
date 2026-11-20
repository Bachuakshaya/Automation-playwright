package pages;

import com.microsoft.playwright.*;

public class openingpcollab {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create(); {   //initializes playwright
 
            Browser browser = playwright.chromium().launch(            //opens browser
                    new BrowserType.LaunchOptions().setHeadless(false));   //shows browser on the screen, if we use true the browser runs without displaying the UI
            Page page = browser.newPage();     //creates a page 	
            page.navigate("https://qas.p-collab.com/#/login");   //navigates to webpage 
            System.out.println("Page title: " + page.title());
            page.waitForTimeout(2000);  
            
                      
            
//            browser.close();
            
           
        }
    }
}	