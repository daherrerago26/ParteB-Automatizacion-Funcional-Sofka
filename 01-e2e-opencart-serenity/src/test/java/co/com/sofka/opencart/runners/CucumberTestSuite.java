package co.com.sofka.opencart.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

// Ejecuta con Cucumber todos los features de src/test/resources/features y reporta a Serenity
@Suite
@IncludeEngines("cucumber")
@SelectPackages("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "co.com.sofka.opencart")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "net.serenitybdd.cucumber.core.plugin.SerenityReporterParallel,pretty")
public class CucumberTestSuite {
}
