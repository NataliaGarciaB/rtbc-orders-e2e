package com.rtbc.orders.e2e.tasks;

import com.rtbc.orders.e2e.models.Estado;
import com.rtbc.orders.e2e.models.SolicitudDeOrden;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

/**
 * Prepara una orden en un estado dado recorriendo el camino válido del ciclo de vida. Se compone de otras tareas en
 * lugar de repetir sus pasos.
 */
public class LlevarUnaOrdenAlEstado implements Task {

	private static final Map<Estado, List<Estado>> CAMINOS = Map.of(
			Estado.CREADA, List.of(),
			Estado.PAGADA, List.of(Estado.PAGADA),
			Estado.ENVIADA, List.of(Estado.PAGADA, Estado.ENVIADA),
			Estado.ENTREGADA, List.of(Estado.PAGADA, Estado.ENVIADA, Estado.ENTREGADA),
			Estado.CANCELADA, List.of(Estado.CANCELADA));

	private final Estado estado;

	public LlevarUnaOrdenAlEstado(Estado estado) {
		this.estado = estado;
	}

	public static LlevarUnaOrdenAlEstado enEstado(Estado estado) {
		return Tasks.instrumented(LlevarUnaOrdenAlEstado.class, estado);
	}

	@Override
	@Step("{0} prepara una orden en estado #estado")
	public <T extends Actor> void performAs(T actor) {
		actor.attemptsTo(CrearUnaOrden.con(SolicitudDeOrden.deUnProducto("P001", 1, new BigDecimal("100000"))));
		CAMINOS.get(estado).forEach(paso -> actor.attemptsTo(CambiarElEstadoDeLaOrden.a(paso)));
	}
}
