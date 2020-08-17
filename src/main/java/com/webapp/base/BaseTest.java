package com.webapp.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;

@Listeners({ com.webapp.base.TestListener.class })

public class BaseTest {
    private static final ThreadLocal<WebDriver> drivers = new ThreadLocal<WebDriver> ();
    protected Logger log;

    protected String testSuiteName;
    protected String testName;
    protected String testMethodName;
    public WebDriver getDriver() {
        return drivers.get ();
    }

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method, @Optional("chrome") String browser, ITestContext context) {

        String testName = context.getCurrentXmlTest ().getName ();
        log = LogManager.getLogger (testName);

        WebDriver driver = BrowserDriverFactory.createDriver (browser, log);
        drivers.set (driver);
        driver.manage ().window ().maximize ();
        //context.setAttribute ("WebDriver", driver);

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
