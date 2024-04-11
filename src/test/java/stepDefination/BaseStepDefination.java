package stepDefination;

import io.cucumber.java.*;
import org.apache.log4j.Logger;
import org.veeva.assignment.BasicWebDriver;
import org.veeva.assignment.utilities.CommonFrameworkLogger;

public class BaseStepDefination {

    private static Logger LOGGER = new CommonFrameworkLogger(BaseStepDefination.class).getLOGGER();

    @Before
    public void setup(Scenario scenario) {
        LOGGER.info("Setting up Webdriver");
        BasicWebDriver.driver = BasicWebDriver.getWebDriver();
        LOGGER.info("Starting Scenario:" + scenario.getName());
    }

    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            handleFail(scenario);
        } else {
            handlePass(scenario);
        }
        BasicWebDriver.tearDown();
    }

    public void handlePass(Scenario scenario) {
        LOGGER.info("Scenario Passing:" + scenario.getName());
    }

    public void handleFail(Scenario scenario) {
        LOGGER.error("Scenario failed:" + scenario.getName());
        LOGGER.error("Scenario failed at line:" + scenario.getLine());
    }
}
