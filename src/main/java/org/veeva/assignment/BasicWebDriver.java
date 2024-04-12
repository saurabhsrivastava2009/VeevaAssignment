package org.veeva.assignment;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.veeva.assignment.utilities.CommonFrameworkLogger;

import java.util.Objects;

/**
 * Base class for driver management
 *
 * @author Saurabh Srivastava
 * @version 0.1
 * @since 09-04-2024
 */
public class BasicWebDriver {

    private static final ThreadLocal<WebDriver> driverThreadLocal = ThreadLocal.withInitial(()->null);
    private static final String browser = System.getProperty("browser");
    private static final Logger LOGGER = new CommonFrameworkLogger(BasicWebDriver.class).getLOGGER();

    /**
     * Method to get the driver
     *
     * @return driver object
     */
    public static WebDriver getWebDriver() {
        if (Objects.isNull(driverThreadLocal.get())) {
            driverThreadLocal.set(getNewWebDriverInstance());
        } else if (driverThreadLocal.get() instanceof RemoteWebDriver) {
            SessionId sessionId = ((RemoteWebDriver) driverThreadLocal.get()).getSessionId();
            if (sessionId == null) {
                driverThreadLocal.set(getNewWebDriverInstance());
            }
        }

        return driverThreadLocal.get();
    }

    /**
     * Method to get a new driver instance
     *
     * @return webdriver
     */
    private static WebDriver getNewWebDriverInstance() {
        WebDriver driver = getBrowserDriver();
        driver.manage().window().maximize();
        return driver;
    }

    /**
     * Method to get web driver based on Browser
     *
     * @return - the webdriver for specified browser
     */
    private static WebDriver getBrowserDriver() {
        String message = "Browser Driver";
        if (BasicWebDriver.browser.equalsIgnoreCase("chrome")) {
            LOGGER.info(message + ":chrome driver");
            ChromeOptions options = new ChromeOptions();
            if(System.getProperty("isHeadless")!=null){
                if(System.getProperty("isHeadless").equals("true")){
                    options.addArguments("--headless=new");
                    return new ChromeDriver(options);
                }
            }
            return new ChromeDriver();
        } else if (BasicWebDriver.browser.equalsIgnoreCase("firefox")) {
            LOGGER.info(message + ":firefox driver");
            return new FirefoxDriver();
        } else if (BasicWebDriver.browser.equalsIgnoreCase("edge")) {
            LOGGER.info(message + ":edge driver");
            return new EdgeDriver();
        }
        LOGGER.error(message + ":not found");
        throw new RuntimeException(message + ":not found");
    }

    /**
     * Method to tear down web driver
     */
    public static void tearDown() {
        WebDriver driver = driverThreadLocal.get();
        if(driver!=null){
            driver.close();
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}
