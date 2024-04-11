package org.veeva.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for NBA Warriors home page
 *
 * @author Saurabh Srivastava
 * @since 10-04-2024
 */
public class NBAWarriorsHomePage extends BasePage {

    private String URL = BasePage.URL +"warriors";
    private By homeMenuBy = By.cssSelector("[aria-label=\"header-primary-menu\"]");
    private By popUpCloseBy = By.cssSelector(".right-3");
    private By menuItemsBy = By.cssSelector("[role=\"menuitem\"]");

    private Map<String,By> mainMenuAriaControlMap = new HashMap<>();

    public NBAWarriorsHomePage(){
        mainMenuAriaControlMap.put("Tickets",By.cssSelector("[aria-controls=\"nav-dropdown-1099074\"]"));
        mainMenuAriaControlMap.put("Schedule",By.cssSelector("[aria-controls=\"nav-dropdown-1016599\"]"));
        mainMenuAriaControlMap.put("Team",By.cssSelector("[aria-controls=\"nav-dropdown-1059583\"]"));
        mainMenuAriaControlMap.put("Shop",By.cssSelector("[aria-controls=\"nav-dropdown-1059653\"]"));
        mainMenuAriaControlMap.put("Chase Center",By.cssSelector("[aria-controls=\"nav-dropdown-1059654\"]"));
        mainMenuAriaControlMap.put("My Warrior Account",By.cssSelector("[aria-controls=\"nav-dropdown-1078363\"]"));
        mainMenuAriaControlMap.put("3 Dots",By.cssSelector("[aria-controls=\"nav-dropdown-1058447\"]"));
    }


    /**
     * Method to navigate to the {@link NBAWarriorsHomePage}
     * @return {@link NBAWarriorsHomePage} for chaining
     */
    @Override
    public NBAWarriorsHomePage navigateToThisPage(){
        pageWebElements.navigateUsingURL(URL);
        return this;
    }

    /**
     * Method to close the pop-up that appears when we navigate to the page
     * @return {@link NBAWarriorsHomePage} for chaining
     */
    public NBAWarriorsHomePage closePopUp(){
        pageWebElements.waitForElementVisible(popUpCloseBy, true);
        if(pageWebElements.isElementVisible(popUpCloseBy)){
            pageWebElements.click(popUpCloseBy,homeMenuBy);
        }
        return this;
    }

    /**
     * Method to select a menu from the main menu bar
     * @param mainMenuValue - the value from the main menu bar that you would like to click
     * @param subMenuValue - the value from the submenu that you would like to click
     */
    public void selectMenuItem(String mainMenuValue, String subMenuValue){
        WebElement element = pageWebElements.getWebElement(mainMenuAriaControlMap.get(mainMenuValue));
        pageWebElements.hover(element);
        for (WebElement webElement : pageWebElements.getWebElements(element, menuItemsBy)) {
            if (pageWebElements.getText(webElement).contains(subMenuValue)) {
                pageWebElements.click(webElement);
                break;
            }
        }
    }
}
