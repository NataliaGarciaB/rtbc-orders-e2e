package com.rtbc.orders.e2e.tasks;

import static com.rtbc.orders.e2e.utils.Memoria.ID_DE_LA_ORDEN;
import static com.rtbc.orders.e2e.utils.RecursosDeLaApi.ORDENES;

import com.rtbc.orders.e2e.models.SolicitudDeOrden;
import io.restassured.http.ContentType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

/** Crea una orden y recuerda su id para las tareas siguientes. */
public class CrearUnaOrden implements Task {

	private final SolicitudDeOrden solicitud;

	public CrearUnaOrden(SolicitudDeOrden solicitud) {
		this.solicitud = solicitud;
	}

	public static CrearUnaOrden con(SolicitudDeOrden solicitud) {
		return Tasks.instrumented(CrearUnaOrden.class, solicitud);
	}

	@Override
	@Step("{0} crea una orden")
	public <T extends Actor> void performAs(T actor) {
		actor.attemptsTo(Post.to(ORDENES).with(request -> request.contentType(ContentType.JSON).body(solicitud.comoJson())));
		actor.remember(ID_DE_LA_ORDEN, SerenityRest.lastResponse().jsonPath().getString("id"));
	}
}
