package com.rtbc.orders.e2e.tasks;

import static com.rtbc.orders.e2e.utils.Memoria.idDeLaOrden;
import static com.rtbc.orders.e2e.utils.RecursosDeLaApi.ID;
import static com.rtbc.orders.e2e.utils.RecursosDeLaApi.ORDEN;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Delete;

/** Cancela la orden recordada (cancelación lógica, DELETE /orders/{id}). */
public class CancelarLaOrden implements Task {

	public static CancelarLaOrden recordada() {
		return Tasks.instrumented(CancelarLaOrden.class);
	}

	@Override
	@Step("{0} cancela la orden")
	public <T extends Actor> void performAs(T actor) {
		actor.attemptsTo(Delete.from(ORDEN).with(request -> request.pathParam(ID, idDeLaOrden(actor))));
	}
}
