package org.jsoftbiz.service;

import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.util.Set;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.ReflectionException;

/**
 * Example service : Cache through
 */

@Service
public class Ex5Service implements SomeService {

  private Cache<String, String> cache;
  private ObjectName objectName;

  public Ex5Service() {
    CachingProvider provider = Caching.getCachingProvider();
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
      System.out.println("cache hits = " + value);

      MBeanInfo info;
      info = mBeanServer.getMBeanInfo(objectName);
      MBeanAttributeInfo[] attrInfo = info.getAttributes();

      System.out.println("Attributes for object: " + objectName + ":");
      for (MBeanAttributeInfo attr : attrInfo) {
        System.out.print("  " + attr.getName() + ", ");
      }
      System.out.println("");
    } catch (Exception e) {
      e.printStackTrace();
    }


    System.out.println("---> Call to service 5");

    String val = cache.get(id);
    return val;
  }
}
