package org.jsoftbiz.service;

import org.jsoftbiz.repository.SomeRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

import javax.cache.integration.CacheLoader;
import javax.cache.integration.CacheLoaderException;

/**
 * @author Aurelien Broszniowski
 */

@Service
public class SomeCacheLoader implements CacheLoader<String, String> {

  SomeRepository repository = new SomeRepository();

  @Override
  public String load(final String id) throws CacheLoaderException {
    return repository.readFromDb(id);

  }

  @Override
  public Map<String, String> loadAll(final Iterable<? extends String> iterable) throws CacheLoaderException {
    for (String id : iterable) {
      load(id);
    }
    return null;
  }
}
