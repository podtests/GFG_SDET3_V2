import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(tags = "@JIRA-3213",
        features = {"src/test/resources/e2e.feature"},
        glue = {"stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber.html"},
        dryRun = false
)
public class Runner extends AbstractTestNGCucumberTests {
}
