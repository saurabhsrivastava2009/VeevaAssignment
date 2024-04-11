package org.veeva.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for NBA Sixer home page
 *
 * @author Saurabh Srivastava
 * @since 11-04-2024
 */
public class NBASixersHomePage extends BasePage {

    private String URL = BasePage.URL + "sixers";
    private By slidesBy = By.cssSelector("[role=\"tablist\"] [type=\"button\"]");
    private By biggerSlideBy = By.cssSelector(".TileHero_tileHeroTitle__2spiN");

    @Override
    public NBASixersHomePage navigateToThisPage() {
        pageWebElements.navigateUsingURL(URL);
        return this;
    }

    /**
     * Method to get web element of all the slides
     *
     * @return list of slides web element
     */
    private List<WebElement> getAllSlides() {
        return pageWebElements.getWebElements(slidesBy);
    }

    /**
     * Method to get titles of all slides
     *
     * @return - list of titles from all sides
     */
    public List<String> getAllSlideTitles() {
        List<String> titles = new ArrayList<>();
        for (WebElement allSlide : getAllSlides()) {
            titles.add(allSlide.getText());
        }
        return titles;
    }

    /**
     * Method to calculate slide duration
     *
     * @return average duration of slides
     */
    public double getAndcalculateSlideDuration() {
        long startTime = 0;
        long endTime = 0;
        long totalElapsedTimeMillis = 0;
        List<WebElement> slides = getAllSlides();
        pageWebElements.waitForElementVisibleBasedOnAttribute(slides.get(0), "aria-selected", "true");
        for (WebElement allSlide : slides) {
            startTime = System.currentTimeMillis();
            pageWebElements.waitForElementVisibleBasedOnAttribute(allSlide, "aria-selected", "true");
            endTime = System.currentTimeMillis();
            long difference = endTime - startTime;
            totalElapsedTimeMillis = totalElapsedTimeMillis + difference;
        }

        return Math.round(totalElapsedTimeMillis / ((double) getAllSlides().size() * 1000));
    }
}
