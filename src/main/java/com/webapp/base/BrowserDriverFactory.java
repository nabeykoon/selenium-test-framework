package com.webapp.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserDriverFactory {
    private String browser;
    private Logger log;

    public BrowserDriverFactory(String browser, Logger log) {
        this.browser = browser.toLowerCase ();
        this.log = log;
    }


    public static WebDriver createDriver(String browser, Logger log) {
        // Create driver
        log.info ("Create driver: " + browser);
        WebDriver driver;
        switch (browser) {

            case "chrome":
                //System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                WebDriverManager.chromedriver ().setup ();
                driver = new ChromeDriver ();
                break;

            case "firefox":
                //System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                WebDriverManager.firefoxdriver ().setup ();
                driver = new FirefoxDriver ();
                break;

            case "chromeHeadless":
                WebDriverManager.chromedriver ().setup ();
                ChromeOptions chromeOptions = new ChromeOptions ();
                chromeOptions.addArguments ("--headless");
                driver = new ChromeDriver (chromeOptions);
                break;

            case "firefoxHeadless":
                WebDriverManager.firefoxdriver ().setup ();
                FirefoxBinary firefoxBinary = new FirefoxBinary ();
                firefoxBinary.addCommandLineOptions ("--headless");
                FirefoxOptions firefoxOptions = new FirefoxOptions ();
                firefoxOptions.setBinary (firefoxBinary);
                driver = new FirefoxDriver (firefoxOptions);
                break;

            default:
                System.out.println ("Do not know how to start: " + browser + ", starting chrome.");
                WebDriverManager.chromedriver ().setup ();
                driver = new ChromeDriver ();
                break;
        }
        return driver;
    }
}

