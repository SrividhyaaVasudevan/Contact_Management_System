package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources/Features/login.feature",
	    glue = {"StepDefinition"},
	    plugin = {
	        "pretty", "html:target/CucumberReports/Report.html"
	    },
	    monochrome = true
	)

public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
