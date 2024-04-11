package org.veeva.assignment.utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * The class which will handle the logging
 *
 * @author Saurabh Srivastava
 * @since 09-04-2024
 */
public class CommonFrameworkLogger {
    private static Logger LOGGER;

    /**
     * Method to initialise logger for respective class
     *
     * @param clazz - the class for which logger is initialised
     */
    public CommonFrameworkLogger(Class clazz) {
        LOGGER = Logger.getLogger(clazz.getName());
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
    }

    /**
     * Method to get the {@link Logger} object
     *
     * @return {@link Logger} object
     */
    public Logger getLOGGER() {
        return LOGGER;
    }
}
