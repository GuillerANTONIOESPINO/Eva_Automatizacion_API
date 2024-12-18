Feature: PetStore API Tests

  Scenario: Crear un pedido (POST /store/order)
    Given que el sistema está listo para crear un pedido
    When el usuario crea un pedido con los siguientes detalles:
      |id|petId|quantity|shipDate             | status    | complete |
      |1 | 1   | 2       |2024-12-17T14:00:00Z | approved  | true     |
    Then el sistema responde con el código de estado 200
    And la respuesta contiene el id del pedido

  Scenario: Consultar un pedido (GET /store/order/{orderId})
    Given que el sistema tiene un pedido con id
    When el usuario consulta el pedido con id
    Then el sistema responde con el código de estado 200
    And la respuesta contiene el id del pedido
