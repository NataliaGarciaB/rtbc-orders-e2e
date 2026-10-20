package com.rtbc.orders.e2e.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;

/** Lo que el actor observa de la última respuesta HTTP. */
public final class LaRespuesta {

	private LaRespuesta() {
	}

	public static Question<Integer> codigoHttp() {
		return Question.about("el código HTTP de la respuesta")
				.answeredBy(actor -> SerenityRest.lastResponse().statusCode());
	}

	public static Question<String> codigoDeError() {
		return Question.about("el código de error de la respuesta")
				.answeredBy(actor -> SerenityRest.lastResponse().jsonPath().getString("codigo"));
	}
}
