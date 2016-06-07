package org.jsoftbiz.service;

import org.jsoftbiz.repository.SomeRepository;

import java.util.Random;

/**
 * Example service
 */
public class Ex1Service implements SomeService {

  private SomeRepository repository = new SomeRepository();

  @Override
  public String someLogic(final String id) {
    return repository.writeToDb(id);
  }
}
