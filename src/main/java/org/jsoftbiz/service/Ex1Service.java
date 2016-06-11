package org.jsoftbiz.service;

import org.jsoftbiz.repository.SomeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Example service
 */
@Service
public class Ex1Service implements SomeService {

  private static final Logger LOGGER = LoggerFactory.getLogger("org.jsoftbiz.Demo");

  private SomeRepository repository = new SomeRepository();

  @Override
  public String someLogic(final String id) {
    LOGGER.debug("---> Call to service 1");
    return repository.readFromDb(id);
  }
}
