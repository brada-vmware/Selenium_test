/*
 * Copyright (C) 2014 VMware, Inc.  All rights reserved. -- VMware Confidential
 */
package com.vmware.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.openqa.selenium.WebDriver;

/**
 * Abstract base class for tests
 */
abstract public class AbstractTest {
    @Rule
    public SeleniumDriverRule seleniumDriverRule = new SeleniumDriverRule(initProperty("selenium.server.url", "http://10.152.32.25:4444/wd/hub"));

    protected String testUrl = initProperty("selenium.test.url.SimpleTest", "http://10.152.32.35:8081/bank/main");

   /**
    *
    * @param propName
    * @param exampleValue
    * @return
    */
   protected String initProperty(String propName, String exampleValue) {
       String prop = System.getProperty(propName);
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
