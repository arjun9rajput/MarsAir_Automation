package Automation.Actions;

import Automation.getPageObjects.GetPage;
import org.openqa.selenium.WebDriver;

public class UIChecks extends GetPage {


    public UIChecks(WebDriver driver) {
        super(driver, "Locators");

    }

    public void verify_TextsAreDisplayed() {
        isElementDisplayed("book_ticket");
        isElementDisplayed("welcome_text");
        isElementDisplayed("departText");
        isElementDisplayed("returnText");
        isElementDisplayed("promoCode");
    }

    public void verifyMarsAirlogo(){
        isElementDisplayed("MarsAirlogo");
    }

    public void verifySearchText(){
        isElementDisplayed("searchResults");
    }


}
