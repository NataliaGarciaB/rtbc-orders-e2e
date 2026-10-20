package com.rtbc.orders.e2e.models;

import java.math.BigDecimal;
import java.util.Map;

/** Una línea de la orden con los nombres de campo del contrato de la API. */
public record LineaDeProducto(String id, int cantidad, BigDecimal valorUnitario) {

	public Map<String, Object> comoJson() {
		return Map.of("id", id, "cantidad", cantidad, "valorUnitario", valorUnitario);
	}
}
