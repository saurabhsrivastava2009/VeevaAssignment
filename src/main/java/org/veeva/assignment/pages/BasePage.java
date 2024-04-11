package org.veeva.assignment.pages;

import org.apache.log4j.Logger;
import org.veeva.assignment.PageWebElements;
import org.veeva.assignment.utilities.CommonFrameworkLogger;

/**
 * Base page for all the Page Objects
 *
 * @author Saurabh Srivastava
 * @since 10-04-2024
 */
public abstract class BasePage {

    public static String URL = "https://www.nba.com/";
    public PageWebElements pageWebElements = new PageWebElements();
    public static Logger LOGGER = new CommonFrameworkLogger(BasePage.class).getLOGGER();

    /**
     * Method to navigate to the page from which this method is called
     *
     * @return Page to which we navigate
     */
    public abstract BasePage navigateToThisPage();

    /**
     * Method to switch window/tab
     *
     * @param windowTitle - the window or tab title to which we would like to switch
     */
    public void switchToWindow(String windowTitle) {
        pageWebElements.switchToWindow(windowTitle);
    }

    /**
     * Method to verify/assert
     *
     * @param actual   - the actual result
     * @param expected - the expected result
     * @param message  - the message that you would like to print
     */
    public void verify(Object actual, Object expected, String message) {
        pageWebElements.verify(actual, expected, message);
    }
}
