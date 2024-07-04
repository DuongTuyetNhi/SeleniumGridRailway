import base.DriverManagement;
import models.User;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObject.HomePage;
import pageObject.LoginPage;

import static base.DriverManagement.getDriver;

public class ParallelTest {
    User validUser = new User("nhi@grr.la", "12345678");

    @DataProvider(name = "browserProvider", parallel = true)
    public Object[][] browserProvider() {
        return new Object[][]{
                {"chrome"},
                {"firefox"},
                {"edge"}
        };
    }

    @BeforeMethod
    public void beforeMethod(Object[] params) throws Exception {
        System.out.println("Pre-condition");
        String browser = (String) params[0];
        DriverManagement.setBrowser(browser);
        DriverManagement.setRunMode("local");
        DriverManagement.initDriver();
    }

    @Test(description = "User is redirected to Home page after logging out", dataProvider = "browserProvider")
    public void testInDifferentBrowsers(String browser) {
        DriverManagement.openRailwayPage();

        HomePage homePage = new HomePage();
        homePage.openLoginTab();
        LoginPage loginPage = new LoginPage();
        loginPage.submitLoginForm(validUser);
        homePage.openTab("Log out");

        Assert.assertTrue(homePage.isHomePageDisplayed());
        Assert.assertFalse(homePage.isLogoutTabPresent(), "Logout tab exists");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        getDriver().quit();
    }
}
