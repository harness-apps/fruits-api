package dev.kameshs.fruits.api;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.List;

@MongoEntity(collection = "fruits", database = "demodb")
public class Fruit extends PanacheMongoEntity {

  public String name;
  public String season;

  // return name as lowercase in the model
  public String getName() {
    return name.toLowerCase();
  }

  // store all names in lowercase in the DB
  public void setName(String name) {
    this.name = name.toLowerCase();
  }

  // return season as lowercase in the model
  public String getSeason() {
    return season.toLowerCase();
  }

  // store all seasons in lowercase in the DB
  public void setSeason(String season) {
    this.season = season.toLowerCase();
  }

  public static List<Fruit> fruitsBySeason(String season) {
    return find("season", season).list();
  }

  public static Fruit fruitsByName(String name) {
    return find("name", name).firstResult();
  }
}
