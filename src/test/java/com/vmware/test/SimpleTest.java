/*
 * Copyright (C) 2014 VMware, Inc.  All rights reserved. -- VMware Confidential
 */
package com.vmware.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Simple test
 */
public class SimpleTest extends AbstractTest {
    private final String messageToVerify = "Please enter your customer ID and password (e.g. \"200/j2ee\") and click Submit.";


    @Test
    public void testLoginPageMessage() {
        WebDriver driver = getDriver();
        driver.get(testUrl);
        WebElement paragraphElement = driver.findElement(By.tagName("p"));
        assertNotNull("paragraphElement is null", paragraphElement);
        assertEquals(messageToVerify, paragraphElement.getText());
    }

}
