package org.jsoftbiz.service;

import org.jsoftbiz.repository.SomeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

/**
 * Example service : Cache aside
 *
 * Please implement TODO lines
 *
 */

@Service
public class Ex2Service implements SomeService {

  private static final Logger LOGGER = LoggerFactory.getLogger("org.jsoftbiz.Demo");

  private SomeRepository repository = new SomeRepository();
  private Cache<String, String> cache;

  public Ex2Service() {
    // TODO : get Ehcache as caching provider ("org.ehcache.jsr107.EhcacheCachingProvider")

    CacheManager cacheManager; // TODO : Get javax.cache.CacheManager from caching provider

    MutableConfiguration<String, String> configuration; // TODO : create Mutableconfiguration and set types : String, String

    // TODO Create Cache
  }

  @Override
  public String someLogic(final String id) {
    LOGGER.debug("---> Call to service 2");

    // TODO implements Cache Aside pattern to cache the call to the repository
    String val = repository.readFromDb(id);
    return val;
  }
}
