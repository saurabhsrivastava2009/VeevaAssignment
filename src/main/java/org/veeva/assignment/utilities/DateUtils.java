package org.veeva.assignment.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class (will) contains methods related to Date
 *
 * @author Saurabh Srivastava
 * @since 10-04-2024
 */
public class DateUtils {

    private static DateFormat dateFormat;
    private static Date date = new Date();

    /**
     * Method to return a date in string based on the specified pattern
     *
     * @param pattern - the pattern in which you want the date e.g. dd-mm-yyyy h-m-s
     * @return - the date
     */
    public static String getDateInThisPattern(String pattern) {
        dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }
}
