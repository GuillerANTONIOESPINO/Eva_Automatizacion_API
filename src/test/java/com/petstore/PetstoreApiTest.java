package com.petstore;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PetstoreApiTest {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    // Paso 1: "Given que el sistema está listo para crear un pedido"
    @Given("que el sistema está listo para crear un pedido")
    public void que_el_sistema_esta_listo_para_crear_un_pedido() {
        System.out.println("Sistema listo para crear un pedido");
    }

    // Paso 2: "When el usuario crea un pedido con los siguientes detalles"
    @When("el usuario crea un pedido con los siguientes detalles:")
    public void el_usuario_crea_un_pedido_con_los_siguientes_detalles(DataTable dataTable) {
        String orderId = dataTable.cell(1, 0);
        String petId = dataTable.cell(1, 1);
        String quantity = dataTable.cell(1, 2);
        String shipDate = dataTable.cell(1, 3);
        String status = dataTable.cell(1, 4);
        String complete = dataTable.cell(1, 5);

        // Enviar el POST request para crear el pedido
        given()
                .contentType("application/json")
                .body("{\"id\": " + orderId + ", \"petId\": " + petId + ", \"quantity\": " + quantity +
                        ", \"shipDate\": \"" + shipDate + "\", \"status\": \"" + status + "\", \"complete\": " + complete + "}")
                .when()
                .post(BASE_URL + "/store/order")
                .then()
                .statusCode(200);
    }

    // Paso 3: "Then el sistema responde con el código de estado {int}"
    @Then("el sistema responde con el código de estado {int}")
    public void el_sistema_responde_con_el_codigo_de_estado(int statusCode) {
        then().statusCode(statusCode);
    }

    // Paso 4: "Then la respuesta contiene el id del pedido"
    @Then("la respuesta contiene el id del pedido")
    public void la_respuesta_contiene_el_id_del_pedido() {
        then()
                .body("id", notNullValue());
    }

    // Paso 5: "Given que el sistema tiene un pedido con id"
    @Given("que el sistema tiene un pedido con id {string}")
    public void que_el_sistema_tiene_un_pedido_con_id(String orderId) {
        given()
                .pathParam("orderId", orderId)
                .when()
                .get(BASE_URL + "/store/order/{orderId}")
                .then()
                .statusCode(200);
    }

    // Paso 6: "When el usuario consulta el pedido con id"
    @When("el usuario consulta el pedido con id {string}")
    public void el_usuario_consulta_el_pedido_con_id(String orderId) {
        given()
                .pathParam("orderId", orderId)
                .when()
                .get(BASE_URL + "/store/order/{orderId}")
                .then()
                .statusCode(200);
    }
}
