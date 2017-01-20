package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

/**
 * Created by Nick on 15/01/2017.
 */

//@RunWith(Cucumber.class) -- JUnit
@CucumberOptions(features = {"src/main/resources"}, format = {"json:target/cucumber.json", "html:target/site/cucumber-pretty"}, glue = {"steps"})
public class TestRunner extends AbstractTestNGCucumberTests {
}
