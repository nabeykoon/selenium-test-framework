package com.webapp.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

@Listeners({com.webapp.base.TestListener.class})


public class BaseTest {
    private static final ThreadLocal<WebDriver> drivers = new ThreadLocal<WebDriver> ();
    protected Logger log;

    protected String testSuiteName;
    protected String testName;
    protected String testMethodName;

    public WebDriver getDriver() {
        return drivers.get ();
    }

    @Parameters({"browser", "os", "node"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method, @Optional("chrome") String browser, @Optional String os, @Optional String node, ITestContext context) {

        String testName = context.getCurrentXmlTest ().getName ();
        log = LogManager.getLogger (testName);

        if (node != null) {
            try {
                RemoteWebDriver driver = BrowserDriverFactory.createRemoteDriver (browser, log, os, node);
                driver.manage ().window ().maximize ();
                drivers.set (driver);
            } catch (MalformedURLException e) {
                e.printStackTrace ();
            }
        } else {
            WebDriver driver = BrowserDriverFactory.createDriver (browser, log);
            driver.manage ().window ().maximize ();
            drivers.set (driver);
        }
        //context.setAttribute ("WebDriver", driver);
        context.setAttribute ("browser", browser);
        this.testSuiteName = context.getSuite ().getName ();
        this.testName = testName;
        this.testMethodName = method.getName ();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info ("Close driver");
        getDriver ().quit ();
        drivers.remove ();
    }
}
