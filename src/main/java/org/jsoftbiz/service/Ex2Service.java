package org.jsoftbiz.service;

import org.jsoftbiz.repository.SomeRepository;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

/**
 * Example service : Cache aside
 */
public class Ex2Service implements SomeService {

  private SomeRepository repository = new SomeRepository();
  private Cache<String, String> cache;

  public Ex2Service() {
    CachingProvider provider = Caching.getCachingProvider();
    CacheManager cacheManager = provider.getCacheManager();

    MutableConfiguration<String, String> configuration = new MutableConfiguration<>();
    configuration.setTypes(String.class, String.class);
    cache = cacheManager.createCache("someCache", configuration);
  }

  @Override
  public String someLogic(final String id) {
    String val = cache.get(id);
    if (val == null) {
      val = repository.writeToDb(id);
      cache.put(id, val);
    }
    return val;
  }
}
