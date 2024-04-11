package org.veeva.assignment;

import org.testng.Assert;
import org.veeva.assignment.utilities.CommonFrameworkLogger;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

public class PageWebElements {

    protected WebDriver driver;
    private String propertyTimeOut = Optional.ofNullable(System.getProperty("timeout")).isPresent() ? System.getProperty("timeout") : "20";
    private static Logger LOGGER = new CommonFrameworkLogger(PageWebElements.class).getLOGGER();
    private JavascriptExecutor js;

    public PageWebElements() {
        try {
            this.driver = BasicWebDriver.driver;
            js = (JavascriptExecutor) driver;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to Wait for element to be visible based on the timeout provided at the run time
     *
     * @param locator          - the component of the page that you want to wait for
     * @param usePropertyTimeOut - your own custom timeout provided at the run time
     * @return the web element for the loadable
     */
    public WebElement waitForElementVisible(By locator, boolean usePropertyTimeOut) {
        try {
            if (usePropertyTimeOut) {
                new WebDriverWait(driver, Duration.ofSeconds(Integer.valueOf(propertyTimeOut)))
                        .until(
                                ExpectedConditions.visibilityOfElementLocated(locator)
                        );
            }
            return waitForElementVisible(locator);
        }
        catch (Exception e){
            CaptureScreen.takeScreenSnapShot();
            LOGGER.warn("Unable to find the Component(locator):"+locator);
            throw new TimeoutException();
        }
    }


    /**
     * Method to Wait for element (based on timeout and attribute and its value) to be visible
     *
     * @param webElement - the web element you want to wait for
     * @return the web element for the loadable
     */
    public Boolean waitForElementVisibleBasedOnAttribute(WebElement webElement, String attribute,String attributeValue) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(Integer.valueOf(propertyTimeOut)))
                    .until(
                            ExpectedConditions.attributeToBe(webElement,attribute,attributeValue)
                    );
            return true;
        }
        catch (Exception e){
            CaptureScreen.takeScreenSnapShot();
            LOGGER.warn("Unable to find the Component(locator):"+webElement);
            throw new TimeoutException();
        }
    }

    /**
     * Method to Wait for element (based on timeout) to be visible
     *
     * @param locator - the component of the page that you want to wait for
     * @return the web element for the loadable
     */
    private WebElement waitForElementVisible(By locator) {
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(Integer.valueOf(propertyTimeOut)))
                    .pollingEvery(Duration.ofSeconds(5))
                    .ignoring(NoSuchElementException.class);
            return wait.until(webDriver -> getWebElement(locator));
        }
        catch (Exception e){
            CaptureScreen.takeScreenSnapShot();
            LOGGER.warn("Unable to find the Component(locator):"+locator);
            throw new TimeoutException();
        }
    }

    /**
     * Method to get the web element for the by
     *
     * @param by - the by for which you want the web element
     * @return - web element for that by
     */
    public WebElement getWebElement(By by) {
        try {
            return driver.findElement(by);
        }
        catch (Exception e){
            CaptureScreen.takeScreenSnapShot();
            LOGGER.warn("Unable to find the Component(locator):"+by);
            throw new RuntimeException();
        }
    }

    /**
     * Method to get the web element from a web element
     *
     * @param webElement - the element inside which you want to search for the following by
     * @param by - the by for which you want the web element
     * @return - web element for that by
     */
    public WebElement getWebElement(WebElement webElement, By by) {
        try {
            return webElement.findElement(by);
        }
        catch (Exception e){
            CaptureScreen.takeScreenSnapShot();
            LOGGER.warn("Unable to find the Component(locator):"+by);
            throw new NoSuchElementException();
        }
    }

    /**
     * Method to navigate using the specified url and then wait for the page to load
     *
     * @param url                       - the url to which you want to navigate
     * @param expectedLoadableComponent - the By which you expected to present once the page is loaded
     */
    public void navigateUsingURL(String url, By expectedLoadableComponent) {
        driver.get(url);
        waitForElementVisible(expectedLoadableComponent);
    }

    /**
     * Method to navigate using the specified url without wait
     *
     * @param url                       - the url to which you want to navigate
     */
    public void navigateUsingURL(String url) {
        driver.get(url);
    }

    /**
     * Method to click and wait for next loadable
     *
     * @param clickableBy - the element you want to click
     * @param expectedBy   - the By you want to wait for if the click is successful
     * @return - the destination web element
     */
    public WebElement click(By clickableBy, By expectedBy) {
        return click(getWebElement(clickableBy), expectedBy);
    }

    /**
     * Method to click and wait for the destination or expected by to load
     *
     * @param element   - the element you want to click
     * @param expectedBy - the destination or expected By once click is successful
     * @return - the destination or expected web element
     */
    public WebElement click(WebElement element, By expectedBy) {
        click(element);
        return waitForElementVisible(expectedBy,true);
    }

    /**
     * Method to just click based on web element
     *
     * @param element - the web element you want to click
     */
    public void click(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            LOGGER.warn("Selenium click did not worked!! Trying java script click!");
            clickUsingJS(element);
        }
    }

    /**
     * Method to click using javascript
     *
     * @param element - the element which you want to click
     */
    public void clickUsingJS(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * Method to find the value present for a by
     *
     * @param by - the by for which you want to get the value
     * @return - the value present for the specified by
     */
    public String getText(By by) {
        return getWebElement(by).getText();
    }

    /**
     * Method to find the value present for a by
     *
     * @param webElement - the webElement for which you want to get the value
     * @return - the value present for the specified by
     */
    public String getText(WebElement webElement) {
        return webElement.getText();
    }

    /**
     * Method to check if element is visible
     *
     * @param by - the locator whose visibility you want to check
     * @return - true if visible else false
     */
    public boolean isElementVisible(By by) {
        return getWebElement(by).isDisplayed();
    }

    /**
     * Method to get multiple elements
     *
     * @param by - the by which you know have multiple matches in the DOM
     * @return - list of web elements if multiple matches found. if no match found, it will return a empty list
     */
    public List<WebElement> getWebElements(By by) {
        return driver.findElements(by);
    }

    /**
     * Method to get multiple elements from inside a webElement
     *
     * @param element - the element inside which you want to get multiple webelements
     * @param by - the by which you know have multiple matches in the DOM
     * @return - list of web elements if multiple matches found. if no match found, it will return a empty list
     */
    public List<WebElement> getWebElements(WebElement element,By by) {
        return element.findElements(by);
    }

    /**
     * Method to hover over a page element
     * @param element - the element to which you want hover
     */
    public void hover(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    /**
     * Method to get all windows
     * @return all windows
     */
    private Set<String> getAllWindows(){
        return driver.getWindowHandles();
    }

    public void switchToWindow(String windowTitle){
        for (String windowHandle : getAllWindows()) {
            driver.switchTo().window(windowHandle);
            if (driver.getTitle().equals(windowTitle)) {
                break;
            }
        }
    }

    /**
     * Method to scroll to a element
     *
     * @param webElement - the web element to which you want to scroll
     */
    public void scroll(WebElement webElement) {
        js.executeScript("arguments[0].scrollIntoView();", webElement);
    }

    /**
     * Get Section WebElement
     * @param sectionName - the section for which you want the web element
     * @param pageSectionBy - the page section by
     * @param subSectionBy - the page subsection of the section
     * @return webelement of the section
     */
    public WebElement getSubSection(String sectionName, By pageSectionBy, By subSectionBy){
        WebElement element = waitForElementVisible(pageSectionBy,true);
        for (WebElement webElement : getAllSections(element,subSectionBy)) {
            if(getText(webElement).contains(sectionName)){
                return webElement;
            }
        }
        throw new NoSuchElementException();
    }

    public List<WebElement> getAllSections(WebElement element,By subSectionBy) {
        return getWebElements(element, subSectionBy);
    }

    /**
     * Method to print and log the asserted output
     *
     * @param actual   - the actual output on the page
     * @param expected - the expected output on the page
     * @param message  - the message you want to print accompanied w.r.t. assertion
     */
    public static void verify(Object actual, Object expected, String message) {
        final String format = String.format("Actual:%s, Expected:%s, Message:%s:%s", actual, expected, message, actual);
        LOGGER.info(format);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Method to get the attribute value for a by
     *
     * @param by        - the by for which you want the attribute value
     * @param attribute - the attribute in that by
     * @return - the value of the attribute
     */
    public String getAttributeValue(By by, String attribute) {
        return getAttributeValue(getWebElement(by),attribute);
    }

    /**
     * Method to get the attribute value for a element
     *
     * @param element        - the element for which you want the attribute value
     * @param attribute - the attribute in that by
     * @return - the value of the attribute
     */
    public String getAttributeValue(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }
}
