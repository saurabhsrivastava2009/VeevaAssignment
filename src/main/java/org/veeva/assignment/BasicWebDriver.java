package org.veeva.assignment;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.veeva.assignment.utilities.CommonFrameworkLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Base class for driver management
 * @author Saurabh Srivastava
 * @version 0.1
 * @since 09-04-2024
 */
public class BasicWebDriver {

    public static WebDriver driver;
    private static final String browser = System.getProperty("browser");
    private static final Logger LOGGER = new CommonFrameworkLogger(BasicWebDriver.class).getLOGGER();
    public static WebDriver getWebDriver(){
        if(driver==null){
            driver = getNewWebDriverInstance();
        }
        else if(driver instanceof RemoteWebDriver){
            SessionId sessionId = ((RemoteWebDriver) driver).getSessionId();
            if(sessionId==null){
                driver = getNewWebDriverInstance();
            }
        }
        return driver;
    }

    /**
     * Method to get a new driver instance
     * @return webdriver
     */
    private static WebDriver getNewWebDriverInstance(){
        driver = getBrowserDriver();
        driver.manage().window().maximize();
        return driver;
    }

    /**
     * Method to get web driver based on Browser
     * @return - the webdriver for specified browser
     */
    private static WebDriver getBrowserDriver() {
        String message = "Browser Driver";
        if(BasicWebDriver.browser.equalsIgnoreCase("chrome")){
            LOGGER.info(message+":chrome driver");
            return new ChromeDriver();
        }
        else if (BasicWebDriver.browser.equalsIgnoreCase("firefox")) {
            LOGGER.info(message+":firefox driver");
            return new FirefoxDriver();
        }
        else if (BasicWebDriver.browser.equalsIgnoreCase("edge")) {
            LOGGER.info(message+":edge driver");
            return new EdgeDriver();
        }
        LOGGER.error(message+":not found");
        throw new RuntimeException(message+":not found");
    }

    /**
     * Method to tear down web driver
     */
    public static void tearDown() {
        driver.quit();
    }
}
