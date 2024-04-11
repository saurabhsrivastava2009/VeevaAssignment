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

    public CommonFrameworkLogger(Class clazz) {
        LOGGER = Logger.getLogger(clazz.getName());
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
    }

    public Logger getLOGGER() {
        return LOGGER;
    }
}
