package org.jsoftbiz.service;

import org.jsoftbiz.repository.SomeRepository;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheResult;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

/**
 * Example service : Cache aside
 */
public class Ex3Service implements SomeService {

  private SomeRepository repository = new SomeRepository();
  private Cache<String, String> cache;

  public Ex3Service() {
    CachingProvider provider = Caching.getCachingProvider();
    CacheManager cacheManager = provider.getCacheManager();

    MutableConfiguration<String, String> configuration = new MutableConfiguration<>();
    configuration.setTypes(String.class, String.class);
    cache = cacheManager.createCache("someCache", configuration);
  }

  @Override
  @CacheResult(cacheName = "someCache")
  public String someLogic(@CacheKey String id) {
    String val = repository.writeToDb(id);
    return val;
  }
}
