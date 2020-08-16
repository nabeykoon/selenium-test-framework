package com.webapp.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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

        if (browser.equals ("chrome")) {
            //System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            WebDriverManager.chromedriver ().setup ();
            return new ChromeDriver ();
        } else if (browser.equals ("firefox")) {

            //System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
            WebDriverManager.firefoxdriver ().setup ();
            return new FirefoxDriver ();
        } else {
            System.out.println ("Do not know how to start: " + browser + ", starting chrome.");
            WebDriverManager.chromedriver ().setup ();
            return new ChromeDriver ();
        }
    }
}

