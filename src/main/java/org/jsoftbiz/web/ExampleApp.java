package org.jsoftbiz.web;

import org.jsoftbiz.service.Ex1Service;
import org.jsoftbiz.service.Ex2Service;
import org.jsoftbiz.service.Ex3Service;
import org.jsoftbiz.service.SomeService;

import static spark.Spark.get;

/**
 * Example App
 */
public class ExampleApp {

  public static void main(String[] args) {
    SomeService service = new Ex3Service();

    get("/read/:id", (request, response) -> {
      long start = System.currentTimeMillis();
      String val = service.someLogic(request.params(":id"));
      long end = System.currentTimeMillis();
      return val + " (this execution took " + (end - start) + "ms).";
    });
  }


}
