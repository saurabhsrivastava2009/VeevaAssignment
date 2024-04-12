package org.veeva.assignment;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.veeva.assignment.utilities.CommonFrameworkLogger;
import org.veeva.assignment.utilities.DateUtils;

import java.io.File;
import java.io.IOException;

/**
 * This class will contain the functionality related to taking screenshot
 *
 * @author Saurabh Srivastava
 * @since 09-04-2024
 */
public class CaptureScreen {

    private static Logger LOGGER = new CommonFrameworkLogger(CaptureScreen.class).getLOGGER();

    /**
     * Method to take the screenshot
     * @throws Exception - throws exceptions if there is issue with FILE
     */
    public static void takeScreenSnapShot(){
        String currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
        String imageName = "Screenshot_"+currentMethodName+"_"+ DateUtils.getDateInThisPattern("dd-mm-yyyy h-m-s-ms") +".png";
        TakesScreenshot screenshot = (TakesScreenshot) BasicWebDriver.getWebDriver();
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("./screenshots/"+imageName+".png");
        try {
            FileUtils.copyFile(sourceFile,destinationFile);
        } catch (IOException e) {
            LOGGER.warn("Unable to take screenshot");
            e.printStackTrace();
        }
    }
}
