package com.rtbc.orders.e2e.tasks;

import static com.rtbc.orders.e2e.utils.Memoria.idDeLaOrden;
import static com.rtbc.orders.e2e.utils.RecursosDeLaApi.ID;
import static com.rtbc.orders.e2e.utils.RecursosDeLaApi.ORDEN;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

/** Consulta la orden recordada (GET /orders/{id}). */
public class ConsultarLaOrden implements Task {

	public static ConsultarLaOrden recordada() {
		return Tasks.instrumented(ConsultarLaOrden.class);
	}

	@Override
	@Step("{0} consulta la orden")
	public <T extends Actor> void performAs(T actor) {
		actor.attemptsTo(Get.resource(ORDEN).with(request -> request.pathParam(ID, idDeLaOrden(actor))));
	}
}
