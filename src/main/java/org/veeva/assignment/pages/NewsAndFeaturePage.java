package org.veeva.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Class for New and Feature section of {@link NBAWarriorsHomePage}
 *
 * @author Saurabh Srivastava
 * @since 10-04-2024
 */
public class NewsAndFeaturePage extends NBAWarriorsHomePage {
    private String URL = "https://www.nba.com/warriors/news";
    private By pageSectionBy = By.cssSelector(".ColumnsComponents_container__YMzra");
    private By pageHeaderBy = By.cssSelector("[data-testid=\"heading\"]");
    private By titleArticleBy = By.cssSelector("[data-testid=\"tile-article\"]");

    /**
     * Method to directly navigate to this page
     *
     * @return {@link NewsAndFeaturePage}
     */
    @Override
    public NewsAndFeaturePage navigateToThisPage() {
        pageWebElements.navigateUsingURL(URL);
        return this;
    }

    /**
     * Method to scroll to the page section
     *
     * @param sectionName - the section to which you want to scroll to
     * @return {@link NewsAndFeaturePage} for chaining
     */
    public NewsAndFeaturePage scrollToSection(String sectionName) {
        WebElement element = pageWebElements.getSubSection(sectionName, pageSectionBy, pageHeaderBy);
        pageWebElements.scroll(element);
        return this;
    }

    /**
     * Method to get the tiles count for the required section
     *
     * @param sectionName - the section name for which you want the count
     * @return - count of tiles
     */
    public int countTilesForSection(String sectionName) {
        for (WebElement webElement : pageWebElements.getWebElements(pageSectionBy)) {
            WebElement element = pageWebElements.getWebElement(webElement, pageHeaderBy);
            if (pageWebElements.getText(element).contains(sectionName)) {
                return pageWebElements.getWebElements(webElement, titleArticleBy).size() + 1;
            }
        }
        return 0;
    }
}
