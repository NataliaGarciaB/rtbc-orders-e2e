package com.rtbc.orders.e2e.runners;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

/** Ejecuta los features de aceptación con Cucumber y los reporta en Serenity. */
@Suite
@IncludeEngines("cucumber")
@SelectPackages("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.rtbc.orders.e2e.stepdefinitions")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,
		value = "net.serenitybdd.cucumber.core.plugin.SerenityReporterParallel,pretty")
public class AceptacionIT {
}
