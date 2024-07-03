import base.DriverManagement;
import org.testng.annotations.*;

import static base.DriverManagement.getDriver;

public class BaseTest {
    @BeforeMethod
    @Parameters({"browser", "runMode"})
    public synchronized void beforeMethod(@Optional("chrome") String browser, @Optional("local") String runMode) throws Throwable {
        System.out.println("Pre-condition");
        DriverManagement.setBrowser(browser);
        DriverManagement.setRunMode(runMode);
        DriverManagement.initDriver();
    }

    @AfterMethod
    public synchronized void afterMethod() {
        System.out.println("Post-condition");
        getDriver().quit();
    }
}
