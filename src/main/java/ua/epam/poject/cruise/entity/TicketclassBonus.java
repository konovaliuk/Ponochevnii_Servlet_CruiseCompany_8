package ua.epam.poject.cruise.entity;


import java.io.Serializable;
import java.util.Objects;

public class TicketclassBonus implements Serializable {

    private static final long serialVersionUID = 7487666675820370566L;

    private int id;
    private int ticketclassId;
    private int shipServiceId;
    private int cruiseId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getTicketclassId() {
        return ticketclassId;
    }

    public void setTicketclassId(int ticketclassId) {
        this.ticketclassId = ticketclassId;
    }


    public int getShipServiceId() {
        return shipServiceId;
    }

    public void setShipServiceId(int shipServiceId) {
        this.shipServiceId = shipServiceId;
    }


    public int getCruiseId() {
        return cruiseId;
    }

    public void setCruiseId(int cruiseId) {
        this.cruiseId = cruiseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketclassBonus that = (TicketclassBonus) o;
        return id == that.id &&
                ticketclassId == that.ticketclassId &&
                shipServiceId == that.shipServiceId &&
                cruiseId == that.cruiseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticketclassId, shipServiceId, cruiseId);
    }

    @Override
    public String toString() {
        return new StringBuffer()
                .append("id: ").append(id)
                .append(", ticketclassId: ").append(ticketclassId)
                .append(", shipServiceId: ").append(shipServiceId)
                .append(", cruiseId: ").append(cruiseId)
                .toString();
    }
}
