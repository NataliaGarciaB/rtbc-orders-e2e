package com.rtbc.orders.e2e.utils;

import net.serenitybdd.screenplay.Actor;

/** Lo que el actor recuerda entre tareas (memoria de Screenplay). */
public final class Memoria {

	public static final String ID_DE_LA_ORDEN = "id de la orden";

	private Memoria() {
	}

	/** Id de la última orden que creó el actor. */
	public static String idDeLaOrden(Actor actor) {
		return actor.recall(ID_DE_LA_ORDEN);
	}
}
