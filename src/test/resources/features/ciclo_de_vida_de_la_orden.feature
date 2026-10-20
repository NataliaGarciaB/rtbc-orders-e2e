# language: es
Característica: Ciclo de vida de una orden
  Como cliente de la tienda
  Quiero crear y gestionar mis órdenes
  Para recibir mis productos o cancelar una compra a tiempo

  @crear @RN-03
  Escenario: Crear una orden calcula el total y la deja CREADA
    Cuando Natalia crea una orden de 2 unidades del producto P001 a 100000 cada una
    Entonces la API responde con el código 201
    Y la orden queda en estado CREADA
    Y el total de la orden es 200000

  @consultar
  Escenario: Consultar una orden existente
    Dado que Natalia tiene una orden en estado CREADA
    Cuando consulta la orden
    Entonces la API responde con el código 200
    Y la orden queda en estado CREADA

  @actualizar-estado @RN-06
  Escenario: Recorrer el camino feliz hasta ENTREGADA
    Dado que Natalia tiene una orden en estado ENVIADA
    Cuando cambia el estado de la orden a ENTREGADA
    Entonces la API responde con el código 200
    Y la orden queda en estado ENTREGADA

  @cancelar
  Escenario: Cancelar una orden pagada
    Dado que Natalia tiene una orden en estado PAGADA
    Cuando cancela la orden
    Entonces la API responde con el código 200
    Y la orden queda en estado CANCELADA

  @RN-05
  Escenario: No se puede enviar una orden sin pagar
    Dado que Natalia tiene una orden en estado CREADA
    Cuando cambia el estado de la orden a ENVIADA
    Entonces la API responde con el código 400
    Y el código de error es ORDEN_NO_PAGADA

  @RN-04
  Escenario: Una orden entregada no se puede cancelar
    Dado que Natalia tiene una orden en estado ENTREGADA
    Cuando cancela la orden
    Entonces la API responde con el código 409
    Y el código de error es TRANSICION_INVALIDA
    Cuando consulta la orden
    Entonces la orden queda en estado ENTREGADA
