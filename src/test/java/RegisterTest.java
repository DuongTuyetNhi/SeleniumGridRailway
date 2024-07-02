import base.DriverManagement;
import models.User;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObject.HomePage;
import pageObject.MailPage;
import pageObject.RegisterPage;

import static base.DriverManagement.getDriver;

public class RegisterTest extends BaseTest {
    HomePage homePage = new HomePage();
    RegisterPage registerPage = new RegisterPage();
    MailPage mailPage = new MailPage();
    String username = "nhiagest@grr.la";
    String password = "12345678";
    String pid = "12345678";
    User oldAccountUser = new User(username, password, pid);
    String newEmail = "helloselenium@gmail.com";
    String blankPassword = "";

    @Test(description = "User cannot create account with an already in use email")
    public void RegisterWithUsedEmail(){
        DriverManagement.openRailwayPage();
        homePage.openTab("Register");
        registerPage.fillRegisterForm(oldAccountUser);
        registerPage.clickBtnRegister();

        String actualMsg = registerPage.getErrorMsg();
        String expectedMsg = "This email address is already in use.";

        Assert.assertEquals(actualMsg, expectedMsg, "The actual message is not the same as expected.");
    }

    @Test(description = "User cannot create account while password and PID fields are empty")
    public void RegisterWithBlankFields(){
        DriverManagement.openRailwayPage();
        homePage.openTab("Register");
        User newUser = new User(newEmail, blankPassword, "");
        registerPage.fillRegisterForm(newUser);
        registerPage.clickBtnRegister();

        SoftAssert softAssert = new SoftAssert();

        String actualMsg = registerPage.getErrorMsg();
        String expectedMsg = "There're errors in the form. Please correct the errors and try again.";
        softAssert.assertEquals(actualMsg, expectedMsg, "The error message is not the same as expected.");

        String passwordActualMsg = registerPage.getValidationPasswordError();
        String passwordExpectedMsg = "Invalid password length.";
        softAssert.assertEquals(passwordActualMsg, passwordExpectedMsg, "The password error message is not the same as expected.");

        String pidActualMsg = registerPage.getValidationPIDError();
        String pidExpectedMsg = "Invalid ID length.";
        softAssert.assertEquals(pidActualMsg, pidExpectedMsg, "The PID error message is not the same as expected.");

        softAssert.assertAll();
    }

    @Test(description = "User create and active account")
    public void RegisterAccount(){
        DriverManagement.openMailPage();
        String mail = mailPage.getEmail();
        User newAccountUser = new User(mail, password, pid);
        String MailWindow = getDriver().getWindowHandle();
        getDriver().switchTo().newWindow(WindowType.TAB);

        DriverManagement.openRailwayPage();
        homePage.clickCreateAnAccountLink();

        String RailwayWindow = getDriver().getWindowHandle();

        registerPage.fillRegisterForm(newAccountUser);
        registerPage.clickBtnRegister();
        Assert.assertTrue(registerPage.isMessageDisplayed());

        getDriver().switchTo().window(MailWindow);
        getDriver().navigate().refresh();

        mailPage.confirmAccount();

        DriverManagement.switchToTab(MailWindow, RailwayWindow);

        Assert.assertTrue(registerPage.isConfirmMessageDisplayed());
    }
}
