package Automation.Actions;

import Automation.getPageObjects.GetPage;
import org.openqa.selenium.WebDriver;

public class PromoCode extends GetPage {

    public PromoCode(WebDriver driver) {

        super(driver, "Locators");
    }

    public void enterPromocode(String promo){
        isElementDisplayed("boxPromo");
        element("boxPromo").sendKeys(promo);
    }

    public void verifypromoValid(String promo, String percentage){
        isElementDisplayed("promotional");
        isElementDisplayed("promocode",promo);
        isElementDisplayed("percentage",percentage);

    }

    public void verifypromoInvalid(String promo){
        verifyElementText("sorry","Sorry, code ");
        isElementDisplayed("promocode",promo);
        verifyElementText("notValid"," is not valid");
    }

}
