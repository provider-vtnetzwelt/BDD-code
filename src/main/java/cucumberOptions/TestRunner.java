package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(
		dryRun = false,
		publish = true,
		monochrome = true,
		features = "C:/Users/netzwelt/Documents/HnH_app/src/main/java/Features/qaexam.feature",
        glue = {"stepDefinations"}, 
        plugin = {"pretty"})
      

public class TestRunner {

}
