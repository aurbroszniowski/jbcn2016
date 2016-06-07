package org.jsoftbiz.repository;

import java.util.Random;

/**
 * Some Repository
 */
public class SomeRepository {

  Random rnd = new Random();

  /**
   * Simulates a DB write call - takes between 100ms and 1100ms
   * @param value to be written
   * @return String written
   */
  public String writeToDb(String value) {
    try {
      Thread.sleep(100 + rnd.nextInt(1000));
    } catch (InterruptedException e) {
      // NOOP
    }

    return "Hello " + value;
  }

  /**
   * Simulates a DB read call - takes between 100ms and 1100ms
   * @param id to be read
   * @return String
   */
  public String readFromDb(String id) {
    try {
      Thread.sleep(100 + rnd.nextInt(1000));
    } catch (InterruptedException e) {
      // NOOP
    }

    return "Hello " + id;
  }

}
