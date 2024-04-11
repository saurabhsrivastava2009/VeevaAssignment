import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.veeva.assignment.BasicWebDriver;

@CucumberOptions(
        features = {"src/test/features"},
        glue = {"stepDefination"},
        tags = ""
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        BasicWebDriver.tearDown();
    }
}
