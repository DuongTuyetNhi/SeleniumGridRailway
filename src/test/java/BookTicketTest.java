import base.DriverManagement;
import enums.Locations;
import enums.SeatType;
import models.Ticket;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObject.*;

import static base.DateUtils.getDateAdd;

public class BookTicketTest extends BaseTest {
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    BookTicketPage bookTicketPage = new BookTicketPage();
    TrainTimetablePage trainTimetablePage = new TrainTimetablePage();
    TicketPricePage ticketPricePage = new TicketPricePage();
    SuccessPage successPage = new SuccessPage();

    String username = "nhiagest@grr.la";
    String password = "12345678";
    User validUser = new User(username, password);

    @Test(description = "User can book 1 ticket at a time")
    public void BookTicket(){
        Ticket ticket1 = new Ticket(getDateAdd(12), Locations.NHA_TRANG, Locations.HUE, SeatType.SBC, "2");

        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(validUser);

        homePage.openTab("Book ticket");
        bookTicketPage.bookTicket(ticket1);
        bookTicketPage.clickBookTicketButton();

        String actualMsg = successPage.getSuccessfulMsg();
        String expectedMsg = "Ticket booked successfully!";
        Assert.assertEquals(actualMsg, expectedMsg, "The actual message is not the same as expected.");
        Assert.assertTrue(successPage.isCorrectInforTicket(ticket1));
    }

    @Test(description = "User can book many tickets at a time")
    public void BookManyTicket(){
        Ticket ticket2 = new Ticket(getDateAdd(25), Locations.NHA_TRANG, Locations.SAI_GON, SeatType.SSC, "5");
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(validUser);

        homePage.openTab("Book ticket");
        bookTicketPage.bookTicket(ticket2);
        bookTicketPage.clickBookTicketButton();

        String actualMsg = successPage.getSuccessfulMsg();
        String expectedMsg = "Ticket booked successfully!";
        Assert.assertEquals(actualMsg, expectedMsg, "Message display is not same");
        Assert.assertTrue(successPage.isCorrectInforTicket(ticket2));
    }

    @Test(description = "User can check price of ticket from Timetable")
    public void CheckPriceTicket(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(validUser);

        homePage.openTab("Timetable");
        trainTimetablePage.selectFunction(Locations.DA_NANG, Locations.SAI_GON, "TicketPricePage");

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(ticketPricePage.doesTitleExist());

        String actualMsg = ticketPricePage.getTicketInfor();
        String expectedMsg = "Ticket price from Đà Nẵng to Sài Gòn";
        softAssert.assertEquals(actualMsg, expectedMsg, "The ticket table displays incorrectly.");

        String actualPriceOfHS = ticketPricePage.getPriceBySeatType("HS");
        String actualPriceOfSS = ticketPricePage.getPriceBySeatType("SS");
        String actualPriceOfSSC = ticketPricePage.getPriceBySeatType("SSC");
        String actualPriceOfHB = ticketPricePage.getPriceBySeatType("HB");
        String actualPriceOfSB = ticketPricePage.getPriceBySeatType("SB");
        String actualPriceOfSBC = ticketPricePage.getPriceBySeatType("SBC");

        String expectedPriceOfHS = "310000";
        String expectedPriceOfSS = "335000";
        String expectedPriceOfSSC = "360000";
        String expectedPriceOfHB = "410000";
        String expectedPriceOfSB = "460000";
        String expectedPriceOfSBC = "510000";

        softAssert.assertEquals(actualPriceOfHS, expectedPriceOfHS,"The price of HS is not the same as expected.");
        softAssert.assertEquals(actualPriceOfSS, expectedPriceOfSS,"The price of SS is not the same as expected.");
        softAssert.assertEquals(actualPriceOfSSC, expectedPriceOfSSC,"The price of SSC is not the same as expected.");
        softAssert.assertEquals(actualPriceOfHB, expectedPriceOfHB,"The price of HB is not the same as expected.");
        softAssert.assertEquals(actualPriceOfSB, expectedPriceOfSB,"The price of SB is not the same as expected.");
        softAssert.assertEquals(actualPriceOfSBC, expectedPriceOfSBC,"The price of SBC is not the same as expected.");

        softAssert.assertAll();
    }

    @Test(description = "User can book ticket from Timetable")
    public void BookTicketFromTimeTable(){
        Ticket ticket4 = new Ticket(getDateAdd(10), Locations.QUANG_NGAI, Locations.HUE, SeatType.HS, "5");

        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(validUser);

        homePage.openTab("Timetable");
        trainTimetablePage.selectFunction(Locations.QUANG_NGAI, Locations.HUE, "BookTicketPage");

        String dateNext10 = getDateAdd(10);
        bookTicketPage.selectInfor("Date",dateNext10);
        bookTicketPage.selectInfor("TicketAmount", "5");
        bookTicketPage.clickBookTicketButton();

        String actualMsg = successPage.getSuccessfulMsg();
        String expectedMsg = "Ticket booked successfully!";
        Assert.assertEquals(actualMsg,expectedMsg,"Fail");
        Assert.assertTrue(successPage.isCorrectInforTicket(ticket4));
    }
}
