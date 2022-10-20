package dev.kameshs.fruits.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.vertx.core.json.JsonObject;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FruitsResourceTest {

  @Test
  @Order(1)
  public void testReadiness() {
    given()
        .when()
        .get("/q/health/ready")
        .then()
        .statusCode(200)
        .body("status", is("UP"));
  }

  @Test
  @Order(2)
  public void testDefaultFruit() {
    given()
        .when()
        .get("/api/fruits/default")
        .then()
        .statusCode(200)
        .body("name", is("banana"));
  }

  @Test
  @Order(3)
  public void testListFruits() {
    ValidatableResponse response = given()
        .when()
        .get("/api/fruits")
        .then()
        .contentType(ContentType.JSON)
        .statusCode(200);

    ArrayList<Map<String, ?>> jsonAsArrayList = response.extract().jsonPath().get();

    assertEquals(9, jsonAsArrayList.size());
    assertEquals("apple", jsonAsArrayList.get(0).get("name"));
    assertEquals("fall", jsonAsArrayList.get(0).get("season"));
  }

  @Test
  @Order(4)
  public void testAddFruit() {
    JsonObject fruit = new JsonObject()
        .put("name", "banana")
        .put("season", "all");
    given()
        .header("Content-type", "application/json")
        .and()
        .body(fruit.encode())
        .when()
        .post("/api/fruits/add")
        .then()
        .statusCode(201);
  }

  @Test
  @Order(5)
  public void testFruitsByName() {
    given()
        .when()
        .get("/api/fruits/search/{name}", "apple")
        .then()
        .contentType(ContentType.JSON)
        .body("name", is("apple"));
  }

  @Test
  @Order(6)
  public void testFruitsBySeason() {
    ValidatableResponse response = given()
        .when()
        .get("/api/fruits/season/{season}", "winter")
        .then()
        .contentType(ContentType.JSON)
        .statusCode(200);

    ArrayList<Map<String, ?>> jsonAsArrayList = response.extract().jsonPath().get();

    assertEquals(2, jsonAsArrayList.size());
    assertEquals("orange", jsonAsArrayList.get(0).get("name"));
    assertEquals("lemon", jsonAsArrayList.get(1).get("name"));
  }

  @Test
  @Order(7)
  public void testDeleteFruit() {
    given()
        .when()
        .delete("/api/fruits/10")
        .then()
        .statusCode(204);
  }

  @Test
  @Order(8)
  public void testDeleteNonExistFruit() {
    given()
        .when()
        .delete("/api/fruits/10")
        .then()
        .statusCode(404);
  }

  @Test
  @Order(9)
  public void testDeleteAll() {
    given()
        .when()
        .delete("/api/fruits")
        .then()
        .statusCode(204);
  }
}