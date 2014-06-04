/*
 * Copyright (C) 2014 VMware, Inc.  All rights reserved. -- VMware Confidential
 */
package com.vmware.test;

import static org.junit.Assert.assertNotNull;

import javax.annotation.PostConstruct;

import org.junit.Rule;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Abstract base class for tests
 */
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
abstract public class AbstractTest extends AbstractJUnit4SpringContextTests {
    @Configuration
    @PropertySource("classpath:test.properties")
    protected static class AbstracTestConfig {

    }

    @Rule
    public SeleniumDriverRule seleniumDriverRule;

    @Autowired
    private Environment environment;

    protected String testUrl;

    @PostConstruct
    public void afterPropertiesSet() {
        seleniumDriverRule = new SeleniumDriverRule(initProperty("selenium.server.url", "http://10.152.32.25:4444/wd/hub"));
        testUrl = initProperty("selenium.test.url.SimpleTest", "http://10.152.32.28:8080/bank/main");
    }

   /**
    *
    * @param propName
    * @param exampleValue
    * @return
    */
   protected String initProperty(String propName, String exampleValue) {
       String prop = environment.getProperty(propName);
       String message = String.format("Required property is null. Please set system property %s"
               + ", e.g.: -D%s=%s", propName, propName, exampleValue);

       assertNotNull(message, prop);

       return prop;
   }

   /**
    *
    * @return
    */
   protected WebDriver getDriver() {
       return seleniumDriverRule.driver;
   }
}
