package ua.study.poject.cruise.entity.printableentity;

import ua.study.poject.cruise.entity.Ticketclass;

import java.util.Objects;

public class PrintableTicketclassBonus {

    private Long ticketClassBonusId;
    private Long cruiseId;
    private PrintableServiceOnShip printableServiceOnShip;
    private Ticketclass ticketclass;

    public Long getTicketClassBonusId() {
        return ticketClassBonusId;
    }

    public void setTicketClassBonusId(Long ticketClassBonusId) {
        this.ticketClassBonusId = ticketClassBonusId;
    }

    public Long getCruiseId() {
        return cruiseId;
    }

    public void setCruiseId(Long cruiseId) {
        this.cruiseId = cruiseId;
    }

    public PrintableServiceOnShip getPrintableServiceOnShip() {
        return printableServiceOnShip;
    }

    public void setPrintableServiceOnShip(PrintableServiceOnShip printableServiceOnShip) {
        this.printableServiceOnShip = printableServiceOnShip;
    }

    public Ticketclass getTicketclass() {
        return ticketclass;
    }

    public void setTicketclass(Ticketclass ticketclass) {
        this.ticketclass = ticketclass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrintableTicketclassBonus that = (PrintableTicketclassBonus) o;
        return Objects.equals(ticketClassBonusId, that.ticketClassBonusId) &&
                Objects.equals(cruiseId, that.cruiseId) &&
                Objects.equals(printableServiceOnShip, that.printableServiceOnShip) &&
                Objects.equals(ticketclass, that.ticketclass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketClassBonusId, cruiseId, printableServiceOnShip, ticketclass);
    }

    @Override
    public String toString() {
        return " ticketClassBonusId: " + ticketClassBonusId +
                ", cruiseId: " + cruiseId +
                printableServiceOnShip +
                ticketclass;
    }
}
