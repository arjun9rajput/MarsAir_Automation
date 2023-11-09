package Automation;


import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

public class WebDriverFactory {

    private String browser = "";

    public WebDriverFactory(String browserName) {
        browser = browserName;
    }

    private static final DesiredCapabilities capabilities = new DesiredCapabilities();

    public WebDriver getDriver(Map<String, String> seleniumconfig) {

        if (browser == null || browser.isEmpty()) {
            browser = seleniumconfig.get("browser");
        }
        Reporter.log("[INFO]: The test Browser is " + browser.toUpperCase()
                + " !!!", true);

        if (seleniumconfig.get("seleniumserver").equalsIgnoreCase("local")) {
            if (browser.equalsIgnoreCase("firefox")) {
                return getFirefoxDriver();
            } else if (browser.equalsIgnoreCase("chrome")) {
                return getChromeDriver();
            }
        }
        return new FirefoxDriver();
    }

    private static WebDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        return new ChromeDriver(options);
    }

    private static WebDriver getFirefoxDriver() {
        FirefoxOptions ffOptions = new FirefoxOptions();
        ffOptions.setCapability("browser.cache.disk.enable", false);
        return new FirefoxDriver(ffOptions);
    }

}
