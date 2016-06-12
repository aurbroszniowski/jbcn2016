package org.jsoftbiz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * Example service : Cache through
 */

@Service
public class Ex5Service implements SomeService {

  private static final Logger LOGGER = LoggerFactory.getLogger("org.jsoftbiz.Demo");

  private Cache<String, String> cache;
  private ObjectName objectName;

  public Ex5Service() {
    CachingProvider provider = Caching.getCachingProvider("org.ehcache.jsr107.EhcacheCachingProvider");
    CacheManager cacheManager = provider.getCacheManager();

    MutableConfiguration<String, String> configuration = new MutableConfiguration<>();
    configuration.setTypes(String.class, String.class);
    configuration.setCacheLoaderFactory(new FactoryBuilder.ClassFactory<>("org.jsoftbiz.service.SomeCacheLoader"));
    configuration.setReadThrough(true);
    configuration.setStatisticsEnabled(true);
    cache = cacheManager.createCache("someCache5", configuration);
  }

  @Override
  public String someLogic(final String id) {
    try {
      MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
      objectName = new ObjectName("javax.cache:type=CacheStatistics,CacheManager=urn.X-ehcache.jsr107-default-config,Cache=someCache5");
      Object value = mBeanServer.getAttribute(objectName, "CacheHits");
      LOGGER.debug("cache hits = " + value);

      MBeanInfo info;
      info = mBeanServer.getMBeanInfo(objectName);
      MBeanAttributeInfo[] attrInfo = info.getAttributes();

      LOGGER.debug("Attributes for object: " + objectName + ":");
      for (MBeanAttributeInfo attr : attrInfo) {
        System.out.print("  " + attr.getName() + ", ");
      }
      LOGGER.debug("");
    } catch (Exception e) {
      e.printStackTrace();
    }


    LOGGER.debug("---> Call to service 5");

    String val = cache.get(id);
    return val;
  }
}
