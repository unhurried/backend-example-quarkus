package io.github.unhurried.example.backend.quarkus;

import static org.hamcrest.Matchers.equalTo;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.github.unhurried.example.backend.quarkus.resource.TodoResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

@QuarkusTest
class HttpTest {

    @Inject private TestHelper helper;

    @Test
    @TestHTTPEndpoint(TodoResource.class)
    void testTodoResource() {
        RestAssured.given().auth().oauth2(helper.createToken())
            .when().get()
            .then().statusCode(200);
    }

    @Test
    @TestHTTPEndpoint(TodoResource.class)
    void testAuthErrorNoToken() {
        RestAssured.given()
            .when().get()
            .then()
                .statusCode(401)
                .body("error", equalTo("unauthorized"));
    }

    @Test
    @TestHTTPEndpoint(TodoResource.class)
    void testAuthErrorInvalidToken() {
        RestAssured.given().auth().oauth2("xxx")
            .when().get()
            .then()
                .statusCode(401)
                .body("error", equalTo("authentication_failed"));
    }

    @Test
    @TestHTTPEndpoint(TodoResource.class)
    void testAuthErrorExpiredToken() {
        RestAssured.given().auth().oauth2(helper.createExpiredToken())
            .when().get()
            .then()
                .statusCode(401)
                .body("error", equalTo("authentication_failed"));
    }

}
