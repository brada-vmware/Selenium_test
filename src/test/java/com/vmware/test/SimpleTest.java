/*
 * Copyright (C) 2014 VMware, Inc.  All rights reserved. -- VMware Confidential
 */
package com.vmware.test;

import static org.junit.Assert.assertNotNull;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Simple test
 */
public class SimpleTest {
    private WebDriver driver;

    private String seleniumServerUrl;
    private String testUrl;
    private final String messageToVerify = "Please enter your customer ID and password (e.g. \"200/j2ee\") and click Submit.";

    @Before
    public void setUp() throws Exception {
        this.seleniumServerUrl = initProperty("selenium.server.url", "http://10.152.32.25:4444/wd/hub");
        this.testUrl = initProperty("selenium.test.url.SimpleTest", "http://10.152.32.35:8081/bank/main");

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
    public void testLoginPageMessage() {
        this.driver.get(testUrl);
        WebElement paragraphElement = driver.findElement(By.tagName("p"));
        assertNotNull("paragraphElement is null", paragraphElement);
        assertEquals(messageToVerify, paragraphElement.getText());
    }

    /**
     *
     * @param propName
     * @param exampleValue
     * @return
     */
    private String initProperty(String propName, String exampleValue) {
        String prop = System.getProperty(propName);
        String message = String.format("Required property is null. Please set system property %s"
                + ", e.g.: -D%s=%s", propName, propName, exampleValue);

        assertNotNull(message, prop);

        return prop;
    }
}
