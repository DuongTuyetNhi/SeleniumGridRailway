import base.DriverManagement;
import base.StaticProvider;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;

public class LogoutTest extends BaseTest {
    String username = "nhiagest@grr.la";
    String password = "12345678";
    User validUser = new User(username, password);

    @Test(description = "User is redirected to Home page after logging out")
    public void LogOut() {

        HomePage homePage = new HomePage();
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        LoginPage loginPage = new LoginPage();
        loginPage.submitLoginForm(validUser);
        homePage.openTab("Log out");

        Assert.assertTrue(homePage.isHomePageDisplayed());
        Assert.assertFalse(homePage.isLogoutTabPresent(), "Logout tab is exist");

    }

//    @Test(description = "User is redirected to Home page after logging out", dataProvider = "userDataProvider", dataProviderClass = StaticProvider.class)
//    public void LogOut(User validUser) {
//
//        HomePage homePage = new HomePage();
//        DriverManagement.openRailwayPage();
//        homePage.openLoginTab();
//        LoginPage loginPage = new LoginPage();
//        loginPage.submitLoginForm(validUser);
//        homePage.openTab("Log out");
//
//        Assert.assertTrue(homePage.isHomePageDisplayed());
//        Assert.assertFalse(homePage.isLogoutTabPresent(), "Logout tab is exist");
//    }
}
