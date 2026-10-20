package com.rtbc.orders.e2e.stepdefinitions;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.equalTo;

import com.rtbc.orders.e2e.models.Estado;
import com.rtbc.orders.e2e.models.SolicitudDeOrden;
import com.rtbc.orders.e2e.questions.LaOrden;
import com.rtbc.orders.e2e.questions.LaRespuesta;
import com.rtbc.orders.e2e.tasks.CambiarElEstadoDeLaOrden;
import com.rtbc.orders.e2e.tasks.CancelarLaOrden;
import com.rtbc.orders.e2e.tasks.ConsultarLaOrden;
import com.rtbc.orders.e2e.tasks.CrearUnaOrden;
import com.rtbc.orders.e2e.tasks.LlevarUnaOrdenAlEstado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import java.math.BigDecimal;

/** Traduce el Gherkin a tareas y preguntas; no conoce HTTP. */
public class CicloDeVidaDeLaOrdenSteps {

	@Dado("que {word} tiene una orden en estado {word}")
	public void tieneUnaOrdenEnEstado(String nombre, String estado) {
		theActorCalled(nombre).wasAbleTo(LlevarUnaOrdenAlEstado.enEstado(Estado.valueOf(estado)));
	}

	@Cuando("{word} crea una orden de {int} unidades del producto {word} a {bigdecimal} cada una")
	public void creaUnaOrden(String nombre, int cantidad, String producto, BigDecimal valorUnitario) {
		theActorCalled(nombre).attemptsTo(
				CrearUnaOrden.con(SolicitudDeOrden.deUnProducto(producto, cantidad, valorUnitario)));
	}

	@Cuando("cambia el estado de la orden a {word}")
	public void cambiaElEstado(String estado) {
		theActorInTheSpotlight().attemptsTo(CambiarElEstadoDeLaOrden.a(Estado.valueOf(estado)));
	}

	@Cuando("cancela la orden")
	public void cancelaLaOrden() {
		theActorInTheSpotlight().attemptsTo(CancelarLaOrden.recordada());
	}

	@Cuando("consulta la orden")
	public void consultaLaOrden() {
		theActorInTheSpotlight().attemptsTo(ConsultarLaOrden.recordada());
	}

	@Entonces("la API responde con el código {int}")
	public void laApiRespondeCon(int codigoHttp) {
		theActorInTheSpotlight().should(seeThat(LaRespuesta.codigoHttp(), equalTo(codigoHttp)));
	}

	@Y("la orden queda en estado {word}")
	public void laOrdenQuedaEnEstado(String estado) {
		theActorInTheSpotlight().should(seeThat(LaOrden.estado(), equalTo(Estado.valueOf(estado))));
	}

	@Y("el total de la orden es {bigdecimal}")
	public void elTotalEs(BigDecimal total) {
		theActorInTheSpotlight().should(seeThat(LaOrden.total(), comparesEqualTo(total)));
	}

	@Y("el código de error es {word}")
	public void elCodigoDeErrorEs(String codigo) {
		theActorInTheSpotlight().should(seeThat(LaRespuesta.codigoDeError(), equalTo(codigo)));
	}
}
