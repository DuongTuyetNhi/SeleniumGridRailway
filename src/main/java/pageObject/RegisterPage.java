package pageObject;

import base.DriverManagement;
import models.User;
import org.openqa.selenium.By;

import static base.DriverManagement.*;

public class RegisterPage extends BasePage {
    private String lblValidationError = "//*[@id='RegisterForm']//label[@for='%s' and @class='validation-error']";
    private By txtEmail = By.id("email");
    private By txtPassword = By.id("password");
    private By txtConfirmPassword = By.id("confirmPassword");
    private By txtPID = By.id("pid");
    private By btnRegister = By.xpath("//*[@id='RegisterForm']//p/input[@type='submit']");
    private By msgError = By.xpath("//*[@id='content']/p[@class='message error']");
    private By msgSuccess = By.xpath("//*[@id='content']/h1[@align='center']");
    private By msgConfirmSuccess = By.xpath("//*[@id='content']/p");

    public void fillRegisterForm(User user) {
        enter(txtEmail, user.getUsername());
        enter(txtPassword, user.getPassword());
        enter(txtConfirmPassword, user.getPassword());
        enter(txtPID, user.getPid());
    }

    public void clickBtnRegister() {
        DriverManagement.scrollToView(btnRegister);
        click(btnRegister);
    }


    public String getErrorMsg() {
        return getText(msgError);
    }

    public String getValidationPasswordError() {
        By passwordError = By.xpath(String.format(lblValidationError, "password"));
        return getText(passwordError);
    }

    public String getValidationPIDError() {
        By pidError = By.xpath(String.format(lblValidationError, "pid"));
        return getText(pidError);
    }

    public boolean isMessageDisplayed() {
        return getDriver().findElement(msgSuccess).isDisplayed();
    }

    public boolean isConfirmMessageDisplayed() {
        return getDriver().findElement(msgConfirmSuccess).isDisplayed();
    }
}
