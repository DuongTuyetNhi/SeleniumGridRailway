package pageObject;

import models.Ticket;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static base.DriverManagement.click;
import static base.DriverManagement.getDriver;

public class MyTicketPage extends BasePage{
    private String rowTicketInfo = "//table[@class='MyTable']//tr[td[text()='%s' and following-sibling::td[text()='%s'" +
            " and following-sibling::td[text()='%s' and following-sibling::td[text()='%s' " +
            "and following-sibling::td[text()='%s']]]]]]//input[contains(@onclick, 'Delete')]";

    public void cancelTicket(Ticket ticket) {
        By ticketLocator = By.xpath(String.format(rowTicketInfo, ticket.getDepartFrom().getValueLocation(), ticket.getArriveAt().getValueLocation(),
                ticket.getSeatType().getValueSeatType(), ticket.getDepartDate(), ticket.getAmount()));
        click(ticketLocator);
    }

    public void confirmCancel() {
        getDriver().switchTo().alert().accept();
    }

    public boolean isTicketDisappeared(Ticket ticket) {
        String ticketLocator = String.format(rowTicketInfo, ticket.getDepartFrom().getValueLocation(), ticket.getArriveAt().getValueLocation(),
                ticket.getSeatType().getValueSeatType(), ticket.getDepartDate(), ticket.getAmount());
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(ticketLocator)));
    }

}
