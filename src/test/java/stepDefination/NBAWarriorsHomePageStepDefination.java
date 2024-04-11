package stepDefination;

import io.cucumber.java.en.Given;
import org.veeva.assignment.pages.NBAWarriorsHomePage;

public class NBAWarriorsHomePageStepDefination{
    private NBAWarriorsHomePage nbaWarriorsHomePage = new NBAWarriorsHomePage();

    @Given("^the user navigates to NBA Warriors Home Page$")
    public void navigateToThisPage(){
        nbaWarriorsHomePage.navigateToThisPage().closePopUp();
    }

    @Given("^the user selects (Tickets|Schedule|Team|Shop|Chase Center|My Warrior Account|3 Dots) and sub-menu as (.+)$")
    public void selectMenuItem(String mainMenu, String subMenu){
        nbaWarriorsHomePage.selectMenuItem(mainMenu,subMenu);
    }

    @Given("^the user switches to the (.+) tab$")
    public void switchToMenShoppingPage(String windowTitle){
        nbaWarriorsHomePage.switchToWindow(windowTitle);
    }
}
