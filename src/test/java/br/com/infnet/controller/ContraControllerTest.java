package br.com.infnet.controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.util.Optional.empty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.hamcrest.number.OrderingComparison.greaterThan;

public class ContraControllerTest {
    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9090;
    }
    @Test
    void deveRetornarSaldoComSucesso() {
        String numeroConta = "TEST123";
        JsonPath resposta = given()
                .when()
                .get("/contas")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();

        List<Map<String, Object>> contas = resposta.getList("");
        assertThat("A lista de contas não pode estar vazia", contas, not(empty()));
        Map<String, Object> conta = contas.get((int) (Math.random() * contas.size()));
        String numero = conta.get("numero").toString();

        given()
                .when()
                .get("/contas/" + numero + "/saldo")
                .then()
                .statusCode(200)
                .body("saldo", notNullValue())
                .body("saldo", greaterThanOrEqualTo(0));
    }


}
