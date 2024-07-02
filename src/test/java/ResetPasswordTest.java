import base.DriverManagement;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.ForgotPasswordPage;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MailPage;

import static base.DriverManagement.getDriver;
import static base.DriverManagement.switchToTab;

public class ResetPasswordTest extends BaseTest {
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    MailPage mailPage = new MailPage();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    String username = "nhiagest@grr.la";
    String password = "12345678";
    String[] str = username.split("@");
    String mailName = str[0];
    String domainName = str[1];


    @Test(description = "Reset password shows error if the new password is same as current")
    public void ResetPasswordSameOldPassword(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.clickForgotPassword();
        String RailwayWindow = getDriver().getWindowHandle();

        forgotPasswordPage.enterEmailAddress(username);
        forgotPasswordPage.clickSendInstructionsBtn();

        getDriver().switchTo().newWindow(WindowType.TAB);
        DriverManagement.openMailPage();
        String MailWindow = getDriver().getWindowHandle();
        mailPage.loginToEmail(mailName, domainName);
        mailPage.resetPassword();

        DriverManagement.switchToTab(MailWindow, RailwayWindow);

        Assert.assertTrue(loginPage.isTokenDisplayed());
        loginPage.fillResetPasswordForm(password, password);
        loginPage.clickResetPasswordBtn();

        String actualMsg = loginPage.getResetMsg();
        String expectedMsg = "The new password cannot be the same with the current password";
        Assert.assertEquals(actualMsg, expectedMsg, "The actual message is not the same as expected.");
    }

    @Test(description = "Reset password shows error if the new password and confirm password doesn't match")
    public void ResetPasswordDoesNotMatch(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.clickForgotPassword();
        String RailwayWindow = getDriver().getWindowHandle();

        forgotPasswordPage.enterEmailAddress(username);
        forgotPasswordPage.clickSendInstructionsBtn();

        getDriver().switchTo().newWindow(WindowType.TAB);
        DriverManagement.openMailPage();
        String MailWindow = getDriver().getWindowHandle();
        mailPage.loginToEmail(mailName, domainName);
        mailPage.resetPassword();

        switchToTab(MailWindow, RailwayWindow);

        Assert.assertTrue(loginPage.isTokenDisplayed());
        loginPage.fillResetPasswordForm(password, "11111111");
        loginPage.clickResetPasswordBtn();

        String actualMsg = loginPage.getResetMsg();
        String expectedMsg = "Could not reset password. Please correct the errors and try again.";
        Assert.assertEquals(actualMsg, expectedMsg, "The reset error message is not the same as expected.");

        String actualConfirmMsg = loginPage.getConfirmPasswordMsg();
        String expectedConfirmMsg = "The password confirmation did not match the new password.";
        Assert.assertEquals(actualConfirmMsg, expectedConfirmMsg,"The confirm password error message is not the same as expected.");
    }
}
