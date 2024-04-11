package stepDefination;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.sl.In;
import org.veeva.assignment.pages.NewsAndFeaturePage;

import java.util.HashMap;
import java.util.Map;

public class NewsAndFeaturePageStepDefination {

    private NewsAndFeaturePage newsAndFeaturePage = new NewsAndFeaturePage();
    private Map<Object, Integer> dynamicVariable = new HashMap<>();

    @Given("^the user counts the tiles on section (NEWS|VIDEOS) and saves in (\\w+)$")
    public void countTiles(String sectionName, String variableName) {
        int count = newsAndFeaturePage.countTilesForSection(sectionName);
        this.dynamicVariable.put(variableName, count);
    }

    @Then("^the user verifies the (\\w+) is (\\d+)$")
    public void verifyCount(String variableName, int expectedCount) {
        newsAndFeaturePage.verify(this.dynamicVariable.get(variableName), expectedCount, "Count is as expected");
    }
}
