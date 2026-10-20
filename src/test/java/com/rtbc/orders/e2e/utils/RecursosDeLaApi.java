package com.rtbc.orders.e2e.utils;

/** Rutas de la API en un solo lugar: si cambian, solo cambia esta clase. */
public final class RecursosDeLaApi {

	public static final String ORDENES = "/orders";
	public static final String ORDEN = "/orders/{id}";
	public static final String ESTADO_DE_LA_ORDEN = "/orders/{id}/status";
	public static final String ID = "id";

	private RecursosDeLaApi() {
	}
}
