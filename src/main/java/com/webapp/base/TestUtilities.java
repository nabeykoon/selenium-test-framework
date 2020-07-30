package com.webapp.base;

public class TestUtilities extends BaseTest {

    //STATIC SLEEP IF REQUIRED FOR DEBUGGING. NOT RECOMMENDED TO USE IN TESTS
    protected void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
