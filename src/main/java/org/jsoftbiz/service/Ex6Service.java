package org.jsoftbiz.service;

import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.jsr107.Eh107Configuration;
import org.jsoftbiz.repository.SomeRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

import static org.ehcache.config.builders.CacheConfigurationBuilder.newCacheConfigurationBuilder;
import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManagerBuilder;
import static org.ehcache.config.builders.ResourcePoolsBuilder.heap;

/**
 * Example service : Cache sizing
 *
 * See http://www.ehcache.org/documentation/3.0/107.html
 */

@Service
public class Ex6Service implements SomeService {

  private SomeRepository repository = new SomeRepository();
  private Cache<String, String> cache;

  public Ex6Service() {
    CachingProvider provider = Caching.getCachingProvider();

    CacheManager cacheManager = provider.getCacheManager();

    CacheConfiguration<String , String> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(String .class, String.class,
        ResourcePoolsBuilder.heap(100)).build();
    cache = cacheManager.createCache("someCache6", Eh107Configuration.fromEhcacheCacheConfiguration(cacheConfiguration));
  }

  @Override
  public String someLogic(final String id) {
    System.out.println("---> Call to service 6");

    String val = cache.get(id);
    if (val == null) {
      val = repository.readFromDb(id);
      cache.put(id, val);
    }
    return val;
  }
}
