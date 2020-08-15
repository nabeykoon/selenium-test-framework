package com.webapp.loginpagetests;

import com.webapp.base.TestUtilities;
import com.webapp.pages.LoginPage;
import com.webapp.pages.SecureAreaPage;
import com.webapp.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveLogInTests extends TestUtilities {
    @Test
    public void logInTest() {

        // open main page
        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();
        takeScreenshotInTest("Welcome page opened", driver);
        log.info("Welcome page is opened.");

        // Click on Form Authentication link
        LoginPage loginPage = welcomePage.clickFormAuthenticationLink();
        takeScreenshotInTest("Login page opened", driver);

        // Log in
        SecureAreaPage secureAreaPage = loginPage.logIn("tomsmith", "SuperSecretPassword!");
        takeScreenshotInTest("Secure page opened", driver);

        // verifications
        // Correct url is appeared
        Assert.assertEquals(secureAreaPage.getCurrentUrl(), secureAreaPage.getPageUrl());

        // log out button is visible
        Assert.assertTrue(secureAreaPage.isLogOutButtonVisible(),
                "logOutButton is not visible.");

        // Successful log in message
        String expectedSuccessMessage = "You logged into a secure area!";
        String actualSuccessMessage = secureAreaPage.getSuccessMessageText();
        Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage),
                "actualSuccessMessage does not contain expectedSuccessMessage\nexpectedSuccessMessage: "
                        + expectedSuccessMessage + "\nactualSuccessMessage: " + actualSuccessMessage);
    }
}
