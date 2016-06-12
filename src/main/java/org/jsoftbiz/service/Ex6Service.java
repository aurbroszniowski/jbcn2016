package org.jsoftbiz.service;

import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.jsr107.Eh107Configuration;
import org.jsoftbiz.repository.SomeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;

/**
 * Example service : Cache sizing
 * <p>
 * See http://www.ehcache.org/documentation/3.0/107.html
 */

@Service
public class Ex6Service implements SomeService {

  private static final Logger LOGGER = LoggerFactory.getLogger("org.jsoftbiz.Demo");

  private SomeRepository repository = new SomeRepository();
  private Cache<String, String> cache;

  public Ex6Service() {
    CachingProvider provider = Caching.getCachingProvider("org.ehcache.jsr107.EhcacheCachingProvider");

    CacheManager cacheManager = provider.getCacheManager();

    CacheConfiguration<String, String> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class,
        ResourcePoolsBuilder.heap(10000)).build();
    cache = cacheManager.createCache("someCache6", Eh107Configuration.fromEhcacheCacheConfiguration(cacheConfiguration));
  }

  @Override
  public String someLogic(final String id) {
    LOGGER.debug("---> Call to service 6");

    String val = cache.get(id);
    if (val == null) {
      val = repository.readFromDb(id);
      cache.put(id, val);
    }
    return val;
  }
}
