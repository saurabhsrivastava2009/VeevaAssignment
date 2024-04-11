package stepDefination;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.veeva.assignment.pages.NBASixersHomePage;

import java.util.ArrayList;
import java.util.List;

public class NBASixersHomePageStepDefination {
    private NBASixersHomePage nbaSixersHomePage = new NBASixersHomePage();
    private List<String> allSlideTitles = new ArrayList<>();

    @Given("^the user navigates to NBA Sixers Home Page$")
    public void navigateToThisPage() {
        nbaSixersHomePage.navigateToThisPage();
    }

    @Given("^the user captures the titles of all slides$")
    public void captureSlideTitles() {
        allSlideTitles = nbaSixersHomePage.getAllSlideTitles();
    }

    @Then("^the user verifies the slide titles:$")
    public void verifySlideTitles(List<String> titles) {
        if (allSlideTitles.size() != titles.size()) {
            nbaSixersHomePage.verify(allSlideTitles.size(), titles.size(), "Slides count is not as expected");
        } else {
            for (int i = 0; i < allSlideTitles.size(); i++) {
                nbaSixersHomePage.verify(allSlideTitles.get(i), titles.get(i), "Titles are matching:" + titles.get(i));
            }
        }
    }

    @Then("^the user verifies the slides duration as approx of (\\d+) seconds$")
    public void verifySlideDuration(double expectedDuration) {
        double actualDuration = nbaSixersHomePage.getAndcalculateSlideDuration();
        nbaSixersHomePage.verify(actualDuration, expectedDuration, "Duration is as expected");
    }
}
