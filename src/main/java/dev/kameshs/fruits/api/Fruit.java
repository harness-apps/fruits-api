package dev.kameshs.fruits.api;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Entity;
import java.util.List;
import java.util.Objects;

@Entity
public class Fruit extends PanacheEntity {

  public String name;
  public String season;

  // get and store in lowercase
  public String getName() {
    return name.toLowerCase();
  }

  public void setName(String name) {
    this.name = name.toLowerCase();
  }

  // get and store in lowercase
  public String getSeason() {
    return season.toLowerCase();
  }

  public void setSeason(String season) {
    this.season = season.toLowerCase();
  }

  public static List<Fruit> fruitsBySeason(String season) {
    return find("season", season.toLowerCase()).list();
  }

  public static Fruit findByName(String name) {
    return find("name", name.toLowerCase()).firstResult();
  }
  
  @Override
  public String toString() {
    return "Fruit{" +
        "name='" + name + '\'' +
        ", season='" + season + '\'' +
        ", id=" + id +
        '}';
  }
}
