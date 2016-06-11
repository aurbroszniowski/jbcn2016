package org.jsoftbiz.service;

import org.jsoftbiz.repository.SomeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

/**
 * Example service : Cache annotation
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Ex3Service implements SomeService {

  private static final Logger LOGGER = LoggerFactory.getLogger("org.jsoftbiz.Demo");

  private SomeRepository repository = new SomeRepository();
  private Cache<String, String> cache;

  public Ex3Service() {
    CachingProvider provider = Caching.getCachingProvider();
    CacheManager cacheManager = provider.getCacheManager();

    MutableConfiguration<String, String> configuration = new MutableConfiguration<>();
    configuration.setTypes(String.class, String.class);
    configuration.setStoreByValue(true);
    cache = cacheManager.createCache("someCache3", configuration);
  }

  @Override
  @Cacheable(value = "someCache3")
  public String someLogic(String id) {
    LOGGER.debug("---> Call to service 3");

    String val = repository.readFromDb(id);
    return val;
  }
}
