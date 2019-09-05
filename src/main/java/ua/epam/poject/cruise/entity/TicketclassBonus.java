package ua.epam.poject.cruise.entity;


import java.io.Serializable;
import java.util.Objects;

public class TicketclassBonus implements Serializable {

    private static final long serialVersionUID = 3638833465921848343L;

    private Long id = -1L;
    private Long ticketclassId;
    private Long shipServiceId;
    private Long cruiseId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getTicketclassId() {
        return ticketclassId;
    }

    public void setTicketclassId(Long ticketclassId) {
        this.ticketclassId = ticketclassId;
    }


    public Long getShipServiceId() {
        return shipServiceId;
    }

    public void setShipServiceId(Long shipServiceId) {
        this.shipServiceId = shipServiceId;
    }


    public Long getCruiseId() {
        return cruiseId;
    }

    public void setCruiseId(Long cruiseId) {
        this.cruiseId = cruiseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketclassBonus that = (TicketclassBonus) o;
        return id.equals(that.id) &&
                ticketclassId.equals(that.ticketclassId) &&
                shipServiceId.equals(that.shipServiceId) &&
                cruiseId.equals(that.cruiseId);
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
