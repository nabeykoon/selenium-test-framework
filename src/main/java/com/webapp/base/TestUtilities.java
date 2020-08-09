package com.webapp.base;

import org.testng.annotations.DataProvider;

public class TestUtilities extends BaseTest {

    //STATIC SLEEP IF REQUIRED FOR DEBUGGING. NOT RECOMMENDED TO USE IN TESTS
    protected void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "files")
    protected static Object[][] files() {
        return new Object[][]{
                {1, "Intel-logo.jpg"},
                {2, "download.png"},
                {3, "text.txt"}
        };
    }
}
