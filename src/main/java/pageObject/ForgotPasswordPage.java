package pageObject;

import org.openqa.selenium.By;

import static base.DriverManagement.click;
import static base.DriverManagement.enter;

public class ForgotPasswordPage extends BasePage{
    private By txtEmail = By.id("email");
    private By btnSend = By.xpath("//input[@value='Send Instructions']");

    public void enterEmailAddress(String email){
        enter(txtEmail, email);
    }
    public void clickSendInstructionsBtn(){
        click(btnSend);
    }

}
