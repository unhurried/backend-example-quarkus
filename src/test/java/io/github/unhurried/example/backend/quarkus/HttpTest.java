package io.github.unhurried.example.backend.quarkus;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.github.unhurried.example.backend.quarkus.resource.TodoResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

@QuarkusTest
public class HttpTest {

    @Inject private TestHelper helper;

    @Test
    @TestHTTPEndpoint(TodoResource.class)
    public void testTodoResource() {
        RestAssured.given().auth().oauth2(helper.createToken())
            .when().get()
            .then().statusCode(200);
    }
}
