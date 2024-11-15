package testRunner;
// Importing necessary classes for Cucumber and TestNG integration
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
// Annotation to specify Cucumber options
@CucumberOptions(
        features = "src/main/Features", // Path to the feature files
        glue = "StepDef", // Path to step definitions
        plugin = {
                "pretty",
                "html:target/cucumber-html-report", // HTML report
                "json:target/cucumber.json", // JSON report for advanced reporting
                "junit:target/cucumber.xml" // JUnit XML report
        },
        monochrome = true // For clean console output
)
public class Runners extends AbstractTestNGCucumberTests {
        // This class serves as the entry point for executing Cucumber tests with TestNG.
        // It extends AbstractTestNGCucumberTests, which integrates Cucumber with the TestNG testing framework.
}
