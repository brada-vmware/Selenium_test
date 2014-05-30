/*
 * Copyright (C) 2014 VMware, Inc.  All rights reserved. -- VMware Confidential
 */
package com.vmware.test;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Simple test
 */
public class SimpleTest {
    private WebDriver driver;

    private final String seleniumServerUrl = System.getProperty("selenium.server.url"); //"http://10.152.32.25:4444/wd/hub";
    private final String testUrl = System.getProperty("selenium.test.url.SimpletTest"); //"http://www.google.com";

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();

        this.driver = new RemoteWebDriver(
                new URL(seleniumServerUrl), capabilities);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws Exception {
        this.driver.quit();
    }

    @Test
    public void test() {
        this.driver.get(testUrl);
        assertEquals("Google", this.driver.getTitle());
    }
}
