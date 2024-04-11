package org.veeva.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.veeva.assignment.BasicWebDriver;
import org.veeva.assignment.utilities.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Class for NBA Bulls page
 *
 * @author Saurabh Srivastava
 * @since 11-04-2024
 */
public class NBABullsHomePage extends BasePage {

    private String URL = BasePage.URL + "bulls";
    private By footerListBy = By.cssSelector("[data-testid=\"footer-list-item\"]");
    private By linkBy = By.cssSelector(".text-sm");

    @Override
    public NBABullsHomePage navigateToThisPage() {
        pageWebElements.navigateUsingURL(URL);
        return this;
    }

    /**
     * Method to get all footer web elements
     *
     * @return list of all footer elements
     */
    private List<WebElement> getAllFooterElements() {
        return pageWebElements.getWebElements(footerListBy);
    }

    /**
     * Method to get footer text
     *
     * @return footer text
     */
    private String getFooterName(WebElement element) {
        return pageWebElements.getText(element);
    }

    /**
     * Method to get footer link
     *
     * @return footer link
     */
    private String getFooterLink(WebElement element) {
        WebElement webElement = pageWebElements.getWebElement(element, linkBy);
        return pageWebElements.getAttributeValue(webElement, "href");
    }

    /**
     * Method to save all footer links
     *
     * @param fileName - the filename you want to create
     */
    public void saveAllFooterLinks(String fileName) {
        String fileFolderPath = "product_details";
        FileUtils.createFolder(fileFolderPath);
        String filePath = fileFolderPath + File.separator + fileName;

        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append("Name,Link,isDuplicate\n");

        for (WebElement productCard : getAllFooterElements()) {
            String footerName = this.getFooterName(productCard);
            String footerLink = this.getFooterLink(productCard);
            boolean isLinkPresent = contentBuilder.toString().contains(footerLink);
            contentBuilder.append(footerName).append(",").append(footerLink).append(",").append(isLinkPresent).append("\n");
        }

        try {
            FileUtils.writeToFile(filePath, contentBuilder.toString());
            LOGGER.info("Data has been written to " + fileName + " in the product_details folder.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            BasicWebDriver.tearDown();
        }
    }
}
