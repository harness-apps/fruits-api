package dev.kameshs.fruits.api;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@QuarkusTest
public class FruitsTest {
  String listPath = "/api/fruits";
  String addPath = "/api/fruits/add";

  @Test
  public void testAll() {
    String apple = "{ \"name\" : \"apple\", \"season\" : \"all\"}";
    String orange = "{ \"name\" : \"orange\", \"season\" : \"winter\"}";
    String banana = "{ \"name\" : \"banana\", \"season\" : \"summer\"}";
    RestAssured.given()
        .contentType(ContentType.JSON)
        .body(apple)
        .when()
        .post(addPath)
        .then()
        .statusCode(201);
    RestAssured.given()
        .contentType(ContentType.JSON)
        .body(orange)
        .when()
        .post(addPath)
        .then()
        .statusCode(201);
    RestAssured.given()
        .contentType(ContentType.JSON)
        .body(banana)
        .when()
        .post(addPath)
        .then()
        .statusCode(201);

    // List
    Fruit[] fruits = RestAssured.given()
        .when()
        .contentType(ContentType.JSON)
        .get(listPath)
        .then()
        .statusCode(200)
        .extract()
        .body().as(Fruit[].class);

    Assertions.assertThat(fruits.length).isEqualTo(3);
    System.out.println("ObjectId: " + fruits[0].id.toString());
    Fruit fruit = RestAssured
        .given()
        .when()
        .contentType(ContentType.JSON)
        .get("/api/fruits/{id}", fruits[0].id.toString())
        .then()
        .statusCode(200)
        .extract()
        .body().as(Fruit.class);

    Assertions.assertThat(fruit.id).isEqualTo(fruits[0].id);
    Assertions.assertThat(fruit.name).isEqualTo(fruits[0].name);
    Assertions.assertThat(fruit.season).isEqualTo(fruits[0].season);

    // Search by Name
    fruit = RestAssured
        .given()
        .contentType(ContentType.JSON)
        .when()
        .get("/api/fruits/search/{name}", "orange")
        .then()
        .statusCode(200)
        .extract()
        .body().as(Fruit.class);

    Assertions.assertThat(fruit.name).isEqualTo("orange");
    Assertions.assertThat(fruit.season).isEqualTo("winter");

    // Search by Season
    Fruit[] seasonalFruits = RestAssured
        .given()
        .contentType(ContentType.JSON)
        .when()
        .get("/api/fruits/search/season/{season}", "summer")
        .then()
        .statusCode(200)
        .extract()
        .body().as(Fruit[].class);

    Assertions.assertThat(seasonalFruits[0].name).isEqualTo("banana");
    Assertions.assertThat(seasonalFruits[0].season).isEqualTo("summer");

    // Delete
    System.out.println("Delete ObjectId: " + fruit.id.toString());
    RestAssured
        .given()
        .when()
        .contentType(ContentType.JSON)
        .delete("/api/fruits/{id}", fruit.id.toString())
        .then()
        .statusCode(204);

    fruits = RestAssured.given()
        .when()
        .contentType(ContentType.JSON)
        .get(listPath)
        .then()
        .statusCode(200)
        .extract()
        .body().as(Fruit[].class);

    Assertions.assertThat(fruits.length).isEqualTo(2);

    // Delete All
    RestAssured
        .given()
        .when()
        .contentType(ContentType.JSON)
        .delete("/api/fruits")
        .then()
        .statusCode(204);
  }

}
