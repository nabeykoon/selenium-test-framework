package com.webapp.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class TestListener extends TestUtilities implements ITestListener {

    Logger log;
    String testSuiteName;
    String testName;
    String testMethodName;

    ExtentReports extent = ExtentReportManager.getReporter ();
    ExtentTest test;
    ThreadLocal<ExtentTest> ExtentTest = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {
        this.testMethodName = result.getMethod().getMethodName();
        log.info("[Starting " + testMethodName + "]");
        test = extent.createTest (testMethodName);
        ExtentTest.set (test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        this.testMethodName = result.getMethod().getMethodName();
        log.info("[Test " + testMethodName + " passed]");
        ExtentTest.get ().log (Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("[Test " + testMethodName + " failed]");
        WebDriver driver = (WebDriver) result.getTestContext ().getAttribute ("WebDriver");
        log.info ("Driver hash code for screenshot " + driver.hashCode () );
        String basePath = getBasePath (testSuiteName, testName, testMethodName);
        String path = getScreenshotPathInFailure (basePath, testMethodName, driver);
        log.info (path);
        ExtentTest.get ().log (Status.FAIL, "Test Failed");
        ExtentTest.get ().fail ("details", MediaEntityBuilder.createScreenCaptureFromPath (path, testMethodName).build ());
        ExtentTest.get ().fail (result.getThrowable ());
    }


    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        this.testSuiteName = context.getSuite().getName();
        this.testName = context.getCurrentXmlTest().getName();
        this.log = LogManager.getLogger(testName);
        log.info("[TEST " + testName + " STARTED]");
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("[ALL " + testName + " FINISHED]");
        extent.flush ();
    }
}
