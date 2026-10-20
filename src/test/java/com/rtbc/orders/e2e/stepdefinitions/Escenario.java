package com.rtbc.orders.e2e.stepdefinitions;

import io.cucumber.java.Before;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.environment.SystemEnvironmentVariables;

/** Prepara el escenario: cada actor puede llamar a la API del entorno elegido. */
public class Escenario {

	@Before
	public void prepararElEscenario() {
		String urlBase = EnvironmentSpecificConfiguration
				.from(SystemEnvironmentVariables.currentEnvironmentVariables())
				.getProperty("restapi.baseurl");
		OnStage.setTheStage(Cast.whereEveryoneCan(CallAnApi.at(urlBase)));
	}
}
