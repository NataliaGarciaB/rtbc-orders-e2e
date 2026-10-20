package com.rtbc.orders.e2e.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/** Datos para crear una orden. El total no se envía: lo calcula la API (RN-03). */
public record SolicitudDeOrden(String cliente, List<LineaDeProducto> productos) {

	private static final String CLIENTE_POR_DEFECTO = "Natalia Garcia";

	/** Una orden con un único producto, para los escenarios donde el cliente no es relevante. */
	public static SolicitudDeOrden deUnProducto(String producto, int cantidad, BigDecimal valorUnitario) {
		return new SolicitudDeOrden(CLIENTE_POR_DEFECTO, List.of(new LineaDeProducto(producto, cantidad, valorUnitario)));
	}

	public Map<String, Object> comoJson() {
		return Map.of("cliente", cliente, "productos", productos.stream().map(LineaDeProducto::comoJson).toList());
	}
}
