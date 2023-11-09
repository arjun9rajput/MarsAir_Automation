package Automation.tests;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Automation.TestSessionInitiator;
import static Automation.utils.ConfigPropertyReader.getProperty;

public class Flight_booking_Test {

    TestSessionInitiator test;

    @BeforeClass
    @Parameters("browser")
    public void start_test_session(@Optional String browser) {
        test = new TestSessionInitiator("Account_Creation_Test", browser);
    }

    @Test
    public void Test01_Launch_Application() {
        test.launchApplication(getProperty("base_url"));
        test.uiChecks.verify_TextsAreDisplayed();
        test.uiChecks.verifyMarsAirlogo();
    }

    @Test
    public void Test02_VerifyAvailableFlight() throws Exception {
        test.flightSearch.selectMonth("departing","July");
        test.flightSearch.selectMonth("returning","December (two years from now)");
        test.flightSearch.searchFlights();
        test.uiChecks.verifySearchText();
        test.flightSearch.verifyAvailabilityMessage();
        test.flightSearch.backtoSearch();
    }

    @Test
    public void Test03_VerifyUnavailableFlight() throws Exception {

        test.flightSearch.selectMonth("departing","July");
        test.flightSearch.selectMonth("returning","July (next year)");
        test.flightSearch.searchFlights();
        test.uiChecks.verifySearchText();
        test.flightSearch.verifyUnavailabilityMessage();
        test.flightSearch.backtoSearch();
    }

    @Test
    public void Test04_VerifyImpossibleSchedule() throws Exception {
        test.flightSearch.selectMonth("departing","July");
        test.flightSearch.selectMonth("returning","December");
        test.flightSearch.searchFlights();
        test.uiChecks.verifySearchText();
        test.flightSearch.verifyScheduleNotPossible();
        test.flightSearch.backtoSearch();
    }

    @AfterMethod
    public void take_screenshot_on_failure(ITestResult result) {
        test.takescreenshot.takeScreenShotOnException(result);
    }

    @AfterClass
    public void stop_test_session() {
        test.closeTestSession();
    }

}
