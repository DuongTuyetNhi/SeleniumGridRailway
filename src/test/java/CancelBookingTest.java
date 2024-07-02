import base.DriverManagement;
import enums.Locations;
import enums.SeatType;
import models.Ticket;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.*;

import static base.DateUtils.getDateAdd;

public class CancelBookingTest extends BaseTest {
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    BookTicketPage bookTicketPage = new BookTicketPage();
    SuccessPage successPage = new SuccessPage();
    MyTicketPage myTicketPage = new MyTicketPage();

    String username = "nhiagest@grr.la";
    String password = "12345678";
    String departDate = getDateAdd(25);

    User validUser = new User(username, password);
    Ticket ticket = new Ticket(departDate, Locations.SAI_GON, Locations.PHAN_THIET, SeatType.HB, "2");
    @Test(description = "User can cancel a ticket")
    public void CancelTicket(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(validUser);

        homePage.openTab("Book ticket");
        bookTicketPage.bookTicket(ticket);
        bookTicketPage.clickBookTicketButton();

        successPage.openTab("My ticket");
        myTicketPage.cancelTicket(ticket);
        myTicketPage.confirmCancel();
        Assert.assertTrue(myTicketPage.isTicketDisappeared(ticket),"The ticket does not disappear.");

    }
}
