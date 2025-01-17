package com.webapp.alertstests;

import com.webapp.base.TestUtilities;
import com.webapp.pages.JavaScriptAlertsPage;
import com.webapp.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AlertsTests extends TestUtilities {

    @Test
    public void jsAlertTest() {
        //Following log removed since it is handled from TestLister
        //log.info("Starting jsAlertTest");
        log.info ("Thread id of jsAlertTest " + Thread.currentThread().getId());
        log.info ("Driver Hash code of jsAlertTest " + getDriver ().hashCode ());
        // open main page
        WelcomePage welcomePage = new WelcomePage (getDriver (), log);
        welcomePage.openPage ();

        // Click on JavaScript Alerts link
        JavaScriptAlertsPage alertsPage = welcomePage.clickJavaScriptAlertsLink ();

        // Click JS Alert button
        alertsPage.openJSAlert ();
        sleep (1000);

        // Get alert text
        String alertMessage = alertsPage.getAlertText ();

        // Click OK button
        alertsPage.acceptAlert ();

        // Get Results text
        String result = alertsPage.getResultText ();
        sleep (1000);
        // Verifications
        // 1 - Alert text is expected
        Assert.assertTrue (alertMessage.equals ("I am a JS Alert"),
                "Alert message is not expected. \nShould be 'I am a JS Alert', but it is '" + alertMessage + "'");

        // 2 - Result text is expected
        Assert.assertTrue (result.equals ("You successfuly clicked an alert"),
                "result is not expected. \nShould be 'You successfuly clicked an alert', but it is '" + result + "'");
    }

    @Test
    public void jsDismissTest() {

        //Following log removed since it is handled from TestLister
        //log.info("Starting jsDismissTest");
        log.info ("Thread id of jsDismissTest " + Thread.currentThread().getId());
        log.info ("Driver Hash code of jsDismissTest " + getDriver ().hashCode ());
        // open main page
        WelcomePage welcomePage = new WelcomePage (getDriver (), log);
        welcomePage.openPage ();

        // Click on JavaScript Alerts link
        JavaScriptAlertsPage alertsPage = welcomePage.clickJavaScriptAlertsLink ();

        // Click JS Confirm button
        alertsPage.openJSConfirm ();
        sleep (1000);
        // Get alert text
        String alertMessage = alertsPage.getAlertText ();

        // Click Cancel button
        alertsPage.dismissAlert ();

        // Get Results text
        String result = alertsPage.getResultText ();
        sleep (1000);
        // Verifications
        // 1 - Alert text is expected
        Assert.assertTrue (alertMessage.equals ("I am a JS Confirm"),
                "Alert message is not expected. \nShould be 'I am a JS Confirm', but it is '" + alertMessage + "'");

        // 2 - Result text is expected
        Assert.assertTrue (result.equals ("You clicked: Cancel"),
                "result is not expected. \nShould be 'You clicked: Cancel', but it is '" + result + "'");
    }

    @Test
    public void jsPromptTest() {
        //Following log removed since it is handled from TestLister
        //log.info("Starting jsPromptTest");
        log.info ("Thread id of jsPromptTest " + Thread.currentThread().getId());
        log.info ("Driver Hash code of jsPromptTest " + getDriver ().hashCode ());
        SoftAssert softAssert = new SoftAssert ();
        // open main page
        WelcomePage welcomePage = new WelcomePage (getDriver (), log);
        welcomePage.openPage ();

        // Click on JavaScript Alerts link
        JavaScriptAlertsPage alertsPage = welcomePage.clickJavaScriptAlertsLink ();

        // Click JS Prompt button
        alertsPage.openJSPrompt ();
        sleep (1000);
        // Get alert text
        String alertMessage = alertsPage.getAlertText ();

        // Type text into alert
        alertsPage.typeTextIntoAlert ("Hello Alert, it's Nadeera here");
        sleep (1000);
        // Get Results text
        String result = alertsPage.getResultText ();
        sleep (1000);
        // Verifications
        // 1 - Alert text is expected
        softAssert.assertTrue (alertMessage.equals ("I am a JS prompt"),
                "Alert message is not expected. \nShould be 'I am a JS prompt', but it is '" + alertMessage + "'");

        // 2 - Result text is expected
        softAssert.assertTrue (result.equals ("You entered: Hello Alert, it's Nadeera here"),
                "result is not expected. \nShould be 'You entered: Hello Alert, it's Nadeera here', but it is '" + result
                        + "'");
        softAssert.assertAll ();
    }
}
