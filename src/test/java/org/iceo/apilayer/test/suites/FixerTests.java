package org.iceo.apilayer.test.suites;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"},
        features = "src/test/resources/features/fixer",
        glue = "org/iceo/apilayer/steps",
        tags = "@Fixer")
public class FixerTests {
}

