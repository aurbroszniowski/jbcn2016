package org.jsoftbiz;

import java.util.Random;

import static spark.Spark.*;

public class ExampleService {

  public static void main(String[] args) {
    Random rnd = new Random();

    get("/hello/:name", (request, response) -> {
      long start = System.currentTimeMillis();
      Thread.sleep(100 + rnd.nextInt(1000));
      String ret = "Hello " + request.params(":name");
      long end = System.currentTimeMillis();

      return ret + ", took " + (end - start) + " ms.";
    });
  }


}
