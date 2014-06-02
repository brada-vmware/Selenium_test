/*
 * Copyright (C) 2014 VMware, Inc.  All rights reserved. -- VMware Confidential
 */
package com.vmware.test;

import org.junit.Test;
import org.openqa.selenium.By;

/**
 * Test login
 */
public class LoginTest extends AbstractTest {
    private final String username = "200";
    private final String password = "j2ee";

    @Test
    public void testLogin() {
        driver.get(testUrl);
        driver.findElement(By.name("j_username")).clear();
        driver.findElement(By.name("j_username")).sendKeys(username);
        driver.findElement(By.name("j_password")).clear();
        driver.findElement(By.name("j_password")).sendKeys(password);
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    }
}
