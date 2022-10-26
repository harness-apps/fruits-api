package dev.kameshs.fruits.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.panache.common.Sort;

@Path("/api/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitsResource {

  @ConfigProperty(name = "my.fruit", defaultValue = "apple")
  String defaultFruit;

  @GET
  @Path("/default")
  public Fruit defaultFruit() {
    return Fruit.fruitsByName(defaultFruit);
  }

  @GET
  @Path("/{id}")
  public Fruit fruitByID(@PathParam("id") String id) {
    Fruit fruit = Fruit.findById(new ObjectId(id));
    if (fruit == null) {
      throw new NotFoundException();
    }
    ;
    return fruit;
  }

  @GET
  @Path("/")
  public List<Fruit> fruits() {
    return Fruit.listAll(Sort.ascending("name,season"));
  }

  @GET
  @Path("/search/{name}")
  public Fruit searchByName(@PathParam("name") String name) {
    return Fruit.fruitsByName(name);
  }

  @GET
  @Path("/search/season/{season}")
  public List<Fruit> fruitsBySeason(@PathParam("season") String season) {
    return Fruit.fruitsBySeason(season);
  }

  @POST
  @Path("/add")
  public Response addFruit(Fruit fruit) {
    fruit.persist();
    return Response
        .status(201)
        .build();
  }

  @DELETE
  @Path("/{id}")
  public void deleteFruit(@PathParam("id") String id) {
    Fruit fruit = Fruit.findById(new ObjectId(id));
    if (fruit == null) {
      throw new NotFoundException();
    }
    fruit.delete();
  }

  @DELETE
  public void deleteAll() {
    Fruit.deleteAll();
  }
}
