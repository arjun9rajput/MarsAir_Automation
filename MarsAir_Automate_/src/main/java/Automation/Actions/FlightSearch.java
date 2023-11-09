package Automation.Actions;

import org.openqa.selenium.WebDriver;

import Automation.getPageObjects.GetPage;

public class FlightSearch extends GetPage {

    WebDriver driver;

    public FlightSearch(WebDriver driver) {
        super(driver, "HomePage");
        this.driver = driver;
    }

    public void selectMonth(String list,String month) throws Exception {
        wait.waitForPageToLoadCompletely();
        setDropDownValue(list,month);

    }

    public void searchFlights(){
        isElementDisplayed("submitSearch");
        element("submitSearch").click();
    }

    public void backtoSearch(){
        isElementDisplayed("backButton");
        element("backButton").click();
    }

    public void verifyAvailabilityMessage(){
        verifyElementText("available","Call now on 0800 MARSAIR to book!");
    }

    public void verifyUnavailabilityMessage(){
        verifyElementText("notavailable","Sorry, there are no more seats available.");
    }

    public void verifyScheduleNotPossible(){
        verifyElementText("schedule","Unfortunately, this schedule is not possible. Please try again.");
    }

}
