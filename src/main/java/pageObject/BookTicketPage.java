package pageObject;

import models.Ticket;
import org.openqa.selenium.By;

import static base.DriverManagement.*;

public class BookTicketPage extends BasePage{
    private String sltValue = "//*[@id='content']//select[@name='%s']";
    private By btnBookTicket = By.xpath("//form//input[@type = 'submit']");

    public void selectInfor(String item, String information){
        By selectItem = By.xpath(String.format(sltValue, item));
        enter(selectItem, information);
    }

    public void bookTicket(Ticket ticket){
        selectInfor("DepartStation", ticket.getDepartFrom().getValueLocation());
        selectInfor("Date", ticket.getDepartDate());
        selectInfor("SeatType", ticket.getSeatType().getValueSeatType());
        selectInfor("TicketAmount", ticket.getAmount());
        selectInfor("ArriveStation", ticket.getArriveAt().getValueLocation());

    }

    public void clickBookTicketButton(){
        scrollToView(btnBookTicket);
        click(btnBookTicket);
    }

}
