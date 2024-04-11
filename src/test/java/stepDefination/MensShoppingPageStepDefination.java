package stepDefination;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.veeva.assignment.pages.MensShoppingPage;

public class MensShoppingPageStepDefination {
    private MensShoppingPage mensShoppingPage = new MensShoppingPage();

    @Given("^the user select the department:(\\w+)$")
    public void selectDepartment(String department) {
        mensShoppingPage.selectDepartment(department);
    }

    @And("^the user saves the product details in file:(.+)$")
    public void saveAllProductDetails(String fileName) {
        mensShoppingPage.saveAllProductDetails(fileName);
    }
}
