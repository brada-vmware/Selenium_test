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
 * Test login
 */
public class LoginTest extends AbstractTest {
    private final String username = "200";
    private final String password = "j2ee";
    private final String welcomeMessageToVerify = "Welcome to Duke's Bank.";

    @Test
    public void testLogin() {
        WebDriver driver = getDriver();
        driver.get(testUrl);

        // fill and submit the login form
        driver.findElement(By.name("j_username")).clear();
        driver.findElement(By.name("j_username")).sendKeys(username);
        driver.findElement(By.name("j_password")).clear();
        driver.findElement(By.name("j_password")).sendKeys(password);
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

        // verify login succeeded
        WebElement welcomeMessageElement = driver.findElement(By.tagName("h3"));
        assertNotNull("Welcome message not found", welcomeMessageElement);
        assertEquals(welcomeMessageToVerify, welcomeMessageElement.getText());
    }
}
