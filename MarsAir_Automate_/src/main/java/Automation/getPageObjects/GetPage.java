package Automation.getPageObjects;

import static Automation.getPageObjects.ObjectFileReader.getELementFromFile;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class GetPage extends BaseUi {

    protected WebDriver webdriver;
    String pageName;

    public GetPage(WebDriver driver, String pageName) {
        super(driver, pageName);
        this.webdriver = driver;
        this.pageName = pageName;
    }

    protected WebElement element(String elementToken) {
        return element(elementToken, "");
    }

    protected WebElement element(String elementToken, String replacement)
            throws NoSuchElementException {
        WebElement elem = null;
        try {
            elem = wait.waitForElementToBeVisible(webdriver
                    .findElement(getLocator(elementToken, replacement)));
        } catch (NoSuchElementException excp) {
            fail("[ASSERT FAILED]: Element " + elementToken
                    + " not found on the " + this.pageName + " !!!");
        } catch (NullPointerException npe) {

        }
        return elem;
    }

    protected  void setDropDownValue(String elementToken, String replacement) throws Exception {
        WebElement selecBox = (WebElement) wait.waitForElementsToBeVisible(webdriver
                .findElements(getLocator(elementToken, replacement)));
        Select selectDropDown = new Select(selecBox);
        selectDropDown.selectByVisibleText(replacement);

    }



    protected boolean isElementDisplayed(String elementName) {
        wait.waitForElementToBeVisible(element(elementName));
        boolean result = element(elementName).isDisplayed();
        assertTrue(result, "[ASSERT FAILED]: element '" + elementName
                + "' is not displayed.");
        logMessage("[ASSERT PASSED]: element " + elementName + " is displayed.");
        return result;
    }

    protected boolean isElementDisplayed(String elementName,
                                         String elementTextReplace) {
        wait.waitForElementToBeVisible(element(elementName, elementTextReplace));
        boolean result = element(elementName, elementTextReplace).isDisplayed();
        assertTrue(result, "[ASSERT FAILED]: element '" + elementName
                + "with text " + elementTextReplace + "' is not displayed.");
        logMessage("[ASSERT PASSED]: element " + elementName + " with text "
                + elementTextReplace + " is displayed.");
        return result;
    }


    protected By getLocator(String elementToken, String replacement) {
        String[] locator = getELementFromFile(this.pageName, elementToken);
        locator[2] = locator[2].replaceAll("\\#\\{.+\\}", replacement);
        return getBy(locator[1].trim(), locator[2].trim());
    }

    protected void verifyElementText(String elementName, String expectedText) {
        wait.waitForElementToBeVisible(element(elementName));
        logMessage("[INFO]: element Text :- '" + element(elementName).getText().trim() + "'\nExpected Text :-" + expectedText);
        assertEquals(element(elementName).getText().trim(), expectedText,
                "[ASSERT FAILED]: Text of the page element '"
                        + elementName + "' is not as expected: ");
        logMessage("[ASSERT PASSED]: element " + elementName
                + " is visible and Text is " + expectedText);
    }


    private By getBy(String locatorType, String locatorValue) {
        switch (Locators.valueOf(locatorType)) {
            case id:
                return By.id(locatorValue);
            case xpath:
                return By.xpath(locatorValue);
            case css:
                return By.cssSelector(locatorValue);
            case name:
                return By.name(locatorValue);
            case classname:
                return By.className(locatorValue);
            case linktext:
                return By.linkText(locatorValue);
            default:
                return By.id(locatorValue);
        }
    }
}
