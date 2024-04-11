package org.veeva.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.veeva.assignment.BasicWebDriver;
import org.veeva.assignment.utilities.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Class for Mens shopping page of {@link NBAWarriorsHomePage}
 *
 * @author Saurabh Srivastava
 * @since 10-04-2024
 */
public class MensShoppingPage extends NBAWarriorsHomePage{

    private String URL = "https://shop.warriors.com/golden-state-warriors-men/t-14145130+ga-67+z-978556-2897172570";
    private By breadcrumbListBy = By.cssSelector("[typeof=\"BreadcrumbList\"]");

    private By jacketsBreadcrumbBy = By.cssSelector("[content=\"Golden State Warriors Men Jackets\"]");
    private By productCardBy = By.cssSelector(".product-card");
    private By productTitleCardBy = By.cssSelector("[data-talos='linkSearchResult']");
    private By priceCardBY = By.cssSelector("[data-talos='srpProductPrice']");
    private By sellerMessageCardBy = By.cssSelector(".top-seller-vibrancy");

    private Map<String,By> departmentRadiosByMap = new HashMap<>();

    public MensShoppingPage(){
        departmentRadiosByMap.put("Jackets",By.cssSelector("[data-trk-id=\"all-departments\"] [data-trk-id=\"jackets\"]"));
        departmentRadiosByMap.put("Hoodies & Sweatshirts",By.cssSelector("[data-trk-id=\"all-departments\"] [data-trk-id=\"hoodies-sweatshirts\"]"));
        //add more as required
    }

    /**
     * Method to directly navigate to this page
     * @return {@link MensShoppingPage}
     */
    @Override
    public MensShoppingPage navigateToThisPage() {
        pageWebElements.navigateUsingURL(URL);
        return this;
    }

    /**
     * Method to select a department and apply that filter
     * @param departmentValue - the department filter you want to apply
     * @return - {@link MensShoppingPage} for chaining
     */
    public MensShoppingPage selectDepartment(String departmentValue){
        pageWebElements.click(departmentRadiosByMap.get(departmentValue),jacketsBreadcrumbBy);
        return this;
    }

    /**
     * Method to get all product cards
     * @return list of all product card web elements
     */
    private List<WebElement> getAllProductCard(){
        return pageWebElements.getWebElements(productCardBy);
    }

    /**
     * Method to get product titles
     * @return product titles
     */
    private String getProductTitle(WebElement element){
        WebElement e = pageWebElements.getWebElement(element,productTitleCardBy);
        return pageWebElements.getText(e);
    }

    /**
     * Method to get product price
     * @return product price
     */
    private String getProductPrice(WebElement element){
        WebElement e = pageWebElements.getWebElement(element,priceCardBY);
        return pageWebElements.getText(e);
    }

    /**
     * Method to get product seller message
     * @return product seller message
     */
    private String getProductSellerMessage(WebElement element){
        try {
            WebElement e = pageWebElements.getWebElement(element, sellerMessageCardBy);
            return pageWebElements.getText(e);
        }
        catch (NoSuchElementException e){
            return "NA";
        }
    }

    /**
     * Method to save all product details in a file
     *
     * @param fileName - the filename you want to create
     */
    public void saveAllProductDetails(String fileName){
        String fileFolderPath = "product_details";
        FileUtils.createFolder(fileFolderPath);
        String filePath = fileFolderPath + File.separator+ fileName;

        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append("Title\tPrice\tTop Seller Message\n");

        for (WebElement productCard : getAllProductCard()) {
            String title = this.getProductTitle(productCard);
            String price = this.getProductPrice(productCard);
            String topSellerMessage = this.getProductSellerMessage(productCard);
            contentBuilder.append(title).append("\t").append(price).append("\t").append(topSellerMessage).append("\n");
        }

        try {
            FileUtils.writeToFile(filePath, contentBuilder.toString());
            LOGGER.info("Data has been written to "+fileName+" in the product_details folder.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            BasicWebDriver.tearDown();
        }
    }

}
