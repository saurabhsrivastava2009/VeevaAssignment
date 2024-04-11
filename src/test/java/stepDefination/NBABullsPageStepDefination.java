package stepDefination;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.veeva.assignment.pages.NBABullsHomePage;

public class NBABullsPageStepDefination {

    private NBABullsHomePage nbaBullsHomePage = new NBABullsHomePage();

    @Given("^the user navigates to NBA Bulls Home Page$")
    public void navigateToThisPage(){
        nbaBullsHomePage.navigateToThisPage();
    }

    @And("^the user saves the footer links in file:(.+)$")
    public void saveAllProductDetails(String fileName){
        nbaBullsHomePage.saveAllFooterLinks(fileName);
    }
}
