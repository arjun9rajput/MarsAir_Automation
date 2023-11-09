package Automation.getPageObjects;

import static Automation.utils.ConfigPropertyReader.getProperty;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import Automation.utils.SeleniumWait;
public class BaseUi {

    WebDriver driver;
    protected SeleniumWait wait;
    private String pageName;

    protected BaseUi(WebDriver driver, String pageName) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.pageName = pageName;
    }
    protected String logMessage(String message) {
        Reporter.log(message, true);
        return message;
    }


}
