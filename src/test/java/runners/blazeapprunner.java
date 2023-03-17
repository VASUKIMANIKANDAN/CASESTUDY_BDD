package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		
		features="src//test//resources//featureFiles//Demo.feature",
		glue={"stepDefs"},
		monochrome=true,
		dryRun=false,
		plugin= {"pretty",
				"html:target//Reports//HTMLReport.html",}
		)

public class blazeapprunner extends AbstractTestNGCucumberTests {
}