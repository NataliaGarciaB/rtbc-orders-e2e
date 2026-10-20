package com.rtbc.orders.e2e.questions;

import com.rtbc.orders.e2e.models.Estado;
import io.restassured.path.json.config.JsonPathConfig;
import java.math.BigDecimal;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;

/** Lo que el actor observa de la orden devuelta en la última respuesta. */
public final class LaOrden {

	private static final JsonPathConfig MONTOS_EXACTOS =
			JsonPathConfig.jsonPathConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL);

	private LaOrden() {
	}

	public static Question<Estado> estado() {
		return Question.about("el estado de la orden")
				.answeredBy(actor -> Estado.valueOf(SerenityRest.lastResponse().jsonPath().getString("estado")));
	}

	public static Question<BigDecimal> total() {
		return Question.about("el total de la orden")
				.answeredBy(actor -> SerenityRest.lastResponse().jsonPath(MONTOS_EXACTOS).get("total"));
	}
}
