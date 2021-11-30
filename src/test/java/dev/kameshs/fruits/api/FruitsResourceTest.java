package dev.kameshs.fruits.api;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalToIgnoringCase;

@QuarkusTest
public class FruitsResourceTest {

  @Test
  public void testDefaultFruit() {
    Fruit defaultFruit = given()
      .when().get("/api/default")
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON)
      .extract()
      .jsonPath()
      .getObject("$", Fruit.class);
    assertThat(defaultFruit.name, equalToIgnoringCase("banana"));
  }

  @Test
  public void testGetFruits() {

    Fruit apple = new Fruit();
    apple.id = 8L;
    apple.name = "Apple";
    apple.season = "Fall";

    Fruit mango = new Fruit();
    mango.id = 1L;
    mango.name = "Mango";
    mango.season = "Spring";

    Collection<Fruit> fruits = given()
      .when()
      .get("/api/fruits")
      .then()
      .contentType(ContentType.JSON)
      .statusCode(200)
      .extract()
      .response()
      .jsonPath()
      .getList("$", Fruit.class);

    assertThat(fruits, hasSize(9));
    assertThat(fruits, hasItems(mango, apple));
  }

}