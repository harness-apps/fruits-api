package dev.kameshs.fruits.api;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.smallrye.config.common.utils.StringUtil;

import javax.persistence.Entity;
import java.util.List;
import java.util.Objects;

@Entity
public class Fruit extends PanacheEntity {

  public String name;
  public String season;


  public static List<Fruit> fruitsBySeason(String season) {
    return find("season", season).list();
  }

  public static Fruit findByName(String name) {
    return find("lower(name) like concat('%',lower(?1),'%')", name).firstResult();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Fruit fruit = (Fruit) o;
    return id.equals(fruit.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
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
