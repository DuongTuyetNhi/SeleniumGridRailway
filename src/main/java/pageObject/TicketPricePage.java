package pageObject;

import org.openqa.selenium.By;

import static base.DriverManagement.*;

public class TicketPricePage extends BasePage{

    private String rowTypeSeat = "//table[@class='NoBorder']//tr[td[text()='%s']]//a";
    private String rowPrice = "//table[@class='MyTable MedTable']//th[normalize-space()='Price (VND)']//following-sibling::td[count(//td[text()='%s']/preceding-sibling::td)+1]";
    private By headerPage = By.xpath("//*[@id='content']/h1[text()='Ticket Price']");
    private By headerTable = By.xpath("//table[@class='MyTable MedTable']//tr[@class='TableSmallHeader']/th");
    public void selectSeatType(String seatType){
        By txtSeatType = By.xpath(String.format(rowTypeSeat, seatType));
        click(txtSeatType);
    }
    public boolean doesTitleExist(){
        return getDriver().findElement(headerPage).isDisplayed();
    }

    public String getTicketInfor(){
        return getText(headerTable);
    }
    public String getPriceBySeatType(String seatType){
        By ticketPrice = By.xpath(String.format(rowPrice, seatType));
        return getText(ticketPrice);
    }
}
