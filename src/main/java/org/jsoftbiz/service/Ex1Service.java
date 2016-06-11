package org.jsoftbiz.service;

import org.jsoftbiz.repository.SomeRepository;
import org.springframework.stereotype.Service;

/**
 * Example service
 */
@Service
public class Ex1Service implements SomeService {

  private SomeRepository repository = new SomeRepository();

  @Override
  public String someLogic(final String id) {
    System.out.println("---> Call to service 1");
    return repository.readFromDb(id);
  }
}
