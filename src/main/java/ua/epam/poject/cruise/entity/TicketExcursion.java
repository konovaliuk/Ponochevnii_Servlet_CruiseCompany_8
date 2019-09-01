package ua.epam.poject.cruise.entity;


import java.io.Serializable;
import java.util.Objects;

public class TicketExcursion implements Serializable {

    private static final long serialVersionUID = -5553719354594101519L;

    private int id;
    private int excurisionId;
    private int ticketId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getExcurisionId() {
        return excurisionId;
    }

    public void setExcurisionId(int excurisionId) {
        this.excurisionId = excurisionId;
    }


    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketExcursion that = (TicketExcursion) o;
        return id == that.id &&
                excurisionId == that.excurisionId &&
                ticketId == that.ticketId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, excurisionId, ticketId);
    }

    @Override
    public String toString() {
        return new StringBuffer()
                .append("id: ").append(id)
                .append(", excurisionId: ").append(excurisionId)
                .append(", ticketId: ").append(ticketId)
                .toString();
    }

}
