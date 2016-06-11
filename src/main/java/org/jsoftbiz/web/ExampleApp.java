package org.jsoftbiz.web;

import org.jsoftbiz.config.WebConfig;
import org.jsoftbiz.service.Ex1Service;
import org.jsoftbiz.service.Ex2Service;
import org.jsoftbiz.service.Ex3Service;
import org.jsoftbiz.service.Ex4Service;
import org.jsoftbiz.service.Ex5Service;
import org.jsoftbiz.service.Ex6Service;
import org.jsoftbiz.service.Ex7Service;
import org.jsoftbiz.service.SomeService;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

import static spark.Spark.get;

/**
 * Example App
 */
@Configuration
@ComponentScan({ "org.jsoftbiz" })
public class ExampleApp {

  private static Class<? extends SomeService> serviceClass = Ex7Service.class;

  public static void main(String[] args) {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ExampleApp.class);
    new WebConfig(ctx.getBean(serviceClass));
    ctx.registerShutdownHook();
  }

}
