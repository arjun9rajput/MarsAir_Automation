package Automation.tests;


import Automation.TestSessionInitiator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import static Automation.utils.ConfigPropertyReader.getProperty;
/**
 *
 * @author prashant.shukla
 */
public class Promo_code_test {

    TestSessionInitiator test;

    String[] browserSizes = {"720x360"};
    String[] layoutTags = {"mobile"};

    @BeforeClass
    @Parameters("browser")
    public void start_test_session(@Optional String browser) {
        test = new TestSessionInitiator("Login_Layout_Tests", browser);
    }

    @Test
    public void Test01_Launch_Application() {
        test.launchApplication(getProperty("base_url"));
        test.uiChecks.verify_TextsAreDisplayed();
        test.uiChecks.verifyMarsAirlogo();
    }

    @Test
    public void Test02_VerifyValidPromoCode() throws Exception {
        test.flightSearch.selectMonth("departing","July");
        test.flightSearch.selectMonth("returning","December (two years from now)");
        test.promoCode.enterPromocode("AF3-FJK-418");
        test.flightSearch.searchFlights();
        test.uiChecks.verifySearchText();
        test.promoCode.verifypromoValid("AF3-FJK-418","30%");
        test.flightSearch.verifyAvailabilityMessage();
        test.flightSearch.backtoSearch();
    }

    @Test
    public void Test03_VerifyInvalidPromocode() throws Exception {
        test.flightSearch.selectMonth("departing","July");
        test.flightSearch.selectMonth("returning","December (two years from now)");
        test.promoCode.enterPromocode("JJ1-OPQ-126");
        test.flightSearch.searchFlights();
        test.uiChecks.verifySearchText();
        test.promoCode.verifypromoInvalid("JJ1-OPQ-126");
        test.flightSearch.verifyAvailabilityMessage();
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
