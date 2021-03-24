import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;


public class PostsApiTest {
    private final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://jsonplaceholder.typicode.com")
            .setBasePath("/posts/")
            .build();

    @Test
    void shouldReturnPostsByCorrectUserId() {
        given()
                .spec(requestSpec)
                .when()
                .get("?userId=1")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath("accounts.schema.json"))
                .body("userId", Matchers.everyItem(is(1)))
        ;
    }

    @Test
    void shouldReturnEmptyListByIncorrectUserId() {
        given()
                .spec(requestSpec)
                .when()
                .get("?userId=0")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath("accounts.schema.json"))
                .body("isEmpty()", Matchers.is(true))
        ;
    }

    @Test
    void shouldReturnEmptyListByEmptyUserId() {
        given()
                .spec(requestSpec)
                .when()
                .get("?userId=")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath("accounts.schema.json"))
                .body("isEmpty()", Matchers.is(true))
        ;
    }

    @Test
    void shouldGetResourceByCorrectId() {
        given()
                .spec(requestSpec)
                .when()
                .get("1")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath("accounts.schema.json"))
                .body("id", Matchers.is(1))
        ;
    }

    @Test
    void shouldReturnEmptyListByIncorrectId() {
        given()
                .spec(requestSpec)
                .when()
                .get("0")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath("accounts.schema.json"))
                .body("isEmpty()", Matchers.is(true))
        ;
    }

    @Test
    void shouldReturnListOfAllResources() {
        given()
                .spec(requestSpec)
                .when()
                .get("")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath("accounts.schema.json"))
                .body("", Matchers.hasSize(100))
        ;
    }
}
