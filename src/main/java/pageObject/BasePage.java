package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static base.DriverManagement.getDriver;

public class BasePage {
    private String menuTab = "//div[@id='menu']//li/a[span[text()='%s']]";

    protected WebElement getTabElement(String tab){
        By byTab = By.xpath(String.format(menuTab, tab));
        return getDriver().findElement(byTab);
    }

    public void openTab(String tab){
        this.getTabElement(tab).click();
    }

    public void openLoginTab(){
        this.getTabElement("Login").click();
    }

    public boolean isLogoutTabPresent(){
        By tabLogout = By.xpath(String.format(menuTab, "Log out"));
        List<WebElement> elements = getDriver().findElements(tabLogout);
        return !elements.isEmpty() &&  elements.get(0).isDisplayed();
    }
}