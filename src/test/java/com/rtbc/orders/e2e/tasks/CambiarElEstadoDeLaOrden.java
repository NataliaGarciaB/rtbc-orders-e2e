package com.rtbc.orders.e2e.tasks;

import static com.rtbc.orders.e2e.utils.Memoria.idDeLaOrden;
import static com.rtbc.orders.e2e.utils.RecursosDeLaApi.ESTADO_DE_LA_ORDEN;
import static com.rtbc.orders.e2e.utils.RecursosDeLaApi.ID;

import com.rtbc.orders.e2e.models.Estado;
import io.restassured.http.ContentType;
import java.util.Map;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Patch;

/** Pide llevar la orden recordada a un nuevo estado (PATCH /orders/{id}/status). */
public class CambiarElEstadoDeLaOrden implements Task {

	private final Estado destino;

	public CambiarElEstadoDeLaOrden(Estado destino) {
		this.destino = destino;
	}

	public static CambiarElEstadoDeLaOrden a(Estado destino) {
		return Tasks.instrumented(CambiarElEstadoDeLaOrden.class, destino);
	}

	@Override
	@Step("{0} cambia el estado de la orden a #destino")
	public <T extends Actor> void performAs(T actor) {
		actor.attemptsTo(Patch.to(ESTADO_DE_LA_ORDEN).with(request -> request
				.pathParam(ID, idDeLaOrden(actor))
				.contentType(ContentType.JSON)
				.body(Map.of("estado", destino.name()))));
	}
}
