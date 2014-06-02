/*
 * Copyright (C) 2014 VMware, Inc.  All rights reserved. -- VMware Confidential
 */
package com.vmware.test;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Set up and clean up the selenium driver, and get a screenshot on test failure
 */
public class SeleniumDriverRule extends TestWatcher {
    protected WebDriver driver;
    private final String seleniumServerUrl;

    public SeleniumDriverRule(String seleniumServerUrl) {
        this.seleniumServerUrl = seleniumServerUrl;
    }

    @Override
    protected void starting(Description description) {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();

        try {
            this.driver = new Augmenter().augment(new RemoteWebDriver(
                    new URL(seleniumServerUrl), capabilities));

        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to initialize driver", e);
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @Override
    protected void finished(Description description) {
        this.driver.quit();
    }

    @Override
    protected void failed(Throwable e, Description description) {
        captureScreenshot(description.getMethodName());
    }

    public void captureScreenshot(String fileName) {
        try {
            new File("target/surefire-reports/").mkdirs();
            FileOutputStream out = new FileOutputStream("target/surefire-reports/screenshot-" + fileName + ".png");
            out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            out.close();

        } catch (Exception e) {
            // No need to crash the tests if the screenshot fails
            e.printStackTrace();
        }
    }

}
