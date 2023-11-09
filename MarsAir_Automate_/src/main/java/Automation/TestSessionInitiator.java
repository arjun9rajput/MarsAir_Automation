package Automation;

import static Automation.utils.ConfigPropertyReader.getProperty;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import Automation.utils.TakeScreenshot;
import Automation.Actions.FlightSearch;
import Automation.Actions.PromoCode;
import Automation.Actions.UIChecks;

public class TestSessionInitiator {

    protected WebDriver driver;
    private final WebDriverFactory wdfactory;

    public FlightSearch flightSearch;
    public UIChecks uiChecks;

    public PromoCode promoCode;


    public TakeScreenshot takescreenshot;

    private String testname;


    private void _initPage() {
        flightSearch = new FlightSearch(driver);
        uiChecks = new UIChecks(driver);
        promoCode = new PromoCode(driver);
    }


    public TestSessionInitiator(String testname, String browserName) {
        wdfactory = new WebDriverFactory(browserName);
        testInitiator(testname);
        this.testname = testname;

    }

    private void testInitiator(String testname) {
        _configureBrowser();
        _initPage();
        takescreenshot = new TakeScreenshot(testname, this.driver);
    }

    private void _configureBrowser() {
        driver = wdfactory.getDriver(_getSessionConfig());

        driver.manage()
                .timeouts()
                .implicitlyWait(Integer.parseInt(getProperty("timeout")),
                        TimeUnit.SECONDS);
    }

    private Map<String, String> _getSessionConfig() {
        String[] configKeys = {"browser", "seleniumserver",
                "seleniumserverhost", "timeout", "driverpath",};
        Map<String, String> config = new HashMap<>();
        for (String string : configKeys) {
            config.put(string, getProperty("./Config.properties", string));
        }
        return config;
    }


    public void launchApplication(String base_url) {
        Reporter.log("\n[INFO]: The application url is :- " + base_url, true);
        driver.manage().deleteAllCookies();
        driver.get(base_url);

    }


    public void closeBrowserSession() {
        Reporter.log("[INFO]: The Test: " + this.testname.toUpperCase() + " COMPLETED!"
                + "\n", true);
        driver.quit();

    }

    public void closeTestSession() {
        closeBrowserSession();
    }
}
